package com.github.shuaidd.client.support;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.client.decoder.WeChatMediaDecoder;
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
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenManager;
import feign.FeignException;
import feign.Headers;
import feign.InvocationHandlerFactory.MethodHandler;
import feign.Param;
import feign.Request;
import feign.RequestLine;
import feign.Response;
import feign.Target;
import feign.Util;
import feign.codec.DecodeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link TokenRetryInvocationHandlerFactory} 的单元测试：令牌失效重试一次、其它错误不重试、无应用名参数不重试、二次失败原样抛、契约里的 token-expired 场景回放、代理的 equals/hashCode/toString 语义、下载调用期间打开 {@link DownloadContext} 作用域并在调用结束后（含失败与令牌失效重试）恢复之前的值
 *
 * @author ddshuai
 * date 2026-11-26 03:45
 **/
class TokenRetryInvocationHandlerFactoryTest {

    private static final Request REQUEST = Request.create(Request.HttpMethod.GET,
            RecordingClient.TARGET_URL + "/get_api_domain_ip", Collections.emptyMap(), null, Util.UTF_8);

    private static final Pattern TOKEN = Pattern.compile("access_token=([^&]+)");

    private static final Pattern TOKEN_INDEX = Pattern.compile("access_token=\\D*(\\d+)");

    private static final byte[] CONTENT = "CONTRACT-DOWNLOAD-CONTENT".getBytes(Util.UTF_8);

    @TempDir
    Path tempDir;

    @AfterEach
    void clearDownloadContext() {
        DownloadContext.clear();
    }

    /**
     * 下载接口夹具：落盘目标参数 downloadFilePath 不在模板与请求头里引用，只经调用期间的作用域传给 decoder
     */
    interface DownloadApi extends CommonClient {

        /**
         * 素材下载到本地文件
         *
         * @param mediaId         媒体文件 ID
         * @param downloadFilePath 落盘目标目录或文件
         * @param app             应用名
         * @return 落盘后的文件
         */
        @RequestLine("GET /media/get?media_id={media_id}&app={app}")
        @Headers(HEAD)
        File downloadFile(@Param("media_id") String mediaId,
                          @Param(DownloadContext.DOWNLOAD_FILE_PATH) String downloadFilePath,
                          @Param("app") String app);

        /**
         * 没有落盘目标参数的普通接口
         *
         * @param app 应用名
         * @return 通用响应
         */
        @RequestLine("GET /get_api_domain_ip?app={app}")
        @Headers(HEAD)
        BaseResponse ping(@Param("app") String app);
    }

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

    /**
     * 落盘目标经调用期间的作用域传给 decoder：请求里不带 downloadFilePath（查询参数、请求头、表单体都没有），文件写到目标目录
     */
    @Test
    void opensDownloadScopeDuringInvocation() throws IOException {
        DownloadFixture fixture = new DownloadFixture();
        fixture.recording.respond(200, CONTENT, downloadHeaders("attachment; filename=\"scope.bin\""));

        File result = fixture.api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP);

        assertEquals(tempDir.resolve("scope.bin").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
        assertEquals(Collections.singletonList(tempDir), fixture.seen);
        assertNull(DownloadContext.get(), "调用结束后恢复为之前的值（未设置）");
        assertEquals(1, fixture.recording.requestCount());
        assertDownloadRequest(fixture.recording.request(0), "tok-1");
    }

    /**
     * 调用前已手动设置了落盘目标：调用期间用接口参数，结束后恢复为之前的值
     */
    @Test
    void restoresPreviousTargetAfterInvocation() {
        DownloadFixture fixture = new DownloadFixture();
        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);
        fixture.recording.respond(200, CONTENT, downloadHeaders("attachment; filename=\"restore.bin\""));

        File result = fixture.api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP);

        assertEquals(tempDir.resolve("restore.bin").toFile(), result);
        assertEquals(Collections.singletonList(tempDir), fixture.seen);
        assertEquals(previous, DownloadContext.get());
    }

    /**
     * 调用抛出非业务异常：作用域照常关闭并恢复之前的值，且不触发令牌失效重试
     */
    @Test
    void restoresPreviousTargetWhenInvocationFails() {
        RecordingStore store = new RecordingStore();
        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);
        List<Path> seen = new ArrayList<>();
        AtomicInteger invokes = new AtomicInteger();
        RuntimeException failure = new RuntimeException("boom");
        Method method = methodOf(DownloadApi.class, "downloadFile", String.class, String.class, String.class);
        MethodHandler handler = argv -> {
            invokes.incrementAndGet();
            seen.add(DownloadContext.get());
            throw failure;
        };
        DownloadApi api = proxy(DownloadApi.class, store, method, handler);

        assertSame(failure, assertThrows(RuntimeException.class,
                () -> api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP)));

        assertEquals(1, invokes.get());
        assertEquals(Collections.singletonList(tempDir), seen);
        assertEquals(previous, DownloadContext.get());
        assertTrue(store.evictions().isEmpty());
    }

    /**
     * 落盘目标实参为 null 或空白时不打开作用域：沿用 DownloadContext 当前的值，调用结束后也不改动它
     */
    @ParameterizedTest(name = "[{index}] target=[{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void blankTargetDoesNotOpenScope(String target) {
        RecordingStore store = new RecordingStore();
        List<Path> seen = new ArrayList<>();
        Method method = methodOf(DownloadApi.class, "downloadFile", String.class, String.class, String.class);
        MethodHandler handler = argv -> {
            seen.add(DownloadContext.get());
            return null;
        };
        DownloadApi api = proxy(DownloadApi.class, store, method, handler);

        api.downloadFile("v_media_id", target, ContractFeign.APP);

        assertEquals(Collections.<Path>singletonList(null), seen);
        assertNull(DownloadContext.get());

        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);
        api.downloadFile("v_media_id", target, ContractFeign.APP);

        assertEquals(Arrays.asList((Path) null, previous), seen);
        assertEquals(previous, DownloadContext.get());
    }

    /**
     * 方法没有落盘目标参数：不打开作用域，DownloadContext 保持调用前的值
     */
    @Test
    void methodWithoutDownloadParamLeavesTargetUntouched() {
        DownloadFixture fixture = new DownloadFixture();
        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);

        assertNotNull(fixture.api.ping(ContractFeign.APP));

        assertEquals(Collections.singletonList(previous), fixture.seen);
        assertEquals(previous, DownloadContext.get());
        assertEquals(1, fixture.recording.requestCount());
    }

    /**
     * 令牌失效后重试仍失败：两次调用都在作用域内，结束后恢复之前的值，令牌缓存被清除一次
     */
    @Test
    void restoresTargetWhenRetryFailsAgain() {
        RecordingStore store = new RecordingStore();
        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);
        List<Path> seen = new ArrayList<>();
        WeChatException failure = new WeChatException("expired", ErrorCode.ERROR_CODE_42001);
        Method method = methodOf(DownloadApi.class, "downloadFile", String.class, String.class, String.class);
        MethodHandler handler = argv -> {
            seen.add(DownloadContext.get());
            throw failure;
        };
        DownloadApi api = proxy(DownloadApi.class, store, method, handler);

        assertSame(failure, assertThrows(WeChatException.class,
                () -> api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP)));

        assertEquals(Arrays.asList(tempDir, tempDir), seen);
        assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
        assertEquals(previous, DownloadContext.get());
    }

    /**
     * 端到端回放令牌失效后的下载重试：首个请求用缓存里的旧令牌并返回 error-code 42001，清缓存取新令牌后重试，
     * 两次请求都在作用域内发出，最终文件落盘、作用域恢复
     */
    @Test
    void retriesDownloadOnceWhenTokenExpired() throws IOException {
        DownloadFixture fixture = new DownloadFixture();
        fixture.recording.respond(200, new byte[0], errorHeaders("42001", "expired"));
        fixture.recording.respond(200, CONTENT, downloadHeaders("attachment; filename=\"retry.bin\""));
        fixture.store.put(ContractFeign.APP, TokenEntry.of("stale", 7200, System.currentTimeMillis()));
        Path previous = tempDir.resolve("previous");
        DownloadContext.set(previous);

        File result = fixture.api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP);

        assertEquals(tempDir.resolve("retry.bin").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
        assertEquals(Arrays.asList(tempDir, tempDir), fixture.seen);
        assertEquals(2, fixture.recording.requestCount());
        assertDownloadRequest(fixture.recording.request(0), "stale");
        assertDownloadRequest(fixture.recording.request(1), "tok-1");
        assertEquals(Collections.singletonList(ContractFeign.APP), fixture.store.evictions());
        assertEquals(1, fixture.fetcher.calls());
        assertEquals(Collections.singletonList("tok-1"), fixture.fetcher.issued());
        assertEquals("tok-1", fixture.store.get(ContractFeign.APP).getAccessToken());
        assertEquals(1, entryCount());
        assertEquals(previous, DownloadContext.get());
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
        MethodHandler handler = argv -> {
            invokes.incrementAndGet();
            throw failure;
        };
        return proxy(ContractApi.class, store, methodOf(ContractApi.class, "apiDomainIp", String.class), handler);
    }

    /**
     * 下载场景的装配夹具：经 Feign 代理调用 {@link DownloadApi}，HTTP client 在收到请求时记下当时的落盘目标
     */
    private static final class DownloadFixture {

        private final List<Path> seen = new ArrayList<>();

        private final RecordingClient recording;

        private final CountingFetcher fetcher = new CountingFetcher();

        private final RecordingStore store = new RecordingStore();

        private final QywxConfig config = ContractFeign.config(false);

        private final TokenManager manager;

        private final DownloadApi api;

        private DownloadFixture() {
            this.recording = new RecordingClient() {
                @Override
                public Response execute(Request request, Request.Options options) throws IOException {
                    seen.add(DownloadContext.get());
                    return super.execute(request, options);
                }
            };
            this.manager = ContractFeign.tokenManager(fetcher, config, store);
            this.api = ContractFeign.create(DownloadApi.class, new WeChatMediaDecoder(),
                    JacksonJsonEncoder.indented(), config, manager, recording);
        }
    }

    /**
     * 构造一个 client 方法由调用方指定行为的代理（装配重试 handler，不装配拦截器）
     */
    private static <T> T proxy(Class<T> type, RecordingStore store, Method method, MethodHandler handler) {
        QywxConfig config = ContractFeign.config(false);
        InvocationHandler retryHandler = new TokenRetryInvocationHandlerFactory(
                new TokenManager(config, store, new CountingFetcher()), config)
                .create(new Target.HardCodedTarget<>(type, RecordingClient.TARGET_URL),
                        Collections.singletonMap(method, handler));
        return type.cast(Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[] {type}, retryHandler));
    }

    private static Method methodOf(Class<?> type, String name, Class<?>... parameterTypes) {
        try {
            return type.getMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * 断言一次素材下载请求：GET、路径、查询参数顺序（含指定令牌）、应用名请求头；落盘目标不出现在请求里
     */
    private static void assertDownloadRequest(Request request, String token) {
        assertEquals("GET", request.method());
        assertEquals("/media/get", pathOf(request));
        assertEquals("media_id=v_media_id&app=" + ContractFeign.APP + "&access_token=" + token, queryOf(request));
        assertEquals(ContractFeign.APP, ContractSnapshot.headerValues(request.headers()).get("app"));
        assertNull(request.body(), "落盘目标不随请求发送，不能被 Feign 当成表单体");
        assertNull(ContractSnapshot.headerValues(request.headers()).get(DownloadContext.DOWNLOAD_FILE_PATH));
    }

    private static Map<String, Collection<String>> downloadHeaders(String disposition) {
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put(FileDownload.CONTENT_DISPOSITION, Collections.singletonList(disposition));
        headers.put(FileDownload.CONTENT_TYPE, Collections.singletonList("application/octet-stream"));
        return headers;
    }

    private static Map<String, Collection<String>> errorHeaders(String code, String message) {
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put(WeChatMediaDecoder.ERROR_CODE, Collections.singletonList(code));
        headers.put(WeChatMediaDecoder.ERROR_MSG, Collections.singletonList(message));
        return headers;
    }

    /**
     * 目标目录里的文件个数
     */
    private long entryCount() throws IOException {
        try (Stream<Path> entries = Files.list(tempDir)) {
            return entries.count();
        }
    }
}
