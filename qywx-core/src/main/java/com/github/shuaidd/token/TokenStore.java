package com.github.shuaidd.token;

/**
 * 描述 access_token 缓存组件。
 * <p>
 * 默认实现为进程内的 {@link InMemoryTokenStore}；多实例部署需要共享令牌时可提供自己的实现
 * （例如基于 Redis），Spring 环境下由适配层提供基于 Spring Cache 的实现。
 * 过期判断由 {@link TokenManager} 统一完成，实现类只需按应用名存取条目。
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public interface TokenStore {

    /**
     * 读取缓存的令牌条目
     *
     * @param applicationName 应用名称
     * @return 条目；不存在时返回 null
     */
    TokenEntry get(String applicationName);

    /**
     * 写入令牌条目（覆盖同名条目）
     *
     * @param applicationName 应用名称
     * @param entry           条目
     */
    void put(String applicationName, TokenEntry entry);

    /**
     * 清除令牌条目；条目不存在时静默返回
     *
     * @param applicationName 应用名称
     */
    void evict(String applicationName);
}
