package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.file.FileDownload;
import com.github.shuaidd.file.QywxFile;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 {@link SpringFileBridge} 的转换守卫：文件名回退链、空白 content-type 规范化、下载的头与体、null 参数
 *
 * @author ddshuai
 * date 2026-11-28 02:00
 **/
public class SpringFileBridgeTest {

    private static final byte[] BYTES = "pdf-bytes".getBytes(StandardCharsets.UTF_8);

    /**
     * 有原始文件名就用它，content-type 原样带过去，内容数组不拷贝
     */
    @Test
    public void convertsUploadWithOriginalFilename() throws IOException {
        QywxFile file = SpringFileBridge.toQywxFile(
                new MockMultipartFile("upload", "report.pdf", "application/pdf", BYTES));

        assertEquals("report.pdf", file.getFilename());
        assertEquals("application/pdf", file.getContentType());
        assertSame(BYTES, file.getContent());
    }

    /**
     * 原始文件名是 null / 空串 / 空白时退回表单字段名，字段名也没有文本时退回 DEFAULT_FILENAME
     * （{@code MockMultipartFile} 不接受空串字段名，所以这一档用空白串）
     */
    @Test
    public void fallsBackAlongTheFilenameChain() throws IOException {
        assertEquals("field-1", SpringFileBridge.toQywxFile(
                new MockMultipartFile("field-1", null, "text/plain", BYTES)).getFilename());
        assertEquals("field-2", SpringFileBridge.toQywxFile(
                new MockMultipartFile("field-2", "", "text/plain", BYTES)).getFilename());
        assertEquals("field-3", SpringFileBridge.toQywxFile(
                new MockMultipartFile("field-3", "  ", "text/plain", BYTES)).getFilename());
        assertEquals(SpringFileBridge.DEFAULT_FILENAME, SpringFileBridge.toQywxFile(
                new MockMultipartFile("  ", "  ", "text/plain", BYTES)).getFilename());
    }

    /**
     * 空白 content-type 与两参构造的 MultipartFile 都规范成 null，和 core 的 QywxFile 一致
     */
    @Test
    public void normalizesBlankContentType() throws IOException {
        QywxFile blank = SpringFileBridge.toQywxFile(new MockMultipartFile("upload", "a.txt", "  ", BYTES));
        assertEquals("a.txt", blank.getFilename());
        assertNull(blank.getContentType());

        QywxFile twoArgs = SpringFileBridge.toQywxFile(new MockMultipartFile("upload", BYTES));
        assertEquals("upload", twoArgs.getFilename());
        assertNull(twoArgs.getContentType());
    }

    /**
     * 下载：200 + 头逐条带过去（多值头保持顺序）+ 体
     */
    @Test
    public void convertsDownloadToOkResponse() {
        LinkedHashMap<String, LinkedList<String>> headers = new LinkedHashMap<String, LinkedList<String>>();
        headers.put("content-type", new LinkedList<String>(Collections.singletonList("application/pdf")));
        headers.put("set-cookie", new LinkedList<String>(Arrays.asList("a=1", "b=2")));
        FileDownload download = new FileDownload(BYTES, headers);

        ResponseEntity<byte[]> response = SpringFileBridge.toResponseEntity(download);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonList("application/pdf"), response.getHeaders().get("content-type"));
        assertEquals(Arrays.asList("a=1", "b=2"), response.getHeaders().get("set-cookie"));
        assertArrayEquals(BYTES, response.getBody());
    }

    @Test
    public void rejectsNullArguments() {
        NullPointerException fileError = assertThrows(NullPointerException.class, () -> SpringFileBridge.toQywxFile(null));
        assertEquals("file", fileError.getMessage());

        NullPointerException downloadError = assertThrows(NullPointerException.class,
                () -> SpringFileBridge.toResponseEntity(null));
        assertEquals("download", downloadError.getMessage());
    }
}
