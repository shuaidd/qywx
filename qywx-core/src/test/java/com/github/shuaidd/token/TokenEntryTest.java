package com.github.shuaidd.token;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 TokenEntry 过期策略单元测试
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class TokenEntryTest {

    private static final long T0 = Instant.parse("2026-01-01T00:00:00Z").toEpochMilli();

    /**
     * expires_in=7200 时缓存 6900 秒，到达过期时刻即视为过期
     */
    @Test
    public void expiresIn7200CachesFor6900Seconds() {
        TokenEntry entry = TokenEntry.of("tok", 7200, T0);
        assertEquals(T0 + 6900_000L, entry.getExpiresAtMillis());
        assertEquals("tok", entry.getAccessToken());
        assertFalse(entry.isExpired(T0));
        assertFalse(entry.isExpired(T0 + 6900_000L - 1));
        assertTrue(entry.isExpired(T0 + 6900_000L));
    }

    /**
     * 缓存时间 = max(60, expires_in − 300)
     */
    @Test
    public void ttlNeverBelowMinimum() {
        assertEquals(60, TokenEntry.ttlSeconds(null));
        assertEquals(60, TokenEntry.ttlSeconds(0));
        assertEquals(60, TokenEntry.ttlSeconds(300));
        assertEquals(60, TokenEntry.ttlSeconds(360));
        assertEquals(61, TokenEntry.ttlSeconds(361));
        assertEquals(6900, TokenEntry.ttlSeconds(7200));
    }

    /**
     * toString 不输出令牌
     */
    @Test
    public void toStringHidesToken() {
        TokenEntry entry = TokenEntry.of("secret-token-value", 7200, T0);
        assertFalse(entry.toString().contains("secret-token-value"));
        assertTrue(entry.toString().contains(String.valueOf(T0 + 6900_000L)));
    }
}
