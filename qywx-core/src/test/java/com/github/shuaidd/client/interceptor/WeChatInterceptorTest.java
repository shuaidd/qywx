package com.github.shuaidd.client.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractApi;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.CountingFetcher;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.token.TokenManager;
import feign.Request;
import feign.RequestTemplate;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link WeChatInterceptor} 的单元测试：gettoken 不注入令牌、按应用名取号并追加到 query、调试参数、缺应用名报错、日志脱敏
 *
 * @author ddshuai
 * date 2026-11-26 03:25
 **/
class WeChatInterceptorTest {

    /**
     * 每个用例一套独立的装配
     */
    private static final class Fixture {

        private final RecordingClient recording = new RecordingClient();

        private final CountingFetcher fetcher = new CountingFetcher();

        private final RecordingStore store = new RecordingStore();

        private final QywxConfig config;

        private final TokenManager manager;

        private final ContractApi api;

        private final WeChatInterceptor interceptor;

        Fixture(boolean debugMode) {
            config = ContractFeign.config(debugMode);
            manager = ContractFeign.tokenManager(fetcher, config, store);
            api = ContractFeign.create(ContractApi.class, config, manager, recording);
            interceptor = new WeChatInterceptor(manager, config);
        }
    }

    /**
     * 获取令牌的请求不注入 access_token，也不触发取号
     */
    @Test
    void skipsTokenInjectionForGetToken() {
        Fixture fixture = new Fixture(false);
        JsonNode entry = ContractSnapshot.entry("WeChatClient.getAccessToken");

        Object result = ContractFeign.call(fixture.api, entry);

        assertNotNull(result);
        ContractSnapshot.assertMatches(entry, fixture.recording);
        assertEquals(0, fixture.fetcher.calls());
        assertTrue(fixture.store.puts().isEmpty());
    }

    /**
     * access_token 追加在 app 之后，令牌来自取号器并写入缓存
     */
    @Test
    void appendsTokenAfterAppQuery() {
        Fixture fixture = new Fixture(false);
        JsonNode entry = ContractSnapshot.entry("WeChatClient.callBackIp");

        Object result = ContractFeign.call(fixture.api, entry);

        assertNotNull(result);
        Request request = fixture.recording.request(0);
        assertEquals(RecordingClient.TARGET_URL + "/getcallbackip?app=" + ContractFeign.APP
                + "&access_token=" + fixture.fetcher.issued().get(0), request.url());
        assertEquals(Collections.singletonList(ContractFeign.APP), fixture.store.puts());
        assertEquals(1, fixture.fetcher.calls());
        ContractSnapshot.assertMatches(entry, fixture.recording);
    }

    /**
     * 请求头 app 缺失或为空串时抛出 RuntimeException，且不发请求、不取号
     */
    @Test
    void throwsWhenApplicationNameIsMissing() {
        Fixture fixture = new Fixture(false);
        for (String app : new String[] {null, ""}) {
            RuntimeException exception = assertThrows(RuntimeException.class, () -> fixture.api.callBackIp(app));
            assertEquals(RuntimeException.class, exception.getClass());
            assertEquals("请求未传递应用名，无法调起接口", exception.getMessage());
        }
        assertEquals(0, fixture.recording.requestCount());
        assertEquals(0, fixture.fetcher.calls());

        RequestTemplate template = new RequestTemplate().uri("/getcallbackip");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> fixture.interceptor.apply(template));
        assertEquals("请求未传递应用名，无法调起接口", exception.getMessage());
    }

    /**
     * Feign 的 resolve 返回新模板，拦截器作用的必须是返回值；解析后的 url 已含 query
     */
    @Test
    void resolvesUsingReturnedTemplate() {
        Fixture fixture = new Fixture(false);
        RequestTemplate template = new RequestTemplate().uri("/get_api_domain_ip?app={app}")
                .header(CommonClient.HEAD_KEY, "{app}");

        RequestTemplate resolved = template.resolve(Collections.singletonMap("app", ContractFeign.APP));
        fixture.interceptor.apply(resolved);

        assertTrue(resolved.url().startsWith("/get_api_domain_ip?app=" + ContractFeign.APP + "&access_token="));
        assertFalse(template.url().contains(CommonClient.ACCESS_TOKEN));
        assertEquals(Collections.singletonList(ContractFeign.APP), fixture.store.puts());
    }

    /**
     * 调试模式下在 access_token 之后追加 debug=1
     */
    @Test
    void appendsDebugFlagInDebugMode() {
        Fixture fixture = new Fixture(true);

        Object result = ContractFeign.call(fixture.api, ContractSnapshot.entry("WeChatClient.callBackIp"));

        assertNotNull(result);
        assertTrue(fixture.recording.request(0).url().matches(".*access_token=[^&]+&debug=1$"));
    }

    /**
     * 调试模式的契约回放
     */
    @Test
    void replaysDebugModeContract() {
        Fixture fixture = new Fixture(true);
        JsonNode entry = ContractSnapshot.entry("WeChatClient.callBackIp#debug-mode");

        Object result = ContractFeign.call(fixture.api, entry);

        assertNotNull(result);
        ContractSnapshot.assertMatches(entry, fixture.recording);
    }

    /**
     * 日志脱敏：access_token 与 corpsecret 的值替换为 ***，其余参数不动
     */
    @Test
    void masksTokenAndSecretInLoggedUrl() {
        assertEquals("http://x/cgi-bin/get_api_domain_ip?access_token=***", WeChatInterceptor.mask(
                "http://x/cgi-bin/get_api_domain_ip?access_token=dummy-token"));
        assertEquals("http://x/cgi-bin/gettoken?corpid=c&corpsecret=***", WeChatInterceptor.mask(
                "http://x/cgi-bin/gettoken?corpid=c&corpsecret=dummy-secret"));
        assertEquals("http://x/cgi-bin/a?access_token=***&corpsecret=***&x=1", WeChatInterceptor.mask(
                "http://x/cgi-bin/a?access_token=dummy-token&corp" + "secret=dummy-secret&x=1"));
        assertEquals("http://x/cgi-bin/a?x=1", WeChatInterceptor.mask("http://x/cgi-bin/a?x=1"));
        assertNull(WeChatInterceptor.mask(null));
    }
}
