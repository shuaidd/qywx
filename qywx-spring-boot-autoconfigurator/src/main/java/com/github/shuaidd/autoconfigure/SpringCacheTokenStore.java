package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.token.InMemoryTokenStore;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

/**
 * 描述 把 Spring 的 {@link CacheManager} 包成 core 的 {@link TokenStore}：容器里有 Redis 等共享缓存时 access_token 自动共用，
 * 多实例部署不用再自己实现 TokenStore
 * <p>
 * 由 5.x 的 {@code TokenService} 上的 {@code @Cacheable} / {@code @CacheEvict} 切面迁来（core 不带 Spring AOP），与 5.x 有三点刻意不同：
 * <ol>
 * <li>键是 {@code access_token:<applicationName>}，5.x 是 {@code qywx_access_token_<applicationName>}：这里存的是整条
 * {@link TokenEntry}（token + 过期时刻），5.x 存的是字符串；滚动升级期间新旧版本共用同一个 Redis 也不会误读对方的值；</li>
 * <li>从缓存里读回的不是 {@link TokenEntry}（比如 JSON 序列化器读回的 {@code LinkedHashMap}）视为未命中、重新取 token，
 * 不抛 {@code ClassCastException}；共享缓存请用 JDK 序列化，或带类型信息的 JSON 序列化；</li>
 * <li>{@link CacheManager} 里没有名为 {@link #CACHE_NAME} 的缓存时打一条 warn、退回进程内的 {@link InMemoryTokenStore}，
 * 不会静默丢 token：Boot 默认的 {@code ConcurrentMapCacheManager} 是动态模式会按需建缓存，适配层自带的那个只建 {@code qywx}。</li>
 * </ol>
 * 缓存自身的异常（连不上 Redis 等）原样透传，由调用方决定失败还是降级。
 *
 * @author ddshuai
 * date 2026-11-28 02:00
 **/
public class SpringCacheTokenStore implements TokenStore {

    public static final String CACHE_NAME = "qywx";

    private static final Logger logger = LoggerFactory.getLogger(SpringCacheTokenStore.class);

    private final Cache cache;

    private final TokenStore fallback = new InMemoryTokenStore();

    public SpringCacheTokenStore(CacheManager cacheManager) {
        Objects.requireNonNull(cacheManager, "cacheManager");
        Cache found = cacheManager.getCache(CACHE_NAME);
        if (found == null) {
            logger.warn("CacheManager 里没有名为 {} 的缓存，access_token 退回进程内缓存", CACHE_NAME);
        }
        this.cache = found;
    }

    static String key(String applicationName) {
        return "access_token:" + applicationName;
    }

    @Override
    public TokenEntry get(String applicationName) {
        if (cache == null) {
            return fallback.get(applicationName);
        }
        Cache.ValueWrapper wrapper = cache.get(key(applicationName));
        Object value = wrapper == null ? null : wrapper.get();
        return value instanceof TokenEntry ? (TokenEntry) value : null;
    }

    @Override
    public void put(String applicationName, TokenEntry entry) {
        Objects.requireNonNull(entry, "entry");
        if (cache == null) {
            fallback.put(applicationName, entry);
            return;
        }
        cache.put(key(applicationName), entry);
    }

    @Override
    public void evict(String applicationName) {
        if (cache == null) {
            fallback.evict(applicationName);
            return;
        }
        cache.evict(key(applicationName));
    }
}
