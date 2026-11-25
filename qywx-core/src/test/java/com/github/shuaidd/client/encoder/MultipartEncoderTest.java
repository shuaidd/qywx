package com.github.shuaidd.client.encoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.contract.ContractApi;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.CountingFetcher;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.file.QywxFile;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link MultipartEncoder} 的单元测试：按 5.2.0 契约快照逐字节回放 multipart 报文、缺少 @Part 报错、非文件请求委托 JSON encoder、文件名清理与类型回退
 *
 * @author ddshuai
 * date 2026-11-26 03:44
 **/
class MultipartEncoderTest {

    private static final String PART_REQUIRED = "上传参数缺少 @Part 注解，无法确定 multipart 字段名";

    private static final Pattern CONTENT_TYPE =
            Pattern.compile("^multipart/form-data; charset=UTF-8; boundary=(----qywx[0-9a-f]{32})$");

    /**
     * 四条 multipart 契约：请求行、请求头、报文逐字节一致（boundary 为运行时随机值，按实际值置换）
     */
    @ParameterizedTest(name = "{0}")
    @ValueSource(strings = {"WeChatMediaClient.uploadMaterial", "WeChatMediaClient.uploadAttachment",
            "WeChatMediaClient.uploadImage", "PaymentUploadClient.uploadImage"})
    void replaysMultipartContracts(String id) {
        Fixture fixture = new Fixture();
        JsonNode entry = ContractSnapshot.entry(id);

        assertNotNull(upload(fixture.api, entry));

        ContractSnapshot.assertMatches(entry, fixture.recording);
        assertEquals(1, fixture.fetcher.calls());
        String contentType = ContractSnapshot.headerValues(fixture.recording.request(0).headers()).get("content-type");
        assertNotNull(contentType);
        Matcher matcher = CONTENT_TYPE.matcher(contentType);
        assertTrue(matcher.matches(), "multipart Content-Type 不符合约定：" + contentType);
        assertEquals(multipartBody(entry, matcher.group(1)),
                new String(fixture.recording.request(0).body(), StandardCharsets.ISO_8859_1));
    }

    /**
     * 文件参数没有 @Part：编码阶段报错，不发请求
     */
    @Test
    void rejectsFileWithoutPartAnnotation() {
        Fixture fixture = new Fixture();

        EncodeException thrown = assertThrows(EncodeException.class,
                () -> fixture.api.uploadWithoutPart(new QywxFile(new byte[] {1, 2, 3}, "a.bin"), ContractFeign.APP));

        assertEquals(PART_REQUIRED, thrown.getMessage());
        assertEquals(0, fixture.recording.requestCount());
    }

    /**
     * 模板上没有方法元数据（单独调用 encoder）：同样报缺少 @Part
     */
    @Test
    void rejectsFileWhenTemplateHasNoMethodMetadata() {
        QywxFile file = new QywxFile(new byte[] {1, 2, 3}, "a.bin");

        EncodeException thrown = assertThrows(EncodeException.class,
                () -> new MultipartEncoder(JacksonJsonEncoder.indented())
                        .encode(file, QywxFile.class, new RequestTemplate()));

        assertEquals(PART_REQUIRED, thrown.getMessage());
    }

    /**
     * 请求体不是文件：原样委托给 JSON encoder（缩进 JSON + form-urlencoded）
     */
    @Test
    void delegatesNonFileBodyToJsonEncoder() {
        RequestTemplate template = new RequestTemplate();

        new MultipartEncoder(JacksonJsonEncoder.indented())
                .encode(Collections.singletonMap("a", 1), Map.class, template);

        assertEquals("{\n  \"a\" : 1\n}",
                new String(template.body(), StandardCharsets.UTF_8).replace("\r\n", "\n"));
        assertEquals(JacksonJsonEncoder.FORM_CONTENT_TYPE,
                ContractSnapshot.headerValues(template.headers()).get("content-type"));
    }

    /**
     * 文件名里的引号与换行会被去掉；未指定类型且按文件名猜不到时回退 application/octet-stream
     */
    @Test
    void sanitizesFilenameAndFallsBackToOctetStream() {
        Fixture fixture = new Fixture();

        assertNotNull(fixture.api.uploadImage(new QywxFile(new byte[] {1, 2, 3}, "a\"b\r\nc.bin", null),
                ContractFeign.APP));

        assertEquals(1, fixture.recording.requestCount());
        String body = new String(fixture.recording.request(0).body(), StandardCharsets.ISO_8859_1);
        assertTrue(body.contains("filename=\"abc.bin\"\r\n"), "文件名应去掉引号与换行：" + body);
        assertTrue(body.contains("Content-Type: application/octet-stream\r\n"), "类型应回退 octet-stream：" + body);
    }

    /**
     * 未指定类型时按文件名猜测：png 应得到 image/png
     */
    @Test
    void guessesContentTypeFromFilename() {
        Fixture fixture = new Fixture();

        assertNotNull(fixture.api.uploadImage(new QywxFile(new byte[] {1, 2, 3}, "x.png"), ContractFeign.APP));

        String body = new String(fixture.recording.request(0).body(), StandardCharsets.ISO_8859_1);
        assertTrue(body.contains("Content-Type: image/png\r\n"), "类型应按文件名猜测：" + body);
    }

    private static final class Fixture {

        private final RecordingClient recording = new RecordingClient();

        private final CountingFetcher fetcher = new CountingFetcher();

        private final QywxConfig config = ContractFeign.config(false);

        private final RecordingStore store = new RecordingStore();

        private final ContractApi api = ContractFeign.create(ContractApi.class, JacksonJsonEncoder.indented(),
                config, ContractFeign.tokenManager(fetcher, config, store),
                recording);
    }

    /**
     * 按契约条目发起上传；支付 client 的 uploadImage 在回放接口上叫 paymentUploadImage（避免与媒体 client 的同名方法混淆）
     */
    private static Object upload(ContractApi api, JsonNode entry) {
        Object[] args = ContractSnapshot.args(entry);
        if (entry.path("id").asText().startsWith("PaymentUploadClient.")) {
            return api.paymentUploadImage((QywxFile) args[0], (String) args[1]);
        }
        return ContractFeign.call(api, entry);
    }

    /**
     * 按契约里记录的 part 名、文件名、类型与内容拼出期望的 multipart 报文
     */
    private static String multipartBody(JsonNode entry, String boundary) {
        JsonNode file = entry.path("params").get(0);
        byte[] content = Base64.getDecoder().decode(file.get("contentBase64").asText());
        StringBuilder body = new StringBuilder();
        body.append("--").append(boundary).append("\r\n")
                .append("Content-Disposition: form-data; name=\"").append(file.get("partName").asText())
                .append("\"; filename=\"").append(file.get("filename").asText()).append("\"\r\n")
                .append("Content-Type: ").append(file.get("contentType").asText()).append("\r\n")
                .append("Content-Transfer-Encoding: binary\r\n\r\n")
                .append(new String(content, StandardCharsets.ISO_8859_1))
                .append("\r\n--").append(boundary).append("--\r\n");
        return body.toString();
    }
}
