package com.github.shuaidd.client.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractApi;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.CountingFetcher;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenManager;
import feign.FeignException;
import feign.InvocationHandlerFactory.MethodHandler;
import feign.Request;
import feign.Target;
import feign.Util;
import feign.codec.DecodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link TokenRetryInvocationHandlerFactory} 的单元测试：令牌失效重试一次、其它错误不重试、无应用名参数不重试、二次失败原样抛、契约里的 token-expired 场景回放、代理的 equals/hashCode/toString 语义
 *
 * @author ddshuai
 * date 2026-11-26 03:45
 **/
class TokenRetryInvocationHandlerFactoryTest {

    private static final Request REQUEST = Request.create(Request.HttpMethod.GET,
            RecordingClient.TARGET_URL + "/get_api_domain_ip", Collections.emptyMap(), null, Util.UTF_8);

    private static final Pattern TOKEN = Pattern.compile("access_token=([^&]+)");

    private static final Pattern TOKEN_INDEX = Pattern.compile("access_token=\\D*(\\d+)");

    /**
     * 40014 / 41001 / 42001：清缓存、重新取号、重试一次后成功
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"40014", "41001", "42001"})
    void retriesOnceWhenTokenInvalid(String code) {
        Fixture fixture = new Fixture();
        fixture.recording.respond(200, "{\"errcode\":" + code + ",\"errmsg\":\"token invalid\"}");

        assertNotNull(fixture.api.apiDomainIp(ContractFeign.APP));

        assertEquals(2, fixture.recording.requestCount());
        assertEquals(2, fixture.fetcher.calls());
        assertEquals(Collections.singletonList(ContractFeign.APP), fixture.store.evictions());
        assertEquals(Arrays.asList(ContractFeign.APP, ContractFeign.APP), fixture.store.puts());
        assertTokenRequest(fixture.recording.request(0), "tok-1");
        assertTokenRequest(fixture.recording.request(1), "tok-2");
        assertEquals("tok-2", fixture.store.get(ContractFeign.APP).getAccessToken());
    }

    /**
     * 其它业务错误码（40001）：不重试，按 Feign 的包装形态原样抛出
     */
    @Test
    void rethrowsOtherWeChatErrorsWithoutRetry() {
        Fixture fixture = new Fixture();
        fixture.recording.respond(200, "{\"errcode\":40001,\"errmsg\":\"invalid credential\"}");

        DecodeException thrown = assertThrows(DecodeException.class,
                () -> fixture.api.apiDomainIp(ContractFeign.APP));

        WeChatException cause = ContractFeign.findCause(thrown, WeChatException.class);
        assertNotNull(cause);
        assertEquals(ErrorCode.ERROR_CODE_40001, cause.getErrorCode());
        assertEquals("40001", cause.getCode());
        assertEquals("invalid credential", cause.getMessage());
        assertEquals(1, fixture.recording.requestCount());
        assertEquals(1, fixture.fetcher.calls());
        assertTrue(fixture.store.evictions().isEmpty());
    }

    /**
     * 方法没有 @Param("app")（ServiceProviderClient）：不知道该清哪个应用，不重试
     */
    @Test
    void doesNotRetryWhenMethodHasNoApplicationParam() {
        RecordingClient recording = new RecordingClient();
        CountingFetcher fetcher = new CountingFetcher();
        RecordingStore store = new RecordingStore();
        QywxConfig config = ContractFeign.config(false);
        ContractApi api = ContractFeign.createWithoutInterceptor(ContractApi.class, JacksonJsonEncoder.compact(),
                config, ContractFeign.tokenManager(fetcher, config, store), recording);
        JsonNode entry = ContractSnapshot.entry("ServiceProviderClient.getProviderToken");
        recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"token invalid\"}");

        DecodeException thrown = assertThrows(DecodeException.class,
                () -> api.getProviderToken((ProviderTokenRequest) ContractSnapshot.args(entry)[0]));

        WeChatException cause = ContractFeign.findCause(thrown, WeChatException.class);
        assertNotNull(cause);
        assertEquals(ErrorCode.ERROR_CODE_42001, cause.getErrorCode());
        assertEquals(1, recording.requestCount());
        assertEquals(0, fetcher.calls());
        assertTrue(store.evictions().isEmpty());
    }

    /**
     * 重试后仍令牌失效：只重试一次，第二次的失败原样抛出
     */
    @Test
    void rethrowsSecondFailureAsIs() {
        Fixture fixture = new Fixture();
        fixture.recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"expired first\"}");
        fixture.recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"expired second\"}");

        DecodeException thrown = assertThrows(DecodeException.class,
                () -> fixture.api.apiDomainIp(ContractFeign.APP));

        WeChatException cause = ContractFeign.findCause(thrown, WeChatException.class);
        assertNotNull(cause);
        assertEquals("expired second", cause.getMessage());
        assertTrue(thrown.getMessage().contains("expired second"), "顶层异常信息：" + thrown.getMessage());
        assertEquals(2, fixture.recording.requestCount());
        assertEquals(Collections.singletonList(ContractFeign.APP), fixture.store.evictions());
        assertEquals(2, fixture.fetcher.calls());
    }

    /**
     * 非业务异常（HTTP 500）：不重试
     */
    @Test
    void rethrowsNonWeChatFailures() {
        Fixture fixture = new Fixture();
        fixture.recording.respond(500, "boom");

        FeignException thrown = assertThrows(FeignException.class,
                () -> fixture.api.apiDomainIp(ContractFeign.APP));

        assertEquals(500, thrown.status());
        assertEquals(1, fixture.recording.requestCount());
        assertTrue(fixture.store.evictions().isEmpty());
    }

    /**
     * cause 链两层的业务异常（DecodeException ← WeChatException）：重试一次，重试仍失败时抛出的是同一个实例
     */
    @Test
    void rethrowsTheSameWrappedFailureWhenRetryFailsAgain() {
        RecordingStore store = new RecordingStore();
        AtomicInteger invokes = new AtomicInteger();
        DecodeException failure = new DecodeException(200, "expired", REQUEST,
                new WeChatException("expired", ErrorCode.ERROR_CODE_42001));
        ContractApi api = failingProxy(store, failure, invokes);

        assertSame(failure, assertThrows(DecodeException.class, () -> api.apiDomainIp(ContractFeign.APP)));

        assertEquals(2, invokes.get());
        assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
    }

    /**
     * 枚举里没有的错误码以原始码为准：unknown 携带 42001 时同样重试
     */
    @Test
    void retriesWhenUnknownErrorCodeCarriesRetryCode() {
        RecordingStore store = new RecordingStore();
        AtomicInteger invokes = new AtomicInteger();
        ContractApi api = failingProxy(store, WeChatException.unknown("42001", "expired"), invokes);

        assertThrows(WeChatException.class, () -> api.apiDomainIp(ContractFeign.APP));

        assertEquals(2, invokes.get());
        assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
    }

    /**
     * unknown 携带的原始码不在重试范围内：不重试
     */
    @Test
    void doesNotRetryWhenUnknownErrorCodeIsNotRetryable() {
        RecordingStore store = new RecordingStore();
        AtomicInteger invokes = new AtomicInteger();
        ContractApi api = failingProxy(store, WeChatException.unknown("40001", "expired"), invokes);

        assertThrows(WeChatException.class, () -> api.apiDomainIp(ContractFeign.APP));

        assertEquals(1, invokes.get());
        assertTrue(store.evictions().isEmpty());
    }

    /**
     * 回放 5.2.0 的 token-expired 场景：缓存命中发第一个请求 → 42001 → gettoken → 新令牌重试，三条请求与快照逐条一致
     */
    @Test
    void replaysTokenExpiredContract() {
        JsonNode entry = ContractSnapshot.entry("WeChatClient.apiDomainIp#token-expired");
        String first = "tok-" + tokenIndexOf(entry.path("requests").get(0));
        String second = "tok-" + tokenIndexOf(entry.path("requests").get(2));
        RecordingClient recording = new RecordingClient();
        RecordingStore store = new RecordingStore();
        QywxConfig config = ContractFeign.config(false);
        AtomicReference<ContractApi> reference = new AtomicReference<ContractApi>();
        AtomicInteger fetches = new AtomicInteger();
        TokenManager.TokenFetcher fetcher = (corpId, secret) -> {
            fetches.incrementAndGet();
            return reference.get().getAccessToken(corpId, secret);
        };
        ContractApi api = ContractFeign.create(ContractApi.class, config,
                ContractFeign.tokenManager(fetcher, config, store), recording);
        reference.set(api);
        store.put(ContractFeign.APP, TokenEntry.of(first, 7200, System.currentTimeMillis()));

        assertNotNull(api.apiDomainIp(ContractFeign.APP));

        assertEquals(1, recording.requestCount());
        assertEquals(0, fetches.get());

        recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"access_token expired\"}");
        recording.respond(200, "{\"errcode\":0,\"errmsg\":\"ok\",\"access_token\":\"" + second
                + "\",\"expires_in\":7200}");

        assertNotNull(api.apiDomainIp(ContractFeign.APP));

        assertEquals(4, recording.requestCount());
        ContractSnapshot.assertMatches(entry, recording, 1);
        assertEquals(first, tokenOf(recording.request(1)));
        assertEquals(1, fetches.get());
        assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
        assertEquals(second, store.get(ContractFeign.APP).getAccessToken());
    }

    /**
     * 代理的 equals / hashCode / toString 以 Feign 默认代理的语义为准：同一 target 相等且 hash 相同，不同 target 不等且保持对称
     */
    @Test
    void keepsFeignProxyObjectMethodSemantics() {
        Fixture fixture = new Fixture();
        ContractApi sameTarget = ContractFeign.create(ContractApi.class, fixture.config, fixture.manager,
                fixture.recording);
        ContractApi otherUrl = ContractFeign.builder(fixture.config, fixture.recording)
                .invocationHandlerFactory(new TokenRetryInvocationHandlerFactory(fixture.manager, fixture.config))
                .target(ContractApi.class, RecordingClient.TARGET_URL + "/other");
        ContractApi plain = ContractFeign.builder(fixture.config, fixture.recording)
                .target(ContractApi.class, RecordingClient.TARGET_URL);

        assertTrue(fixture.api.equals(fixture.api));
        assertTrue(fixture.api.equals(sameTarget));
        assertTrue(sameTarget.equals(fixture.api));
        assertNotEquals(fixture.api, otherUrl);
        assertNotEquals(otherUrl, fixture.api);
        assertNotEquals(fixture.api, plain);
        assertNotEquals(plain, fixture.api);
        assertFalse(fixture.api.equals(new Object()));
        assertEquals(fixture.api.hashCode(), sameTarget.hashCode());
        assertEquals(sameTarget.hashCode(), plain.hashCode());
        assertNotEquals(fixture.api.hashCode(), otherUrl.hashCode());
        assertEquals(plain.toString(), fixture.api.toString());
        assertEquals(0, fixture.recording.requestCount());
    }

    private static final class Fixture {

        private final RecordingClient recording = new RecordingClient();

        private final CountingFetcher fetcher = new CountingFetcher();

        private final RecordingStore store = new RecordingStore();

        private final QywxConfig config = ContractFeign.config(false);

        private final TokenManager manager;

        private final ContractApi api;

        private Fixture() {
            this.manager = ContractFeign.tokenManager(fetcher, config, store);
            this.api = ContractFeign.create(ContractApi.class, config, manager, recording);
        }
    }

    /**
     * 断言一次带令牌的业务请求：GET、路径、查询参数顺序、应用名请求头
     */
    private static void assertTokenRequest(Request request, String token) {
        assertEquals("GET", request.method());
        assertEquals("/get_api_domain_ip", pathOf(request));
        assertEquals("app=" + ContractFeign.APP + "&access_token=" + token, queryOf(request));
        assertEquals(ContractFeign.APP, ContractSnapshot.headerValues(request.headers()).get("app"));
    }

    private static String queryOf(Request request) {
        int index = request.url().indexOf('?');
        assertTrue(index > 0, "请求缺少查询参数：" + request.url());
        return request.url().substring(index + 1);
    }

    private static String pathOf(Request request) {
        String url = request.url();
        int index = url.indexOf('?');
        return url.substring(RecordingClient.TARGET_URL.length(), index > 0 ? index : url.length());
    }

    private static String tokenOf(Request request) {
        Matcher matcher = TOKEN.matcher(queryOf(request));
        assertTrue(matcher.find(), "请求缺少 access_token：" + request.url());
        return matcher.group(1);
    }

    /**
     * 取契约请求里 access_token 的序号（快照把令牌归一成带序号的占位形式）
     */
    private static String tokenIndexOf(JsonNode request) {
        String query = request.path("query").asText();
        Matcher matcher = TOKEN_INDEX.matcher(query);
        assertTrue(matcher.find(), "契约请求缺少 access_token 序号：" + query);
        return matcher.group(1);
    }

    /**
     * 构造一个 client 方法固定抛出指定异常的代理，用来验证重试 handler 对异常形态的处理
     */
    private static ContractApi failingProxy(RecordingStore store, Throwable failure, AtomicInteger invokes) {
        try {
            Method method = ContractApi.class.getMethod("apiDomainIp", String.class);
            MethodHandler handler = argv -> {
                invokes.incrementAndGet();
                throw failure;
            };
            QywxConfig config = ContractFeign.config(false);
            InvocationHandler retryHandler = new TokenRetryInvocationHandlerFactory(
                    new TokenManager(config, store, new CountingFetcher()), config)
                    .create(new Target.HardCodedTarget<>(ContractApi.class, RecordingClient.TARGET_URL),
                            Collections.singletonMap(method, handler));
            return (ContractApi) Proxy.newProxyInstance(ContractApi.class.getClassLoader(),
                    new Class<?>[] {ContractApi.class}, retryHandler);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }
}
