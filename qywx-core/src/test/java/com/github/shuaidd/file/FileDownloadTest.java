package com.github.shuaidd.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link FileDownload} 的单元测试：响应头拷贝（小写键、多值、只读）、Content-Disposition 文件名解析
 * （RFC 5987 的 {@code filename*} 优先、去目录与非法字符）
 *
 * @author ddshuai
 * date 2026-11-26 04:26
 **/
class FileDownloadTest {

    private static final byte[] CONTENT = "contract-bytes".getBytes(StandardCharsets.UTF_8);

    public static Stream<Arguments> contentDispositions() {
        return Stream.<Arguments>of(
                Arguments.of("attachment; filename=\"fallback.bin\"; filename*=UTF-8''%E4%B8%AD%E6%96%87%20v2+1.bin",
                        "中文 v2+1.bin"),
                Arguments.of("attachment; filename*=ISO-8859-1''caf%E9.bin", "café.bin"),
                Arguments.of("attachment; filename=\"a;b\\\"c.bin\"", "a;b_c.bin"),
                Arguments.of("attachment; filename=plain.bin", "plain.bin"),
                Arguments.of("attachment; filename=\"café.bin\"", "café.bin"),
                Arguments.of("attachment; filename=\"sp ace.bin\"", "sp ace.bin"),
                Arguments.of("attachment; FILENAME=\"upper.bin\"", "upper.bin"),
                Arguments.of("attachment; filename=../../etc/passwd", "passwd"),
                Arguments.of("attachment; filename=\"..\\..\\etc\\passwd\"", "passwd"),
                Arguments.of("attachment; filename=\"C:\\dir\\bad:name?.bin\"", "bad_name_.bin"),
                Arguments.of("attachment", null),
                Arguments.of((String) null, null),
                Arguments.of("", null),
                Arguments.of("attachment; size=10", null),
                Arguments.of("attachment; filename=\"dir/\"", null),
                Arguments.of("attachment; filename=\"fallback.bin\"; filename*=BAD''x", "fallback.bin"),
                Arguments.of("attachment; filename=\"fallback.bin\"; filename*=UTF-8''%ZZ", "fallback.bin"),
                Arguments.of("attachment; filename=\"fallback.bin\"; filename*=UTF-8abc", "fallback.bin"),
                Arguments.of("attachment; filename=\"..\"", ".."));
    }

    /**
     * 按参数化用例解析文件名：filename* 优先；引号内只还原转义引号与反斜杠，其余反斜杠按目录分隔符去掉；
     * 去掉目录、控制字符与 Windows 非法字符；解析不到（包括去掉目录后为空）时返回 null
     */
    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @MethodSource("contentDispositions")
    void parsesFileName(String contentDisposition, String expected) {
        assertEquals(expected, FileDownload.parseFileName(contentDisposition));
    }

    /**
     * 响应头拷贝为小写键的只读多值表：之后修改源 Map、源列表都不影响
     */
    @Test
    void copiesHeadersAsLowerCaseReadOnlyMultiValues() {
        Map<String, Collection<String>> source = new LinkedHashMap<>();
        source.put("Content-Type", Arrays.asList("application/octet-stream", "charset=UTF-8"));
        List<String> disposition = new ArrayList<>(Collections.singletonList("attachment"));
        source.put("Content-Disposition", disposition);
        source.put(null, Collections.singletonList("null-key"));
        source.put("X-Null", null);

        FileDownload download = new FileDownload(CONTENT, source);
        disposition.add("added-later");

        Map<String, List<String>> headers = download.getHeaders();
        assertEquals(2, headers.size());
        assertEquals(Arrays.asList("application/octet-stream", "charset=UTF-8"), headers.get("content-type"));
        assertEquals(Collections.singletonList("attachment"), headers.get("content-disposition"));
        assertEquals("application/octet-stream", download.getContentType());
        assertEquals("application/octet-stream", download.getHeader("CONTENT-TYPE"));
        assertNull(download.getHeader("X-Missing"));
        assertNull(download.getHeader(null));
        assertThrows(UnsupportedOperationException.class, () -> headers.put("x", Collections.emptyList()));
        assertThrows(UnsupportedOperationException.class, () -> headers.get("content-type").add("mutated"));
        assertArrayEquals(CONTENT, download.getContent());
    }

    /**
     * 内容不允许为 null；响应头为 null 时按没有响应头处理
     */
    @Test
    void rejectsNullContentAndAcceptsNullHeaders() {
        assertEquals("content", assertThrows(NullPointerException.class,
                () -> new FileDownload(null, Collections.emptyMap())).getMessage());

        FileDownload download = new FileDownload(CONTENT, null);
        assertTrue(download.getHeaders().isEmpty());
        assertNull(download.getContentType());
        assertNull(download.getFileName());
    }

    /**
     * 文件名取自 Content-Disposition
     */
    @Test
    void readsFileNameFromContentDisposition() {
        FileDownload download = new FileDownload(CONTENT, Collections.singletonMap("Content-Disposition",
                Collections.singletonList("attachment; filename=\"report.pdf\"")));

        assertEquals("report.pdf", download.getFileName());
    }

    /**
     * toString 只描述大小与 Content-Type
     */
    @Test
    void describesSizeAndContentType() {
        FileDownload download = new FileDownload(CONTENT, Collections.singletonMap(FileDownload.CONTENT_TYPE,
                Collections.singletonList("application/pdf")));

        assertEquals("FileDownload{size=14, contentType=application/pdf}", download.toString());
    }
}
