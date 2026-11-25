package com.github.shuaidd.client.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractApi;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.CountingFetcher;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.resquest.addressbook.BatchDeleteUserRequest;
import feign.Request;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link JacksonJsonEncoder} 的单元测试：按 5.2.0 契约快照回放缩进 JSON 与紧凑 JSON、无字段请求类、Content-Type 覆盖、序列化失败的包装
 *
 * @author ddshuai
 * date 2026-11-26 03:44
 **/
class JacksonJsonEncoderTest {

    /**
     * 业务 client 的缩进 JSON：嵌套对象、数组、空数组逐字节回放契约（行尾归一后）
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"MeetingClient.getInvitees", "AddressBookClient.batchDelete", "AddressBookClient.createUser"})
    void replaysIndentedJsonContracts(String id) {
        Fixture fixture = new Fixture(JacksonJsonEncoder.indented());
        JsonNode entry = ContractSnapshot.entry(id);

        assertNotNull(ContractFeign.call(fixture.api, entry));

        ContractSnapshot.assertMatches(entry, fixture.recording);
        assertEquals(1, fixture.fetcher.calls());
    }

    /**
     * ServiceProviderClient：不传应用名、不取号，请求体为紧凑 JSON，Content-Type 为 application/json
     */
    @Test
    void encodesServiceProviderTokenAsCompactJson() {
        RecordingClient recording = new RecordingClient();
        CountingFetcher fetcher = new CountingFetcher();
        QywxConfig config = ContractFeign.config(false);
        ContractApi api = ContractFeign.createWithoutInterceptor(ContractApi.class, JacksonJsonEncoder.compact(),
                config, ContractFeign.tokenManager(fetcher, config), recording);
        JsonNode entry = ContractSnapshot.entry("ServiceProviderClient.getProviderToken");

        assertNotNull(ContractFeign.call(api, entry));

        ContractSnapshot.assertMatches(entry, recording);
        assertEquals(0, fetcher.calls(), "ServiceProviderClient 不传应用名，不应取号");
        assertEquals(entry.path("requests").get(0).path("body").asText(),
                new String(recording.request(0).body(), StandardCharsets.UTF_8));
        assertEquals(JacksonJsonEncoder.JSON_CONTENT_TYPE,
                ContractSnapshot.headerValues(recording.request(0).headers()).get("content-type"));
    }

    /**
     * 无字段请求类：5.2.0 因 FAIL_ON_EMPTY_BEANS 抛 EncodeException，core 应能发出空对象（偏差 #8）
     */
    @Test
    void sendsEmptyObjectWhenRequestHasNoFields() {
        Fixture fixture = new Fixture(JacksonJsonEncoder.indented());
        JsonNode entry = ContractSnapshot.entry("MeetingAdvancedClient.getRoomsInventory");

        assertNotNull(ContractFeign.call(fixture.api, entry));

        assertEquals(1, fixture.recording.requestCount());
        Request request = fixture.recording.request(0);
        assertEquals("POST", request.method());
        assertTrue(request.url().startsWith(RecordingClient.TARGET_URL + "/meeting/rooms/get_inventory?app="
                + ContractFeign.APP + "&access_token="), "请求地址：" + request.url());
        Map<String, String> headers = ContractSnapshot.headerValues(request.headers());
        assertEquals(ContractFeign.APP, headers.get("app"));
        assertEquals(JacksonJsonEncoder.FORM_CONTENT_TYPE, headers.get("content-type"));
        assertEquals("{ }", new String(request.body(), StandardCharsets.UTF_8));
        assertEquals(1, fixture.fetcher.calls());
    }

    /**
     * 接口上已声明 Content-Type：encoder 写入的头覆盖它，且只保留一个值
     */
    @Test
    void overridesExistingContentTypeHeader() {
        Fixture fixture = new Fixture(JacksonJsonEncoder.indented());
        JsonNode entry = ContractSnapshot.entry("AddressBookClient.batchDelete");
        Object[] args = ContractSnapshot.args(entry);

        assertNotNull(fixture.api.batchDeleteWithContentType((BatchDeleteUserRequest) args[0], (String) args[1]));

        ContractSnapshot.assertMatches(entry, fixture.recording);
        assertEquals(JacksonJsonEncoder.FORM_CONTENT_TYPE,
                ContractSnapshot.headerValues(fixture.recording.request(0).headers()).get("content-type"));
    }

    /**
     * 序列化失败：包成 EncodeException，原始 Jackson 异常与业务异常都在 cause 链上，模板不留半成品
     */
    @Test
    void wrapsSerializationFailureInEncodeException() {
        RequestTemplate template = new RequestTemplate();

        EncodeException thrown = assertThrows(EncodeException.class,
                () -> JacksonJsonEncoder.indented().encode(new Broken(), Broken.class, template));

        assertNotNull(ContractFeign.findCause(thrown, JsonProcessingException.class));
        IllegalStateException cause = ContractFeign.findCause(thrown, IllegalStateException.class);
        assertNotNull(cause);
        assertEquals("boom", cause.getMessage());
        assertNull(template.body());
        assertTrue(template.headers().isEmpty());
    }

    private static final class Fixture {

        private final RecordingClient recording = new RecordingClient();

        private final CountingFetcher fetcher = new CountingFetcher();

        private final RecordingStore store = new RecordingStore();

        private final QywxConfig config = ContractFeign.config(false);

        private final ContractApi api;

        private Fixture(JacksonJsonEncoder encoder) {
            this.api = ContractFeign.create(ContractApi.class, encoder, config,
                    ContractFeign.tokenManager(fetcher, config, store), recording);
        }
    }

    /**
     * getter 抛异常的请求对象，用来验证序列化失败的包装
     */
    private static final class Broken {

        public String getValue() {
            throw new IllegalStateException("boom");
        }
    }
}
