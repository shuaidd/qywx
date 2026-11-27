package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.client.config.CallbackProperties;
import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.support.CallBackManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 {@link WeChatAutoConfiguration} 在 Spring Boot 下的装配守卫：经 {@code AutoConfiguration.imports}
 * 可被自动配置机制发现、仍由 {@code @EnableConfigurationProperties} 绑定 {@code qywx.*}、并把回调配置登记到
 * {@link CallBackManager}
 *
 * @author ddshuai
 * date 2026-11-28 01:30
 **/
public class WeChatAutoConfigurationTest {

    @AfterEach
    public void resetStaticEntrances() {
        //静态入口是进程级的，测试里动过就清掉，避免污染其他用例
        CallBackManager.properties(new ArrayList<CallbackProperties>());
    }

    /**
     * Boot 2.7+ 只认 META-INF/spring/…AutoConfiguration.imports：适配层在 classpath 上时，不加 @EnableWeChat 也会被自动配置机制发现
     */
    @Test
    public void isListedInAutoConfigurationImports() {
        List<String> candidates = ImportCandidates.load(AutoConfiguration.class, getClass().getClassLoader()).getCandidates();

        //Boot 自己的 autoconfigure jar 也在同一 classpath 上，候选有一百多个，只断言本适配层恰好在册一次
        assertEquals(1, Collections.frequency(candidates, WeChatAutoConfiguration.class.getName()), candidates.toString());
    }

    /**
     * 属性绑定入口保持不变：仍是 @EnableConfigurationProperties(WeChatConfigurationProperties.class)，前缀仍是 qywx
     */
    @Test
    public void bindsQywxPropertiesThroughEnableConfigurationProperties() {
        EnableConfigurationProperties annotation = WeChatAutoConfiguration.class.getAnnotation(EnableConfigurationProperties.class);
        assertNotNull(annotation);
        assertArrayEquals(new Class<?>[]{WeChatConfigurationProperties.class}, annotation.value());
        ConfigurationProperties properties = WeChatConfigurationProperties.class.getAnnotation(ConfigurationProperties.class);
        assertNotNull(properties);
        assertEquals("qywx", properties.prefix());
    }

    /**
     * afterPropertiesSet 把配置里的回调列表转发到 CallBackManager：已登记的应用走到密钥校验（故意给非法 aesKey），
     * 未登记的应用直接返回空串
     */
    @Test
    public void registersCallbackListIntoCallBackManager() throws AesException {
        CallbackProperties callback = new CallbackProperties();
        callback.setApplicationName("cb-app");
        callback.setToken("token");
        callback.setEncodingAesKey("too-short");
        callback.setReceiveId("receive");
        WeChatConfigurationProperties properties = new WeChatConfigurationProperties();
        properties.setCallbackList(new ArrayList<>(Collections.singletonList(callback)));

        new WeChatAutoConfiguration(properties).afterPropertiesSet();

        assertThrows(AesException.class, () -> CallBackManager.verifyUrl("cb-app", "sig", "ts", "nonce", "echo"));
        assertEquals("", CallBackManager.verifyUrl("not-configured", "sig", "ts", "nonce", "echo"));
    }

    /**
     * 没有配置回调列表时，afterPropertiesSet 登记的是空列表：处理回调抛 WeChatException，消息里带着应用名
     */
    @Test
    public void registersEmptyCallbackListWhenNothingConfigured() {
        new WeChatAutoConfiguration(new WeChatConfigurationProperties()).afterPropertiesSet();

        WeChatException error = assertThrows(WeChatException.class,
                () -> CallBackManager.handle("little-helper", "<xml/>", "sig", "ts", "nonce"));
        assertEquals("应用-little-helper-未配置密钥信息，无法处理", error.getMessage());
    }
}
