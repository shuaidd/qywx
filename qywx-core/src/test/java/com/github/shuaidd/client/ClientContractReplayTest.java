package com.github.shuaidd.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shuaidd.client.decoder.WeChatMediaDecoder;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenManager;
import feign.codec.DecodeException;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 逐条回放 5.2.0 的全部 Feign 客户端快照，验证原生 OpenFeign 报文与令牌行为
 * <p>
 * 正常条目预热一次真实的 {@code /gettoken} 请求后只与业务请求比对；三个非默认条目、
 * 下载响应与已经记录的迁移差异在本类中显式处理，防止快照兼容性被静默削弱。
 *
 * @author ddshuai
 * date 2026-11-26 08:20
 **/
class ClientContractReplayTest {

    private static final List<String> CLIENTS = Arrays.asList(
            "AddressBookClient", "AdvancedFeatureClient", "AuthClient", "EfficiencyToolClient",
            "ElectronicInvoiceClient", "EnterpriseEmailClient", "EnterpriseInterConnectionClient",
            "ExternalContactClient", "FrontEndToolsClient", "GovernmentCommunicationClient", "HrClient",
            "LinkedCorpClient", "MeetingAdvancedClient", "MeetingClient", "MessageClient", "PaymentClient",
            "PaymentUploadClient", "SchoolClient", "SecurityClient", "ServiceProviderClient",
            "WeChatCallCenterClient", "WeChatClient", "WeChatMediaClient", "WechatDocClient",
            "WechatSmartSheetClient", "WorkApplicationClient", "WorkOaClient");

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("entries")
    void replaysAllContracts(String id, JsonNode entry, @TempDir Path tempDir) throws Exception {
        RecordingClient recording = new RecordingClient();
        RecordingStore store = new RecordingStore();
        String scenario = entry.path("scenario").asText("default");
        QywxConfig config = ContractFeign.config("debug-mode".equals(scenario));
        config.setTokenStore(store);

        AtomicReference<WeChatClient> weChat = new AtomicReference<WeChatClient>();
        TokenManager manager = ContractFeign.tokenManager(
                (corpId, secret) -> weChat.get().getAccessToken(corpId, secret), config, store);
        WeChatClient tokenClient = ContractFeign.create(WeChatClient.class, config, manager, recording);
        weChat.set(tokenClient);
        Object api = create(entry.path("client").asText(), config, manager, recording);

        if ("ServiceProviderClient".equals(entry.path("client").asText())) {
            Object result = ContractFeign.call(api, entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertNotNull(result);
            assertEquals(0, recording.requestCount() - entry.path("requests").size(), "服务商接口不应取 token");
            return;
        }

        if ("AddressBookClient.authSucc".equals(id)) {
            recording.respond(200, ContractFeign.tokenBody("tok-1"));
            Object result = ContractFeign.call(api, entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertNotNull(result);
            return;
        }

        if ("WeChatClient.getAccessToken".equals(id)) {
            recording.respond(200, ContractFeign.tokenBody("tok-1"));
            Object result = ContractFeign.call(api, entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertEquals("tok-1", ((AccessTokenResponse) result).getAccessToken());
            return;
        }

        if ("token-expired".equals(scenario)) {
            store.put(ContractFeign.APP, TokenEntry.of("tok-1", 7200, System.currentTimeMillis()));
            recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"access_token expired\"}");
            recording.respond(200, ContractFeign.tokenBody("tok-2"));
            Object result = ContractFeign.call(api, entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertNotNull(result);
            assertEquals(Arrays.asList(ContractFeign.APP), store.evictions());
            assertEquals("tok-2", store.get(ContractFeign.APP).getAccessToken());
            return;
        }

        if ("MeetingAdvancedClient.getRoomsInventory".equals(id)) {
            recording.respond(200, ContractFeign.tokenBody("tok-1"));
            manager.getAccessToken(ContractFeign.APP);
            Object result = ContractFeign.call(api, entry);
            assertNotNull(result);
            assertEquals(2, recording.requestCount());
            assertEquals("POST", recording.request(1).method());
            assertEquals("http://contract.invalid/cgi-bin/meeting/rooms/get_inventory?app=contract&access_token=tok-1",
                    recording.request(1).url());
            assertEquals("contract", ContractSnapshot.headerValues(recording.request(1).headers()).get("app"));
            assertEquals(JacksonJsonEncoder.FORM_CONTENT_TYPE,
                    ContractSnapshot.headerValues(recording.request(1).headers()).get("content-type"));
            assertEquals("{ }", new String(recording.request(1).body(), java.nio.charset.StandardCharsets.UTF_8));
            return;
        }

        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        assertEquals("tok-1", manager.getAccessToken(ContractFeign.APP));
        assertEquals(1, recording.requestCount());

        if (id.startsWith("WeChatMediaClient.download") && "download-error".equals(scenario)) {
            recording.respond(200, ContractFeign.DOWNLOAD_CONTENT,
                    ContractFeign.downloadErrorHeaders("40007", "invalid media id"));
            AssertionError error = assertThrows(AssertionError.class, () -> ContractFeign.call(api, entry));
            DecodeException decode = ContractFeign.findCause(error, DecodeException.class);
            WeChatException weChatException = ContractFeign.findCause(error, WeChatException.class);
            assertNotNull(decode);
            assertNotNull(weChatException);
            assertEquals("40007", weChatException.getCode());
            assertEquals("invalid media id", weChatException.getMessage());
            ContractSnapshot.assertMatches(entry, recording, 1);
            return;
        }

        if (id.startsWith("WeChatMediaClient.download")) {
            recording.respond(200, ContractFeign.DOWNLOAD_CONTENT, ContractFeign.downloadHeaders());
            replayDownload(id, api, entry, recording, tempDir);
            return;
        }

        Object result = ContractFeign.call(api, entry);
        JsonNode expected = "FrontEndToolsClient.getAppTicket".equals(id) ? frontEndTicketExpectation(entry) : entry;
        ContractSnapshot.assertMatches(expected, recording, 1);
        assertNotNull(result);
    }

    static Stream<Arguments> entries() {
        List<Arguments> arguments = new ArrayList<Arguments>();
        for (String client : CLIENTS) {
            for (JsonNode entry : ContractSnapshot.entries(client)) {
                arguments.add(Arguments.of(entry.path("id").asText(), entry));
            }
        }
        assertEquals(27, CLIENTS.size(), "5.2.0 client 数量");
        assertEquals(577, arguments.size(), "5.2.0 契约条目数量");
        assertEquals(574, arguments.stream().filter(argument -> "default".equals(
                ((JsonNode) argument.get()[1]).path("scenario").asText("default"))).count(), "默认映射数量");
        return arguments.stream();
    }

    @SuppressWarnings("unchecked")
    private static Object create(String client, QywxConfig config, TokenManager manager, RecordingClient recording)
            throws ClassNotFoundException {
        Class<Object> type = (Class<Object>) Class.forName("com.github.shuaidd.client." + client);
        if ("ServiceProviderClient".equals(client)) {
            return ContractFeign.createWithoutInterceptor(type, JacksonJsonEncoder.compact(), config, manager, recording);
        }
        if ("WeChatMediaClient".equals(client)) {
            return ContractFeign.create(type, new WeChatMediaDecoder(), JacksonJsonEncoder.indented(),
                    config, manager, recording);
        }
        return ContractFeign.create(type, config, manager, recording);
    }

    private static void replayDownload(String id, Object api, JsonNode entry, RecordingClient recording, Path tempDir)
            throws Exception {
        if (id.endsWith("download") || id.endsWith("downloadJsMedia")) {
            FileDownload result = (FileDownload) ContractFeign.call(api, entry);
            assertArrayEquals(ContractFeign.DOWNLOAD_CONTENT, result.getContent());
            assertEquals("contract.bin", result.getFileName());
            ContractSnapshot.assertMatches(entry, recording, 1);
            return;
        }

        File result = (File) ContractFeign.call(api, entry, 1, tempDir.toString());
        assertEquals(tempDir.resolve("contract.bin").toFile(), result);
        assertArrayEquals(ContractFeign.DOWNLOAD_CONTENT, Files.readAllBytes(result.toPath()));
        assertNull(DownloadContext.get());
        ContractSnapshot.assertMatchesWithoutDownloadFilePath(entry, recording, 1);
    }

    private static JsonNode frontEndTicketExpectation(JsonNode entry) {
        ObjectNode expected = entry.deepCopy();
        ((ObjectNode) expected.path("requests").path(0)).put("query",
                "access_token=v_access_token&access_token={{TOKEN_1}}&app=contract");
        return expected;
    }
}
