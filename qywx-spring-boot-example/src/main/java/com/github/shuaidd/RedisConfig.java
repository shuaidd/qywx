package com.github.shuaidd;

import com.github.shuaidd.autoconfigure.WeChatAutoConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * 此配置会覆盖掉默认的缓存配置 使用redis管理 access_token
 * @see WeChatAutoConfiguration
 *
 * @author ddshuai
 * @date 2022-09-01 15:10
 **/
@Configuration
@RequiredArgsConstructor
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * redis 过期时间
     */
    private final static Map<String, Long> EXPIRE_MAP = new HashMap<>();

    static {
        EXPIRE_MAP.put("qywx", 7200L);
    }

    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager;
        Map<String, RedisCacheConfiguration> cacheConfigurationMap = new HashMap<>();
        EXPIRE_MAP.forEach((key, value) -> {
            RedisSerializationContext.SerializationPair serializationPair = RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer());
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().prefixCacheNameWith("qywx:")
                    .serializeValuesWith(serializationPair)
                    .entryTtl(Duration.ofSeconds(value));
            cacheConfigurationMap.put(key, redisCacheConfiguration);
        });
        redisCacheManager = RedisCacheManager.builder(connectionFactory).withInitialCacheConfigurations(cacheConfigurationMap).build();
        return redisCacheManager;
    }

}
