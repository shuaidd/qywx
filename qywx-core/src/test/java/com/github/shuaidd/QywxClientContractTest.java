package com.github.shuaidd;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.token.TokenEntry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 QywxClient 入口装配后的契约回放单元测试：经正式装配路径逐条回放 5.2.0 的 WeChatClient 快照
 * <p>
 * P1.4/P1.5 用 {@code ContractFeign} 手工装配 client 来回放；这里改用 {@code QywxClient.create(config, recording)}，
 * 证明入口装配（契约、超时、encoder/decoder、拦截器、重试 handler）产出的报文与 5.2.0 一致。
 *
 * @author ddshuai
 * date 2026-11-26 06:45
 **/
public class QywxClientContractTest {

    private static final String GET_TOKEN_URL =
            "http://contract.invalid/cgi-bin/gettoken?corpid=corp-contract&corpsecret=secret-contract";

    private static final Pattern TOKEN_INDEX = Pattern.compile("access_token=\\{\\{TOKEN_(\\d+)\\}\\}");

    /**
     * 快照里的全部 WeChatClient 契约逐条回放：默认场景、调试模式与令牌失效重试
     */
    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("entries")
    void replaysWeChatClientContracts(String id, JsonNode entry) {
        RecordingClient recording = new RecordingClient();
        RecordingStore store = new RecordingStore();
        String scenario = entry.path("scenario").asText("default");
        QywxConfig config = ContractFeign.config("debug-mode".equals(scenario));
        config.setTokenStore(store);
        QywxClient client = QywxClient.create(config, recording);

        if ("WeChatClient.getAccessToken".equals(id)) {
            recording.respond(200, ContractFeign.tokenBody("tok-1"));
            Object result = ContractFeign.call(client.weChatClient(), entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertEquals("tok-1", ((AccessTokenResponse) result).getAccessToken());
            return;
        }

        if ("token-expired".equals(scenario)) {
            //预置旧令牌：首个业务请求返回 42001，重试 handler 清缓存、重新取号后重发
            String expired = tokenOf(entry.path("requests").get(0));
            String refreshed = tokenOf(entry.path("requests").get(2));
            store.put(ContractFeign.APP, TokenEntry.of(expired, 7200, System.currentTimeMillis()));
            recording.respond(200, "{\"errcode\":42001,\"errmsg\":\"access_token expired\"}");
            recording.respond(200, ContractFeign.tokenBody(refreshed));
            Object result = ContractFeign.call(client.weChatClient(), entry);
            ContractSnapshot.assertMatches(entry, recording);
            assertNotNull(result);
            assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
            assertEquals(refreshed, store.get(ContractFeign.APP).getAccessToken());
            return;
        }

        //其余场景：先取号预热缓存（第 0 个请求是 gettoken），再回放业务请求
        String token = tokenOf(entry.path("requests").get(0));
        recording.respond(200, ContractFeign.tokenBody(token));
        assertEquals(token, client.tokenService().getAccessToken(ContractFeign.APP));
        assertEquals(1, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Object result = ContractFeign.call(client.weChatClient(), entry);
        ContractSnapshot.assertMatches(entry, recording, 1);
        assertNotNull(result);
    }

    /**
     * 枚举 WeChatClient 的全部契约条目，保持快照顺序
     */
    static Stream<Arguments> entries() {
        List<Arguments> arguments = new ArrayList<>();
        for (JsonNode entry : ContractSnapshot.entries("WeChatClient")) {
            arguments.add(Arguments.of(entry.path("id").asText(), entry));
        }
        assertEquals(12, arguments.size(), "WeChatClient 契约条目数量");
        return arguments.stream();
    }

    /**
     * 从契约请求的查询串里取出 access_token 占位符对应的令牌名
     */
    private static String tokenOf(JsonNode request) {
        String query = request.path("query").asText();
        Matcher matcher = TOKEN_INDEX.matcher(query);
        assertTrue(matcher.find(), "契约请求缺少 access_token 占位符：" + query);
        return "tok-" + matcher.group(1);
    }
}
