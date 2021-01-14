package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.client.config.WeChatConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
public class WeChatAutoConfiguration {

    @ConditionalOnMissingBean(CacheManager.class)
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(true);
        concurrentMapCacheManager.setCacheNames(Collections.singletonList("qywx"));
        return concurrentMapCacheManager;
    }
}
