package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import com.github.shuaidd.support.CallBackManager;
import com.github.shuaidd.support.WeChatContextHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * 描述 企业微信 Spring 适配层配置：绑定 {@code qywx.*}、提供默认的本地 {@link CacheManager}，并把回调配置登记到
 * {@link CallBackManager}
 * <p>
 * 由 5.x 的同名类迁移而来，去掉了 {@code @ComponentScan}：service / support / aspect 已迁入 qywx-core，不再带 Spring
 * 构造型注解，扫描不会命中任何类；client 是原生 OpenFeign 代理，也不经 Spring Cloud OpenFeign 注册。
 *
 * @author ddshuai
 * date 2019-04-03 15:46
 * @author shuaidongdong
 */
@Configuration
@EnableConfigurationProperties(WeChatConfigurationProperties.class)
public class WeChatAutoConfiguration implements InitializingBean {

    private final WeChatConfigurationProperties properties;

    public WeChatAutoConfiguration(WeChatConfigurationProperties properties) {
        this.properties = properties;
    }

    @ConditionalOnMissingBean(CacheManager.class)
    @Bean
    public CacheManager concurrentMapCacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(true);
        concurrentMapCacheManager.setCacheNames(Collections.singletonList("qywx"));
        return concurrentMapCacheManager;
    }

    @Bean
    public WeChatContextHolder weChatContextHolder() {
        return new WeChatContextHolder();
    }

    @Override
    public void afterPropertiesSet() {
        CallBackManager.properties(properties.getCallbackList());
    }
}
