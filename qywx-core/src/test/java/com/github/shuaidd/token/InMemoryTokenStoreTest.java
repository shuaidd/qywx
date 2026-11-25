package com.github.shuaidd.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * 描述 InMemoryTokenStore 存取与清除单元测试
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class InMemoryTokenStoreTest {

    /**
     * 按应用名存取；清除不存在的条目不抛异常
     */
    @Test
    public void putGetEvict() {
        InMemoryTokenStore store = new InMemoryTokenStore();
        assertNull(store.get("a"));

        TokenEntry entry = new TokenEntry("tok", Long.MAX_VALUE);
        store.put("a", entry);
        assertSame(entry, store.get("a"));

        TokenEntry replaced = new TokenEntry("tok-2", Long.MAX_VALUE);
        store.put("a", replaced);
        assertSame(replaced, store.get("a"));

        store.evict("a");
        assertNull(store.get("a"));
        store.evict("a");
        assertNull(store.get("a"));
    }
}