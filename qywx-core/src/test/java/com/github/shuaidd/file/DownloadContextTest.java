package com.github.shuaidd.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * 描述 {@link DownloadContext} 的单元测试：设置与清除、空白字符串按未设置处理、作用域的嵌套恢复与幂等关闭、目标路径解析、线程隔离
 *
 * @author ddshuai
 * date 2026-11-26 04:26
 **/
class DownloadContextTest {

    @TempDir
    Path tempDir;

    @AfterEach
    void clear() {
        DownloadContext.clear();
    }

    /**
     * set 接受路径字符串与 Path；传 null 等同于 clear
     */
    @Test
    void setsAndClearsTarget() {
        assertNull(DownloadContext.get());

        DownloadContext.set(tempDir.toString());
        assertEquals(tempDir, DownloadContext.get());

        DownloadContext.set(tempDir);
        assertEquals(tempDir, DownloadContext.get());

        DownloadContext.set((String) null);
        assertNull(DownloadContext.get());

        DownloadContext.set(tempDir);
        DownloadContext.set((Path) null);
        assertNull(DownloadContext.get());

        DownloadContext.set(tempDir);
        DownloadContext.clear();
        assertNull(DownloadContext.get());
    }

    /**
     * 作用域嵌套：关闭内层恢复外层，关闭最外层恢复进入之前的值
     */
    @Test
    void scopeRestoresPreviousTarget() {
        DownloadContext.set(tempDir.toString());
        Path outer = tempDir.resolve("outer");
        Path innermost = tempDir.resolve("innermost");

        try (DownloadContext.Scope outerScope = DownloadContext.scope(outer)) {
            assertEquals(outer, DownloadContext.get());
            try (DownloadContext.Scope innerScope = DownloadContext.scope(innermost)) {
                assertEquals(innermost, DownloadContext.get());
            }
            assertEquals(outer, DownloadContext.get());
        }
        assertEquals(tempDir, DownloadContext.get());
    }

    /**
     * 进入作用域前没有目标时，关闭即清空；二次关闭不再改动当前值
     */
    @Test
    void scopeClearsAndCloseIsIdempotent() {
        try (DownloadContext.Scope scope = DownloadContext.scope(tempDir)) {
            assertEquals(tempDir, DownloadContext.get());
            scope.close();
            assertNull(DownloadContext.get());

            DownloadContext.set(tempDir);
            scope.close();
            assertEquals(tempDir, DownloadContext.get(), "二次关闭不应改动之后的值");
        }
        assertEquals(tempDir, DownloadContext.get());
    }

    /**
     * scope(null) 表示作用域内不设目标
     */
    @Test
    void nullScopeClearsTarget() {
        DownloadContext.set(tempDir);

        try (DownloadContext.Scope ignored = DownloadContext.scope((String) null)) {
            assertNull(DownloadContext.get());
        }
        assertEquals(tempDir, DownloadContext.get());
    }

    /**
     * 目标是已存在的目录时拼接文件名；其它路径（含相对路径、父目录不存在的路径）原样使用；未设置时用系统临时目录
     */
    @Test
    void resolvesTarget() throws IOException {
        assertEquals(Paths.get(System.getProperty("java.io.tmpdir"), "report.pdf"),
                DownloadContext.resolve("report.pdf"));

        Path existingDirectory = tempDir.resolve("sub");
        Files.createDirectories(existingDirectory);
        DownloadContext.set(existingDirectory);
        assertEquals(existingDirectory.resolve("report.pdf"), DownloadContext.resolve("report.pdf"));

        Path missingFile = tempDir.resolve("missing").resolve("file.bin");
        DownloadContext.set(missingFile);
        assertEquals(missingFile, DownloadContext.resolve("report.pdf"));

        DownloadContext.set("plain-name");
        assertEquals(Paths.get("plain-name"), DownloadContext.resolve("report.pdf"));
    }

    /**
     * 目标只在当前线程有效
     */
    @Test
    void targetIsThreadLocal() throws Exception {
        DownloadContext.set(tempDir);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            Future<Path> other = executor.submit(DownloadContext::get);
            assertNull(other.get());
        } finally {
            executor.shutdownNow();
        }
        assertEquals(tempDir, DownloadContext.get());
    }

    /**
     * set 传入 null 或空白字符串等同于 clear
     */
    @ParameterizedTest(name = "[{index}] target=[{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void blankStringClearsTarget(String target) {
        DownloadContext.set(tempDir.toString());

        DownloadContext.set(target);

        assertNull(DownloadContext.get());
    }

    /**
     * scope 传入 null 或空白字符串：作用域内不设目标，关闭后恢复进入之前的值
     */
    @ParameterizedTest(name = "[{index}] target=[{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    void blankScopeClearsTargetInside(String target) {
        DownloadContext.set(tempDir);

        try (DownloadContext.Scope ignored = DownloadContext.scope(target)) {
            assertNull(DownloadContext.get());
        }

        assertEquals(tempDir, DownloadContext.get());
    }
}
