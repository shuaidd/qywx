package com.github.shuaidd.token;

import java.io.Serializable;

/**
 * 描述 缓存中的 access_token 条目：令牌值 + 过期时刻。
 * <p>
 * 过期策略集中在本类：企业微信返回的 expires_in 是令牌有效期（秒），这里提前
 * {@link #EXPIRY_BUFFER_SECONDS} 秒视为过期，避免令牌在网络传输途中失效；
 * 提前量不小于 {@link #MIN_TTL_SECONDS} 秒，避免 expires_in 异常偏小时每次调用都要重新取令牌。
 * <p>
 * 实现序列化接口，便于 Spring 环境下经 Redis 等外部缓存共享。
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class TokenEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 过期缓冲（秒）：距 expires_in 还剩这么久时即视为过期，提前刷新
     */
    public static final int EXPIRY_BUFFER_SECONDS = 300;

    /**
     * 最短缓存时间（秒）
     */
    public static final int MIN_TTL_SECONDS = 60;

    /**
     * 访问令牌
     */
    private final String accessToken;

    /**
     * 过期时刻（epoch 毫秒）
     */
    private final long expiresAtMillis;

    public TokenEntry(String accessToken, long expiresAtMillis) {
        this.accessToken = accessToken;
        this.expiresAtMillis = expiresAtMillis;
    }

    /**
     * 按企业微信返回的 expires_in 计算缓存时间并构造条目
     *
     * @param accessToken      访问令牌
     * @param expiresInSeconds 企业微信返回的有效期（秒），可为 null
     * @param nowMillis        当前时间（epoch 毫秒）
     * @return TokenEntry
     */
    public static TokenEntry of(String accessToken, Integer expiresInSeconds, long nowMillis) {
        return new TokenEntry(accessToken, nowMillis + ttlSeconds(expiresInSeconds) * 1000L);
    }

    /**
     * 计算缓存时间：max(MIN_TTL_SECONDS, expires_in - EXPIRY_BUFFER_SECONDS)
     *
     * @param expiresInSeconds 企业微信返回的有效期（秒），可为 null
     * @return 缓存时间（秒）
     */
    public static long ttlSeconds(Integer expiresInSeconds) {
        int expiresIn = expiresInSeconds == null ? 0 : expiresInSeconds;
        return Math.max(MIN_TTL_SECONDS, expiresIn - EXPIRY_BUFFER_SECONDS);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiresAtMillis() {
        return expiresAtMillis;
    }

    /**
     * 是否已过期（到达过期时刻即视为过期）
     *
     * @param nowMillis 当前时间（epoch 毫秒）
     * @return boolean
     */
    public boolean isExpired(long nowMillis) {
        return nowMillis >= expiresAtMillis;
    }

    /**
     * 不输出令牌本身，避免令牌随日志泄露
     */
    @Override
    public String toString() {
        return "TokenEntry{expiresAtMillis=" + expiresAtMillis + "}";
    }
}