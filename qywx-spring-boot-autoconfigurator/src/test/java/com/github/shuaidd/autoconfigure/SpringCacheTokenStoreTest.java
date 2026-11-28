package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.token.TokenEntry;
import org.junit.jupiter.api.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 {@link SpringCacheTokenStore} 的接线守卫：直写 {@code qywx} 缓存、非 {@link TokenEntry} 视为未命中、
 * 缺缓存时退回进程内、缓存异常原样透传
 *
 * @author ddshuai
 * date 2026-11-28 02:00
 **/
public class SpringCacheTokenStoreTest {

    private static final String APP = "app-cache";

    private static TokenEntry entry() {
        return new TokenEntry("token-value", System.currentTimeMillis() + 7_000_000L);
    }

    /**
     * 有 qywx 缓存时按 access_token:<app> 直写整条 TokenEntry，evict 同键
     */
    @Test
    public void writesEntriesIntoTheQywxCache() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(SpringCacheTokenStore.CACHE_NAME);
        SpringCacheTokenStore store = new SpringCacheTokenStore(cacheManager);
        TokenEntry entry = entry();

        store.put(APP, entry);

        Cache cache = cacheManager.getCache(SpringCacheTokenStore.CACHE_NAME);
        assertNotNull(cache);
        Cache.ValueWrapper wrapper = cache.get("access_token:" + APP);
        assertNotNull(wrapper);
        assertSame(entry, wrapper.get());
        assertSame(entry, store.get(APP));

        store.evict(APP);

        assertNull(cache.get("access_token:" + APP));
        assertNull(store.get(APP));
    }

    /**
     * 5.x 留下的字符串、JSON 读回的 LinkedHashMap 都不是 TokenEntry：视为未命中，原值不动
     */
    @Test
    public void treatsForeignValuesAsMisses() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(SpringCacheTokenStore.CACHE_NAME);
        Cache cache = cacheManager.getCache(SpringCacheTokenStore.CACHE_NAME);
        assertNotNull(cache);
        String legacy = "qywx_access_token_legacy";
        LinkedHashMap<String, Object> jsonShape = new LinkedHashMap<String, Object>();
        cache.put("access_token:" + APP, legacy);
        cache.put("access_token:other", jsonShape);
        SpringCacheTokenStore store = new SpringCacheTokenStore(cacheManager);

        assertNull(store.get(APP));
        assertNull(store.get("other"));

        Cache.ValueWrapper legacyWrapper = cache.get("access_token:" + APP);
        Cache.ValueWrapper jsonShapeWrapper = cache.get("access_token:other");
        assertNotNull(legacyWrapper);
        assertNotNull(jsonShapeWrapper);
        assertSame(legacy, legacyWrapper.get());
        assertSame(jsonShape, jsonShapeWrapper.get());
    }

    /**
     * CacheManager 是静态模式且没有 qywx 缓存：退回进程内，不会顺手把 qywx 缓存建出来
     */
    @Test
    public void fallsBackToInMemoryWhenCacheIsMissing() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("other");
        SpringCacheTokenStore store = new SpringCacheTokenStore(cacheManager);
        TokenEntry entry = entry();

        store.put(APP, entry);

        assertSame(entry, store.get(APP));
        assertEquals(Collections.singleton("other"), new HashSet<String>(cacheManager.getCacheNames()));
        assertNull(cacheManager.getCache(SpringCacheTokenStore.CACHE_NAME));

        store.evict(APP);

        assertNull(store.get(APP));
    }

    @Test
    public void rejectsNullCacheManager() {
        NullPointerException error = assertThrows(NullPointerException.class, () -> new SpringCacheTokenStore(null));

        assertEquals("cacheManager", error.getMessage());
    }

    /**
     * 缓存自身抛异常时原样透传，不吞成「没取到 token」
     */
    @Test
    public void propagatesCacheFailures() {
        CacheManager cacheManager = new CacheManager() {
            @Override
            public Cache getCache(@NonNull String name) {
                return new ConcurrentMapCache(name) {
                    @Override
                    public ValueWrapper get(@NonNull Object key) {
                        throw new IllegalStateException("cache down");
                    }
                };
            }

            @Override
            public @NonNull Collection<String> getCacheNames() {
                return new ArrayList<>();
            }
        };
        SpringCacheTokenStore store = new SpringCacheTokenStore(cacheManager);

        IllegalStateException error = assertThrows(IllegalStateException.class, () -> store.get(APP));

        assertEquals("cache down", error.getMessage());
    }
}
