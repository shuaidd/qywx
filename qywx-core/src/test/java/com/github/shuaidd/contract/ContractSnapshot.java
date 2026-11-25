package com.github.shuaidd.contract;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuaidd.file.QywxFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 描述 读取 5.2.0 契约快照，并把参数与请求转换成可回放的断言
 *
 * @author ddshuai
 * date 2026-11-26 00:30
 **/
public final class ContractSnapshot {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ConcurrentHashMap<String, JsonNode> CLIENTS = new ConcurrentHashMap<>();

    private static final Pattern TOKEN = Pattern.compile("access_token=tok-(\\d+)");

    private static final Pattern BOUNDARY = Pattern.compile("boundary=([^;]+)");

    private ContractSnapshot() {
    }

    /**
     * 取完整 id（Client.method 或 Client.method#scenario）对应的契约条目；条目自身的 id 也是完整形式
     */
    public static JsonNode entry(String id) {
        int dot = id.indexOf('.');
        assertTrue(dot > 0, "契约 id 必须包含 client 与 method：" + id);
        String client = id.substring(0, dot);
        JsonNode entries = CLIENTS.computeIfAbsent(client, ContractSnapshot::load);
        for (JsonNode entry : entries) {
            if (id.equals(entry.path("id").asText())) {
                return entry;
            }
        }
        throw new AssertionError("找不到契约条目：" + id);
    }

    /**
     * 按快照中的参数顺序构造调用参数
     */
    public static Object[] args(JsonNode entry) {
        JsonNode params = entry.get("params");
        Object[] args = new Object[params == null || params.isNull() ? 0 : params.size()];
        if (params == null || params.isNull()) {
            return args;
        }
        for (int i = 0; i < params.size(); i++) {
            args[i] = argument(params.get(i));
        }
        return args;
    }

    /**
     * 比对契约条目与回放客户端记录的请求；offset 用于跳过预热阶段的请求
     */
    public static void assertMatches(JsonNode entry, RecordingClient recording) {
        assertMatches(entry, recording, 0);
    }

    public static void assertMatches(JsonNode entry, RecordingClient recording, int offset) {
        JsonNode expectedRequests = entry.get("requests");
        int expectedCount = expectedRequests == null || expectedRequests.isNull() ? 0 : expectedRequests.size();
        assertEquals(expectedCount, recording.requestCount() - offset, "请求数量");
        if (expectedCount == 0) {
            return;
        }
        for (int i = 0; i < expectedCount; i++) {
            assertRequest(expectedRequests.get(i), recording.request(offset + i), "第 " + i + " 个请求：");
        }
    }

    /**
     * 把 Feign 请求头转成小写键、逗号连接值的 Map
     */
    public static Map<String, String> headerValues(Map<String, Collection<String>> headers) {
        Map<String, String> values = new LinkedHashMap<>();
        if (headers == null) {
            return values;
        }
        for (Map.Entry<String, Collection<String>> header : headers.entrySet()) {
            if (header.getKey() == null || header.getValue() == null) {
                continue;
            }
            StringBuilder joined = new StringBuilder();
            for (String value : header.getValue()) {
                if (joined.length() > 0) {
                    joined.append(',');
                }
                joined.append(value);
            }
            values.put(header.getKey().toLowerCase(java.util.Locale.ROOT), joined.toString());
        }
        return values;
    }

    private static JsonNode load(String client) {
        String resource = "/contract/5.2.0/" + client + ".json";
        try (InputStream in = ContractSnapshot.class.getResourceAsStream(resource)) {
            assertNotNull(in, "找不到契约快照：" + resource);
            return MAPPER.readTree(in);
        } catch (IOException e) {
            throw new AssertionError("读取契约快照失败：" + resource, e);
        }
    }

    /**
     * 取 multipart 请求头里的 boundary；非 multipart 请求返回 null
     */
    private static String multipartBoundary(String contentType, String message) {
        if (contentType == null || !contentType.startsWith("multipart/form-data")) {
            return null;
        }
        Matcher matcher = BOUNDARY.matcher(contentType);
        assertTrue(matcher.find(), message + "multipart Content-Type 缺少 boundary");
        return matcher.group(1);
    }

    private static Object argument(JsonNode param) {
        String kind = param.path("kind").asText();
        if ("file".equals(kind)) {
            byte[] content = Base64.getDecoder().decode(param.get("contentBase64").asText());
            return new QywxFile(content, param.get("filename").asText(), param.get("contentType").asText());
        }
        JsonNode value = param.get("value");
        if (value == null || value.isNull()) {
            return null;
        }
        String type = param.get("type").asText();
        if ("java.lang.String".equals(type)) {
            return value.asText();
        }
        if ("java.lang.Integer".equals(type)) {
            return value.intValue();
        }
        try {
            return MAPPER.treeToValue(value, Class.forName(type));
        } catch (Exception e) {
            throw new AssertionError("构造契约参数失败：" + type, e);
        }
    }

    private static void assertRequest(JsonNode expected, feign.Request actual, String message) {
        assertEquals(expected.path("method").asText(), actual.method(), message + "method");

        String relative = actual.url().substring(RecordingClient.TARGET_URL.length());
        int queryIndex = relative.indexOf('?');
        String actualPath = queryIndex < 0 ? relative : relative.substring(0, queryIndex);
        String actualQuery = queryIndex < 0 ? "" : relative.substring(queryIndex + 1);
        assertEquals(expected.path("path").asText(), RecordingClient.PUBLIC_PATH + actualPath, message + "path");

        Matcher matcher = TOKEN.matcher(actualQuery);
        StringBuffer normalized = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(normalized, "access_token={{TOKEN_" + matcher.group(1) + "}}");
        }
        matcher.appendTail(normalized);
        assertEquals(expected.path("query").asText(), normalized.toString(), message + "query");

        Map<String, String> expectedHeaders = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = expected.path("headers").fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            expectedHeaders.put(field.getKey().toLowerCase(java.util.Locale.ROOT), field.getValue().asText());
        }
        Map<String, String> actualHeaders = headerValues(actual.headers());
        actualHeaders.remove("content-length");
        //boundary 是运行时随机值：先从原始请求头取出，再把请求头里的值换回占位符后与快照比对
        String boundary = multipartBoundary(actualHeaders.get("content-type"), message);
        String expectedContentType = expectedHeaders.get("content-type");
        if (expectedContentType != null && expectedContentType.contains("{{BOUNDARY}}")) {
            assertNotNull(boundary, message + "缺少 multipart Content-Type");
            actualHeaders.put("content-type", actualHeaders.get("content-type").replace(boundary, "{{BOUNDARY}}"));
        }
        assertEquals(expectedHeaders, actualHeaders, message + "headers");

        JsonNode expectedBody = expected.get("body");
        byte[] actualBody = actual.body();
        if (expectedBody == null || expectedBody.isNull() || expectedBody.asText().isEmpty()) {
            assertTrue(actualBody == null || actualBody.length == 0, message + "应无请求体");
            return;
        }
        assertNotNull(actualBody, message + "缺少请求体");
        String expectedText = expectedBody.asText();
        if (boundary != null) {
            expectedText = expectedText.replace("{{BOUNDARY}}", boundary);
            assertEquals(expectedText, new String(actualBody, StandardCharsets.ISO_8859_1), message + "body");
        } else {
            assertEquals(expectedText.replace("\r\n", "\n"),
                    new String(actualBody, StandardCharsets.UTF_8).replace("\r\n", "\n"), message + "body");
        }
    }
}
