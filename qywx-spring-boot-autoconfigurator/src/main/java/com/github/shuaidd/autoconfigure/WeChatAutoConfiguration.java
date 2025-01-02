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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-03 15:46
 * @author shuaidongdong
 */
@Configuration
@ComponentScan(basePackages = {"com.github.shuaidd.service", "com.github.shuaidd.support", "com.github.shuaidd.aspect"})
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
