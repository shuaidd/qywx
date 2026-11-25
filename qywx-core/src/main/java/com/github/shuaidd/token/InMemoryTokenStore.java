package com.github.shuaidd.token;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述 进程内的 access_token 缓存（默认实现）。
 * <p>
 * 过期条目由 {@link TokenManager} 在下次读取时识别并用新令牌覆盖；条目数量以配置的应用数为上限。
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class InMemoryTokenStore implements TokenStore {

    private final Map<String, TokenEntry> entries = new ConcurrentHashMap<>();

    @Override
    public TokenEntry get(String applicationName) {
        return entries.get(applicationName);
    }

    @Override
    public void put(String applicationName, TokenEntry entry) {
        entries.put(applicationName, entry);
    }

    @Override
    public void evict(String applicationName) {
        entries.remove(applicationName);
    }
}
