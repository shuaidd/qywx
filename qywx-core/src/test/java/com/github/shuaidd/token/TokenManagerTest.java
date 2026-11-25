package com.github.shuaidd.token;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 TokenManager 缓存、过期、防击穿与参数校验单元测试
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class TokenManagerTest {

    private static final long T0 = Instant.parse("2026-01-01T00:00:00Z").toEpochMilli();

    private final MutableClock clock = new MutableClock(Instant.ofEpochMilli(T0));

    /**
     * 按应用分别缓存，命中缓存不再调用 gettoken
     */
    @Test
    public void cachesTokenPerApplication() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null), app("b", "sb", null));

        assertEquals("tok-1", manager.getAccessToken("a"));
        assertEquals("tok-1", manager.getAccessToken("a"));
        assertEquals("tok-2", manager.getAccessToken("b"));
        assertEquals("tok-1", manager.getAccessToken("a"));
        assertEquals(2, fetcher.calls.get());
    }

    /**
     * expires_in=7200 时缓存 6900 秒，到期后重新获取
     */
    @Test
    public void refreshesWhenTtlEnds() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        assertEquals("tok-1", manager.getAccessToken("a"));
        clock.advanceSeconds(6899);
        assertEquals("tok-1", manager.getAccessToken("a"));
        clock.advanceSeconds(1);
        assertEquals("tok-2", manager.getAccessToken("a"));
        assertEquals(2, fetcher.calls.get());
    }

    /**
     * expires_in 偏小时按最短缓存时间 60 秒处理
     */
    @Test
    public void usesMinimumTtlWhenExpiresInIsSmall() {
        CountingFetcher fetcher = new CountingFetcher(100);
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        assertEquals("tok-1", manager.getAccessToken("a"));
        clock.advanceSeconds(59);
        assertEquals("tok-1", manager.getAccessToken("a"));
        clock.advanceSeconds(1);
        assertEquals("tok-2", manager.getAccessToken("a"));
    }

    /**
     * expires_in 缺失时同样按 60 秒缓存
     */
    @Test
    public void nullExpiresInUsesMinimumTtl() {
        CountingFetcher fetcher = new CountingFetcher(null);
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        assertEquals("tok-1", manager.getAccessToken("a"));
        clock.advanceSeconds(60);
        assertEquals("tok-2", manager.getAccessToken("a"));
    }

    /**
     * evict 后下次获取重新调用 gettoken；清除未配置的应用不抛异常
     */
    @Test
    public void evictForcesRefetch() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        assertEquals("tok-1", manager.getAccessToken("a"));
        manager.evict("a");
        assertEquals("tok-2", manager.getAccessToken("a"));
        manager.evict("not-configured");
        assertEquals("tok-2", manager.getAccessToken("a"));
        assertEquals(2, fetcher.calls.get());
    }

    /**
     * 并发获取同一应用的令牌只调用一次 gettoken
     */
    @Test
    public void concurrentCallsFetchOnce() throws Exception {
        CountingFetcher fetcher = new CountingFetcher(7200);
        fetcher.sleepMillis = 200;
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        int threads = 16;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<String>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < threads; i++) {
                futures.add(pool.submit(() -> {
                    start.await();
                    return manager.getAccessToken("a");
                }));
            }
            start.countDown();
            for (Future<String> future : futures) {
                assertEquals("tok-1", future.get(10, TimeUnit.SECONDS));
            }
        } finally {
            pool.shutdownNow();
        }
        assertEquals(1, fetcher.calls.get());
    }

    /**
     * 应用名称为空时抛出参数校验异常，且不调用 gettoken
     */
    @Test
    public void blankApplicationNameRejected() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null));

        assertEquals("调用接口的应用名称不能为空",
                assertThrows(ParamCheckException.class, () -> manager.getAccessToken("")).getMessage());
        assertEquals("调用接口的应用名称不能为空",
                assertThrows(ParamCheckException.class, () -> manager.getAccessToken(null)).getMessage());
        assertEquals(0, fetcher.calls.get());
    }

    /**
     * 未配置的应用名称抛出参数校验异常，消息与 5.x 一致
     */
    @Test
    public void unknownApplicationRejected() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null), app("b", "sb", null));

        ParamCheckException e = assertThrows(ParamCheckException.class, () -> manager.getAccessToken("nope"));
        assertEquals("应用名称【nope】无效，不在有效配置列表之内-->[a, b]", e.getMessage());
        assertEquals(0, fetcher.calls.get());
    }

    /**
     * 应用密匙为空时抛出参数校验异常（5.x 误判的是 properties 对象，此处修正）
     */
    @Test
    public void blankSecretRejected() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "", null), app("b", null, null), app("c", "sc", null));

        assertEquals("a应用不存在密匙",
                assertThrows(ParamCheckException.class, () -> manager.getAccessToken("a")).getMessage());
        assertEquals("b应用不存在密匙",
                assertThrows(ParamCheckException.class, () -> manager.getAccessToken("b")).getMessage());
        assertEquals(0, fetcher.calls.get());
        assertEquals("tok-1", manager.getAccessToken("c"));
    }

    /**
     * 应用未单独配置企业号时回退到全局 corpId；密匙原样传递
     */
    @Test
    public void corpIdFallsBackToGlobalCorpId() {
        CountingFetcher fetcher = new CountingFetcher(7200);
        TokenManager manager = manager(fetcher, app("a", "sa", null), app("b", "sb", "corp-b"));

        manager.getAccessToken("a");
        manager.getAccessToken("b");

        assertEquals(Arrays.asList("corp-global", "corp-b"), fetcher.corpIds);
        assertEquals(Arrays.asList("sa", "sb"), fetcher.secrets);
    }

    /**
     * gettoken 抛出的 WeChatException 原样传播且不写缓存，下次调用会重新获取
     */
    @Test
    public void fetchFailureIsNotCached() {
        AtomicInteger calls = new AtomicInteger();
        TokenManager manager = new TokenManager(config(app("a", "sa", null)), new InMemoryTokenStore(), (corpId, secret) -> {
            calls.incrementAndGet();
            throw WeChatException.unknown("40001", "invalid credential");
        }, clock);

        WeChatException first = assertThrows(WeChatException.class, () -> manager.getAccessToken("a"));
        assertEquals("40001", first.getCode());
        assertThrows(WeChatException.class, () -> manager.getAccessToken("a"));
        assertEquals(2, calls.get());
    }

    private TokenManager manager(CountingFetcher fetcher, ApplicationProperties... apps) {
        return new TokenManager(config(apps), new InMemoryTokenStore(), fetcher, clock);
    }

    private static QywxConfig config(ApplicationProperties... apps) {
        QywxConfig config = new QywxConfig();
        config.setCorpId("corp-global");
        config.setApplicationList(new ArrayList<>(Arrays.asList(apps)));
        return config;
    }

    private static ApplicationProperties app(String name, String secret, String corpId) {
        ApplicationProperties app = new ApplicationProperties();
        app.setApplicationName(name);
        app.setSecret(secret);
        app.setCorpId(corpId);
        return app;
    }

    private static AccessTokenResponse response(String token, Integer expiresIn) {
        AccessTokenResponse response = new AccessTokenResponse();
        response.setAccessToken(token);
        response.setExpiresIn(expiresIn);
        return response;
    }

    /**
     * 记录调用次数与入参的 gettoken 替身
     */
    private static final class CountingFetcher implements TokenManager.TokenFetcher {

        private final AtomicInteger calls = new AtomicInteger();

        private final List<String> corpIds = new CopyOnWriteArrayList<>();

        private final List<String> secrets = new CopyOnWriteArrayList<>();

        private final Integer expiresIn;

        private long sleepMillis;

        private CountingFetcher(Integer expiresIn) {
            this.expiresIn = expiresIn;
        }

        @Override
        public AccessTokenResponse fetch(String corpId, String secret) {
            int call = calls.incrementAndGet();
            corpIds.add(corpId);
            secrets.add(secret);
            if (sleepMillis > 0) {
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return response("tok-" + call, expiresIn);
        }
    }

    /**
     * 可手动推进的时钟
     */
    private static final class MutableClock extends Clock {

        private Instant instant;

        private MutableClock(Instant instant) {
            this.instant = instant;
        }

        private void advanceSeconds(long seconds) {
            instant = instant.plusSeconds(seconds);
        }

        @Override
        public ZoneId getZone() {
            return ZoneOffset.UTC;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return instant;
        }
    }
}
