package com.github.shuaidd.client.decoder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractApi;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import com.github.shuaidd.token.TokenManager;
import feign.FeignException;
import feign.Request;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link WeChatMediaDecoder} 的单元测试：下载到内存、按 {@link DownloadContext} 落盘、文件名处理、error-code 响应头，
 * 以及 5.2.0 契约快照里 5 条下载记录的回放
 *
 * @author ddshuai
 * date 2026-11-26 04:31
 **/
class WeChatMediaDecoderTest {

    private static final Request REQUEST = Request.create(Request.HttpMethod.GET,
            "http://contract.invalid/cgi-bin/media/get", Collections.emptyMap(), null, Util.UTF_8);

    /** 5.2.0 录制时的下载响应体 */
    private static final byte[] CONTENT = "CONTRACT-DOWNLOAD-CONTENT".getBytes(Util.UTF_8);

    /** 5.2.0 录制时的 Content-Disposition：同时带 filename 与 filename* */
    private static final String CONTRACT_DISPOSITION =
            "attachment; filename=\"contract.bin\"; filename*=UTF-8''contract.bin";

    private static final Type TYPE = FileDownload.class;

    @TempDir
    Path tempDir;

    private final WeChatMediaDecoder decoder = new WeChatMediaDecoder();

    @AfterEach
    void clear() {
        DownloadContext.clear();
    }

    /**
     * 返回类型为 FileDownload：响应体读进内存，响应头随结果返回，响应流读完即关
     */
    @Test
    void downloadsIntoMemory() throws IOException {
        TrackedStream stream = new TrackedStream(CONTENT);

        FileDownload result = (FileDownload) decoder.decode(response(contractHeaders(), stream), TYPE);

        assertArrayEquals(CONTENT, result.getContent());
        assertEquals("application/octet-stream", result.getContentType());
        assertEquals("contract.bin", result.getFileName());
        assertEquals(CONTRACT_DISPOSITION, result.getHeader("Content-Disposition"));
        assertTrue(stream.read.get());
        assertTrue(stream.closed.get());
    }

    /**
     * 没有响应体时内容为空数组，响应头照常解析
     */
    @Test
    void returnsEmptyContentWhenBodyIsMissing() throws IOException {
        FileDownload result = (FileDownload) decoder.decode(response(contractHeaders(), (InputStream) null), TYPE);

        assertEquals(0, result.getContent().length);
        assertEquals("contract.bin", result.getFileName());
    }

    /**
     * 读取响应体失败：抛出 IOException，并且关闭响应体
     */
    @Test
    void closesBodyWhenReadingFails() {
        FailingStream stream = new FailingStream();

        IOException exception = assertThrows(IOException.class,
                () -> decoder.decode(response(contractHeaders(), stream), TYPE));

        assertEquals("read failed", exception.getMessage());
        assertTrue(stream.closed.get());
    }

    /**
     * 返回类型为 File 且目标是已存在的目录：按服务端文件名保存，内容一致，响应流读完即关，输出流已关闭
     */
    @Test
    void downloadsIntoTargetDirectory() throws IOException {
        DownloadContext.set(tempDir);
        TrackedStream stream = new TrackedStream(CONTENT);

        File result = (File) decoder.decode(response(disposition("attachment; filename=\"report.pdf\""), stream),
                File.class);

        assertEquals(tempDir.resolve("report.pdf").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
        assertTrue(stream.read.get());
        assertTrue(stream.closed.get());
        //输出流已关闭：Windows 上文件被占用时删除会失败
        Files.delete(result.toPath());
    }

    /**
     * 响应头没有 Content-Disposition 时使用默认文件名
     */
    @Test
    void usesDefaultFileNameWhenContentDispositionIsMissing() throws IOException {
        DownloadContext.set(tempDir);

        File result = (File) decoder.decode(response(Collections.emptyMap(), CONTENT), File.class);

        assertEquals(tempDir.resolve("download").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
    }

    /**
     * RFC 5987 的文件名解码失败（非法的百分号转义）时回退到 filename 参数
     */
    @Test
    void fallsBackToPlainFileNameWhenExtendedFileNameIsInvalid() throws IOException {
        DownloadContext.set(tempDir);

        File result = (File) decoder.decode(
                response(disposition("attachment; filename=\"fallback.bin\"; filename*=UTF-8''%ZZ"), CONTENT),
                File.class);

        assertEquals(tempDir.resolve("fallback.bin").toFile(), result);
    }

    /**
     * 服务端文件名带目录：只保留文件名部分，不会写到目标目录之外
     */
    @Test
    void stripsDirectoryFromFileName() throws IOException {
        DownloadContext.set(tempDir);

        File result = (File) decoder.decode(
                response(disposition("attachment; filename=\"../../evil.bin\""), CONTENT), File.class);

        assertEquals(tempDir.resolve("evil.bin").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
        assertEquals(1, entryCount());
    }

    /**
     * 纯点名的文件名拒绝落盘：不读取响应体、不创建文件，并关闭响应体
     */
    @Test
    void rejectsDotOnlyFileName() throws IOException {
        DownloadContext.set(tempDir);
        TrackedStream stream = new TrackedStream(CONTENT);

        IOException exception = assertThrows(IOException.class, () -> decoder.decode(
                response(disposition("attachment; filename=\"..\""), stream), File.class));

        assertTrue(exception.getMessage().contains("文件名不合法"));
        assertFalse(stream.read.get());
        assertTrue(stream.closed.get());
        assertEquals(0, entryCount());
    }

    /**
     * 目标是文件路径（与 5.x 的 downloadFilePath 语义一致）：按该路径保存，服务端文件名被忽略
     */
    @Test
    void downloadsIntoExplicitFile() throws IOException {
        Path parent = tempDir.resolve("out");
        Files.createDirectories(parent);
        Path target = parent.resolve("renamed.bin");
        DownloadContext.set(target);

        File result = (File) decoder.decode(response(contractHeaders(), CONTENT), File.class);

        assertEquals(target.toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(target));
        assertFalse(Files.exists(tempDir.resolve("contract.bin")));
    }

    /**
     * 目标文件的父目录不存在：抛出 NoSuchFileException，不创建文件，响应体未被读取但已关闭
     */
    @Test
    void failsWhenParentDirectoryIsMissing() {
        Path target = tempDir.resolve("missing").resolve("file.bin");
        DownloadContext.set(target);
        TrackedStream stream = new TrackedStream(CONTENT);

        assertThrows(NoSuchFileException.class, () -> decoder.decode(response(contractHeaders(), stream), File.class));

        assertFalse(Files.exists(target));
        assertFalse(stream.read.get());
        assertTrue(stream.closed.get());
    }

    /**
     * 没有设置落盘目标：保存到系统临时目录
     */
    @Test
    void downloadsIntoSystemTempDirectoryWhenTargetIsMissing() throws IOException {
        Path expected = Paths.get(System.getProperty("java.io.tmpdir"), "contract.bin");
        try {
            File result = (File) decoder.decode(response(contractHeaders(), CONTENT), File.class);

            assertEquals(expected.toFile(), result);
            assertArrayEquals(CONTENT, Files.readAllBytes(expected));
        } finally {
            Files.deleteIfExists(expected);
        }
    }

    /**
     * 响应头 error-code 为已知错误码：抛出带枚举错误码的 WeChatException，不读取响应体，并关闭响应体
     */
    @Test
    void throwsWeChatExceptionForKnownErrorCode() {
        TrackedStream stream = new TrackedStream(CONTENT);

        WeChatException exception = assertThrows(WeChatException.class, () -> decoder.decode(
                response(errorHeaders("40007", "invalid media id"), stream), TYPE));

        assertEquals(ErrorCode.ERROR_CODE_40007, exception.getErrorCode());
        assertEquals("40007", exception.getCode());
        assertEquals("invalid media id", exception.getMessage());
        assertFalse(stream.read.get());
        assertTrue(stream.closed.get());
    }

    /**
     * 枚举里没有的错误码：抛出 UNKNOWN 的 WeChatException 并保留原始码；落盘场景下不创建文件
     */
    @Test
    void throwsUnknownWeChatExceptionForUnlistedErrorCode() throws IOException {
        DownloadContext.set(tempDir);
        TrackedStream stream = new TrackedStream(CONTENT);

        WeChatException exception = assertThrows(WeChatException.class, () -> decoder.decode(
                response(errorHeaders("49999", "weird"), stream), File.class));

        assertEquals(ErrorCode.UNKNOWN, exception.getErrorCode());
        assertEquals("49999", exception.getCode());
        assertEquals("weird", exception.getMessage());
        assertFalse(stream.read.get());
        assertTrue(stream.closed.get());
        assertEquals(0, entryCount());
    }

    /**
     * 缺少 error-msg 时不抛 NPE，异常消息为 null
     */
    @Test
    void toleratesMissingErrorMessage() {
        TrackedStream stream = new TrackedStream(CONTENT);

        WeChatException exception = assertThrows(WeChatException.class,
                () -> decoder.decode(response(errorHeaders("40007", null), stream), TYPE));

        assertEquals(ErrorCode.ERROR_CODE_40007, exception.getErrorCode());
        assertNull(exception.getMessage());
        assertFalse(stream.read.get());
        assertTrue(stream.closed.get());
    }

    /**
     * 响应头名称不区分大小写：Error-Code 为 0 时正常下载，参数名 FILENAME 也能解析
     */
    @Test
    void matchesHeaderNamesCaseInsensitively() throws IOException {
        DownloadContext.set(tempDir);
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put("Error-Code", Collections.singletonList("0"));
        headers.put("Content-Disposition", Collections.singletonList("attachment; FILENAME=\"report.pdf\""));

        File result = (File) decoder.decode(response(headers, CONTENT), File.class);

        assertEquals(tempDir.resolve("report.pdf").toFile(), result);
    }

    /**
     * 非下载类型交给 CustomJacksonDecoder
     */
    @Test
    void delegatesOtherTypesToJacksonDecoder() throws IOException {
        Object result = decoder.decode(response(Collections.emptyMap(),
                "{\"errcode\":0,\"errmsg\":\"ok\",\"ip_list\":[\"1.2.3.4\"]}".getBytes(Util.UTF_8)),
                ApiDomainIpResponse.class);

        assertEquals(Collections.singletonList("1.2.3.4"), ((ApiDomainIpResponse) result).getIpList());
    }

    /**
     * 只在返回类型不是下载类型时才调用注入的 decoder
     */
    @Test
    void callsInjectedDecoderOnlyForNonDownloadTypes() throws IOException {
        List<Type> delegated = new ArrayList<>();
        WeChatMediaDecoder custom = new WeChatMediaDecoder((response, type) -> {
            delegated.add(type);
            return "delegated";
        });

        assertEquals("delegated", custom.decode(response(Collections.emptyMap(), (InputStream) null), String.class));
        assertEquals(Collections.<Type>singletonList(String.class), delegated);

        assertTrue(custom.decode(response(contractHeaders(), CONTENT), TYPE) instanceof FileDownload);
        assertEquals(1, delegated.size());
    }

    /**
     * 注入的 decoder 不能为 null
     */
    @Test
    void rejectsNullDelegate() {
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> new WeChatMediaDecoder(null));

        assertEquals("delegate", exception.getMessage());
    }

    /**
     * 契约回放：下载到内存的两个方法，请求与 5.2.0 逐项一致（返回类型由 {@code ResponseEntity<byte[]>} 换成 {@link FileDownload}）
     */
    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"WeChatMediaClient.download", "WeChatMediaClient.downloadJsMedia"})
    void replaysDownloadContracts(String id) throws IOException {
        RecordingClient recording = new RecordingClient().respond(200, CONTENT, contractHeaders());
        JsonNode entry = ContractSnapshot.entry(id);

        FileDownload result = (FileDownload) ContractFeign.call(api(recording), entry);

        assertArrayEquals(CONTENT, result.getContent());
        assertEquals("contract.bin", result.getFileName());
        assertEquals(1, recording.requestCount());
        ContractSnapshot.assertMatches(entry, recording);
    }

    /**
     * 契约回放：下载出错（响应头 error-code=40007）时对外仍是 Feign 包装后的 DecodeException，cause 为 WeChatException，
     * 与 5.2.0 录制结果一致；40007 不是令牌失效错误码，不重试
     */
    @Test
    void replaysDownloadErrorContract() {
        RecordingClient recording = new RecordingClient().respond(200, CONTENT, errorHeaders("40007", "invalid media id"));
        JsonNode expected = ContractSnapshot.entry("WeChatMediaClient.download#download-error").path("result");
        assertEquals("throw", expected.path("kind").asText());

        DecodeException exception = assertThrows(DecodeException.class,
                () -> api(recording).download("v_media_id", ContractFeign.APP));

        assertEquals(expected.path("type").asText(), exception.getClass().getName());
        assertEquals(expected.path("msg").asText(), exception.getMessage());
        WeChatException cause = ContractFeign.findCause(exception, WeChatException.class);
        assertNotNull(cause);
        assertEquals(expected.path("rootType").asText(), cause.getClass().getName());
        assertEquals(expected.path("rootErrorCode").asText(), cause.getCode());
        assertEquals(expected.path("rootMsg").asText(), cause.getMessage());
        assertEquals(1, recording.requestCount());
        ContractSnapshot.assertMatches(ContractSnapshot.entry("WeChatMediaClient.download#download-error"), recording);
    }

    /**
     * 契约回放：下载到文件的两个方法。5.2.0 把 downloadFilePath 作为查询参数与请求头发送，core 不再发送（偏差 #4），
     * 目标改由 {@link DownloadContext} 提供：实参经调用 handler 在调用期间交给 DownloadContext，调用结束后恢复为之前的值；
     * 文件名取 RFC 5987 的 contract.bin（偏差 #5，5.2.0 录制结果为 UTF-8''contract.bin）。
     * 快照里下标 1 是录制时的临时目录，回放时换成当前临时目录
     */
    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"WeChatMediaClient.downloadFile", "WeChatMediaClient.downloadJsMediaFile"})
    void replaysDownloadFileContracts(String id) throws IOException {
        RecordingClient recording = new RecordingClient().respond(200, CONTENT, contractHeaders());
        JsonNode entry = ContractSnapshot.entry(id);

        File result = (File) ContractFeign.call(api(recording), entry, 1, tempDir.toString());

        assertEquals("contract.bin", result.getName());
        assertEquals(tempDir.resolve("contract.bin").toFile(), result);
        assertArrayEquals(CONTENT, Files.readAllBytes(result.toPath()));
        assertEquals(1, recording.requestCount());
        assertMatchesWithoutDownloadFilePath(entry, recording);
        assertNull(recording.request(0).body(), "落盘目标不随请求发送，不能被 Feign 当成表单体");
        assertNull(ContractSnapshot.headerValues(recording.request(0).headers()).get("content-type"),
                "没有请求体时不应该有 Content-Type");
        assertNull(DownloadContext.get(), "调用结束后恢复为之前的值（未设置）");
    }

    /**
     * 经 Feign 代理调用：落盘前的检查失败时，IOException 被 Feign 包成 FeignException 抛出，cause 仍是原始的 IOException；
     * 不创建文件、不重试
     */
    @Test
    void wrapsFileFailuresInFeignException() throws IOException {
        RecordingClient recording = new RecordingClient().respond(200, CONTENT, disposition("attachment; filename=\"..\""));
        ContractApi api = api(recording);

        FeignException exception = assertThrows(FeignException.class,
                () -> api.downloadFile("v_media_id", tempDir.toString(), ContractFeign.APP));

        IOException cause = ContractFeign.findCause(exception, IOException.class);
        assertNotNull(cause);
        assertTrue(cause.getMessage().contains("文件名不合法"));
        assertEquals(0, entryCount());
        assertEquals(1, recording.requestCount());
        assertNull(DownloadContext.get(), "调用异常结束时同样恢复为之前的值");
    }

    /**
     * 按 core 的装配方式组装契约回放用的 client：下载场景换成 WeChatMediaDecoder
     */
    private static ContractApi api(RecordingClient recording) {
        QywxConfig config = ContractFeign.config(false);
        TokenManager tokenManager = ContractFeign.tokenManager(new ContractFeign.CountingFetcher(), config);
        return ContractFeign.create(ContractApi.class, new WeChatMediaDecoder(), JacksonJsonEncoder.indented(),
                config, tokenManager, recording);
    }

    /**
     * 回放下载到文件的契约条目：5.2.0 会把 downloadFilePath 放进查询参数与请求头，core 不再发送（偏差 #4），
     * 比对前先把快照里的这两处去掉
     */
    private static void assertMatchesWithoutDownloadFilePath(JsonNode entry, RecordingClient recording) {
        ObjectNode expected = entry.deepCopy();
        ObjectNode request = (ObjectNode) expected.path("requests").path(0);
        request.put("query", request.path("query").asText().replaceFirst("downloadFilePath=[^&]*&", ""));
        ((ObjectNode) request.path("headers")).remove("downloadFilePath");
        ContractSnapshot.assertMatches(expected, recording);
    }

    /**
     * 5.2.0 录制时的下载响应头
     */
    private static Map<String, Collection<String>> contractHeaders() {
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put("Content-Disposition", Collections.singletonList(CONTRACT_DISPOSITION));
        headers.put("Content-Type", Collections.singletonList("application/octet-stream"));
        return headers;
    }

    /**
     * 下载出错的响应头：在录制头的基础上加 error-code，errorMsg 不为 null 时加 error-msg
     */
    private static Map<String, Collection<String>> errorHeaders(String errorCode, String errorMsg) {
        Map<String, Collection<String>> headers = contractHeaders();
        headers.put("error-code", Collections.singletonList(errorCode));
        if (errorMsg != null) {
            headers.put("error-msg", Collections.singletonList(errorMsg));
        }
        return headers;
    }

    private static Map<String, Collection<String>> disposition(String contentDisposition) {
        Map<String, Collection<String>> headers = new LinkedHashMap<>();
        headers.put("Content-Disposition", Collections.singletonList(contentDisposition));
        return headers;
    }

    private static Response response(Map<String, Collection<String>> headers, InputStream stream, int length) {
        Response.Builder builder = Response.builder()
                .status(200)
                .reason("contract")
                .headers(headers)
                .request(REQUEST);
        if (stream != null) {
            builder.body(stream, length);
        }
        return builder.build();
    }

    /**
     * 响应体为输入流：Feign 把它原样包成 InputStreamBody，关闭时直接关这个流
     */
    private static Response response(Map<String, Collection<String>> headers, InputStream stream) {
        return response(headers, stream, CONTENT.length);
    }

    private static Response response(Map<String, Collection<String>> headers, byte[] content) {
        return response(headers, content == null ? null : new ByteArrayInputStream(content), content == null ? 0 : content.length);
    }

    /**
     * 目标目录下的条目数
     */
    private long entryCount() throws IOException {
        try (Stream<Path> entries = Files.list(tempDir)) {
            return entries.count();
        }
    }

    /**
     * 记录是否被读取、是否被关闭的响应流
     */
    private static final class TrackedStream extends ByteArrayInputStream {

        private final AtomicBoolean read = new AtomicBoolean();

        private final AtomicBoolean closed = new AtomicBoolean();

        private TrackedStream(byte[] content) {
            super(content);
        }

        @Override
        public int read(byte[] bytes, int offset, int length) {
            read.set(true);
            return super.read(bytes, offset, length);
        }

        @Override
        public int read() {
            read.set(true);
            return super.read();
        }

        @Override
        public void close() throws IOException {
            closed.set(true);
            super.close();
        }
    }

    /**
     * 读取时抛出 IOException 的响应流
     */
    private static final class FailingStream extends InputStream {

        private final AtomicBoolean closed = new AtomicBoolean();

        @Override
        public int read() throws IOException {
            throw new IOException("read failed");
        }

        @Override
        public int read(byte[] bytes, int offset, int length) throws IOException {
            throw new IOException("read failed");
        }

        @Override
        public void close() {
            closed.set(true);
        }
    }
}
