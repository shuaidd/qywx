package com.github.shuaidd;

import com.github.shuaidd.QywxClient;
import com.github.shuaidd.service.TokenService;
import com.github.shuaidd.support.WeChatManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 只验证 Spring 容器能把 6.0 的适配层装配起来：不做业务调用，不访问企业微信，也不连 redis。
 * <p>
 * 6.0 的 starter 不再依赖 spring-cloud-openfeign，也不靠 @EnableFeignClients 扫描：
 * WeChatManager / QywxClient / 各 service 由 WeChatAutoConfiguration 经 AutoConfiguration.imports 显式登记。
 * 本测试走的是「真实 Application + 真实 application.yml」这条路径，与 autoconfigurator 模块里
 * 基于 ApplicationContextRunner 的用例互补；spring.cache.type=simple 只为覆盖 yml 里的 redis。
 *
 * @author ddshuai
 * date 2026-11-28
 **/
@SpringBootTest(classes = Application.class, properties = "spring.cache.type=simple")
public class ApplicationContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void contextLoadsAndRegistersCoreBeans() {
        assertNotNull(applicationContext.getBean(WeChatManager.class));
        assertNotNull(applicationContext.getBean(QywxClient.class));
        assertEquals(1, applicationContext.getBeansOfType(TokenService.class).size());
    }
}
