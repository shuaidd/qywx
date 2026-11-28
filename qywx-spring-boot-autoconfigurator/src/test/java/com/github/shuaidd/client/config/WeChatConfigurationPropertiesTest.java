package com.github.shuaidd.client.config;

import com.github.shuaidd.config.QywxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link WeChatConfigurationProperties} 的绑定守卫：README 与 example 的 application.yml 里出现的全部 {@code qywx.*} 键
 * （含宽松命名的驼峰与短横线两种写法）经 Spring Boot {@link Binder} 绑定后，与纯 Java 的 {@link QywxConfig} 字段对齐
 *
 * @author ddshuai
 * date 2026-11-28 01:30
 **/
public class WeChatConfigurationPropertiesTest {

    private static final String URL = "https://qyapi.example.invalid";

    /**
     * README 与 example application.yml 里出现的键名全部能绑定：corp-id / url / public-path / debug-mode、
     * application-list 的 secret / agent-id / application-name（第二个应用按 README 用驼峰 agentId）、callback-list 的四个键
     */
    @Test
    public void bindsDocumentedKeys() {
        Map<String, Object> source = new HashMap<>();
        source.put("qywx.corp-id", "corp-1");
        source.put("qywx.url", URL);
        source.put("qywx.public-path", "cgi-bin");
        source.put("qywx.debug-mode", "true");
        source.put("qywx.application-list[0].secret", "s-1");
        source.put("qywx.application-list[0].agent-id", "1000003");
        source.put("qywx.application-list[0].application-name", "little-helper");
        source.put("qywx.application-list[1].secret", "s-2");
        source.put("qywx.application-list[1].agentId", "1000004");
        source.put("qywx.application-list[1].application-name", "reporter");
        source.put("qywx.callback-list[0].application-name", "cb-app");
        source.put("qywx.callback-list[0].token", "token");
        source.put("qywx.callback-list[0].encoding-aes-key", "key");
        source.put("qywx.callback-list[0].receive-id", "receive");

        WeChatConfigurationProperties properties = bind(source);

        assertAll(
                () -> assertEquals("corp-1", properties.getCorpId()),
                () -> assertEquals(URL, properties.getUrl()),
                () -> assertEquals("cgi-bin", properties.getPublicPath()),
                () -> assertEquals("https://qyapi.example.invalid/cgi-bin", properties.baseUrl()),
                () -> assertEquals(Boolean.TRUE, properties.getDebugMode()),
                () -> assertEquals(2, properties.getApplicationList().size()),
                () -> assertEquals("s-1", properties.findApplication("little-helper").getSecret()),
                () -> assertEquals("1000003", properties.findApplication("little-helper").getAgentId()),
                () -> assertEquals("reporter", properties.findApplication("reporter").getApplicationName()),
                () -> assertEquals("1000004", properties.findApplication("reporter").getAgentId()),
                () -> assertEquals(1, properties.getCallbackList().size()),
                () -> assertEquals("cb-app", properties.getCallbackList().get(0).getApplicationName()),
                () -> assertEquals("token", properties.getCallbackList().get(0).getToken()),
                () -> assertEquals("key", properties.getCallbackList().get(0).getEncodingAesKey()),
                () -> assertEquals("receive", properties.getCallbackList().get(0).getReceiveId()));
    }

    /**
     * 6.0 新增的两个可选项 qywx.connect-timeout-millis / read-timeout-millis 能绑定到继承自 core 的同名字段
     */
    @Test
    public void bindsTimeouts() {
        Map<String, Object> source = new HashMap<>();
        source.put("qywx.connect-timeout-millis", 3000);
        source.put("qywx.read-timeout-millis", 45000);

        WeChatConfigurationProperties properties = bind(source);

        assertEquals(3000, properties.getConnectTimeoutMillis());
        assertEquals(45000, properties.getReadTimeoutMillis());
    }

    /**
     * 什么都不配时，绑定结果与纯 Java 的 QywxConfig.builder().build() 对齐：url / public-path / 超时是 core 默认值，
     * 两个列表为空而不是 null，debugMode 为 false，corpId 与 tokenStore 为 null
     */
    @Test
    public void usesCoreDefaultsWhenNothingConfigured() {
        QywxConfig expected = QywxConfig.builder().build();

        WeChatConfigurationProperties properties = bind(new HashMap<>());

        assertAll(
                () -> assertEquals(QywxConfig.DEFAULT_URL, properties.getUrl()),
                () -> assertEquals(QywxConfig.DEFAULT_PUBLIC_PATH, properties.getPublicPath()),
                () -> assertEquals(expected.getConnectTimeoutMillis(), properties.getConnectTimeoutMillis()),
                () -> assertEquals(expected.getReadTimeoutMillis(), properties.getReadTimeoutMillis()),
                () -> assertNotNull(properties.getApplicationList()),
                () -> assertTrue(properties.getApplicationList().isEmpty()),
                () -> assertNotNull(properties.getCallbackList()),
                () -> assertTrue(properties.getCallbackList().isEmpty()),
                () -> assertEquals(Boolean.FALSE, properties.getDebugMode()),
                () -> assertNull(properties.getCorpId()),
                () -> assertNull(properties.getTokenStore()));
    }

    /**
     * 用与 Spring Boot 启动时相同的 Binder 绑定 qywx 前缀；去掉系统属性与环境变量两个来源，避免本机的 QYWX_* 变量干扰用例
     */
    private static WeChatConfigurationProperties bind(@NonNull Map<String, Object> source) {
        StandardEnvironment environment = new StandardEnvironment();
        environment.getPropertySources().remove(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME);
        environment.getPropertySources().remove(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);
        environment.getPropertySources().addFirst(new MapPropertySource("qywx-test", source));
        return Binder.get(environment).bindOrCreate("qywx", Bindable.of(WeChatConfigurationProperties.class));
    }
}
