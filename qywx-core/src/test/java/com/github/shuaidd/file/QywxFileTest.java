package com.github.shuaidd.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 描述 {@link QywxFile} 的单元测试：参数校验、本地文件读取与 Content-Type 探测
 *
 * @author ddshuai
 * date 2026-11-26 02:22
 **/
class QywxFileTest {

    private static final byte[] CONTENT = "contract-bytes".getBytes(StandardCharsets.UTF_8);

    @TempDir
    Path tempDir;

    /**
     * 内容、文件名与本地文件不允许为空
     */
    @Test
    void rejectsNullArguments() {
        assertEquals("content", assertThrows(NullPointerException.class,
                () -> new QywxFile(null, "a.bin")).getMessage());
        assertEquals("filename", assertThrows(NullPointerException.class,
                () -> new QywxFile(CONTENT, null)).getMessage());
        assertEquals("file", assertThrows(NullPointerException.class,
                () -> QywxFile.of((File) null)).getMessage());
    }

    /**
     * 空白的 Content-Type 视为未指定
     */
    @Test
    void treatsBlankContentTypeAsUnspecified() {
        assertNull(new QywxFile(CONTENT, "a.bin").getContentType());
        assertNull(new QywxFile(CONTENT, "a.bin", "  ").getContentType());
        assertEquals("text/plain", new QywxFile(CONTENT, "a.bin", "text/plain").getContentType());
    }

    /**
     * 读取本地文件：内容一致，Content-Type 由系统探测或按文件名猜测
     */
    @Test
    void readsLocalFile() throws IOException {
        Path path = tempDir.resolve("qywx-contract.png");
        Files.write(path, CONTENT);

        QywxFile file = QywxFile.of(path.toFile());

        assertEquals("qywx-contract.png", file.getFilename());
        assertEquals("image/png", file.getContentType());
        assertEquals(14, file.getContent().length);
        assertArrayEquals(CONTENT, file.getContent());
    }

    /**
     * toString 只描述文件名、类型与大小，不输出内容
     */
    @Test
    void describesFileWithoutLeakingContent() {
        QywxFile file = new QywxFile(CONTENT, "a.bin", "image/png");

        assertEquals("QywxFile{filename='a.bin', contentType=image/png, size=14}", file.toString());
        assertFalse(file.toString().contains("contract-bytes"));
    }
}
