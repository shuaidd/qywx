package com.github.shuaidd.file;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 描述 下载文件的落盘目标（仅当前线程有效）：替代 5.x 中随请求发送给服务端的 {@code downloadFilePath} 请求头与查询参数
 * <p>
 * {@code WeChatMediaDecoder} 在返回类型为 {@code java.io.File} 时读取这里的目标：
 * <ul>
 * <li>目标是已存在的目录：文件以服务端给出的文件名保存在该目录下</li>
 * <li>目标是其它路径：视为目标文件，按该路径保存，服务端文件名被忽略（与 5.x 的 {@code downloadFilePath} 语义一致）</li>
 * <li>未设置：保存到 {@code java.io.tmpdir}</li>
 * </ul>
 * 路径字符串为 null 或空白时一律视为未设置。
 * <p>
 * 手动设置后用完必须 {@link #clear()}，否则线程池复用线程时会串到其它请求；推荐 {@link #scope(String)}（try-with-resources），
 * 关闭时自动恢复为之前的值。
 * <p>
 * 经 Feign 代理调用下载接口（如 {@code WeChatMediaClient#downloadFile}）时不必手动设置：
 * {@code TokenRetryInvocationHandlerFactory} 会在调用期间（含令牌失效后的重试）打开作用域，
 * 把 {@code @Param("downloadFilePath")} 的实参交给这里，调用结束后恢复为之前的值。
 *
 * @author ddshuai
 * date 2026-11-26 04:10
 **/
public final class DownloadContext {

    /**
     * 下载接口上「落盘目标」参数的名称（{@code @Param("downloadFilePath")}）：只在调用期间传给这里，
     * 不进请求模板、查询参数、请求头或表单体；{@code QywxContract} 据此把它从表单参数里移除
     */
    public static final String DOWNLOAD_FILE_PATH = "downloadFilePath";

    private static final ThreadLocal<Path> TARGET = new ThreadLocal<>();

    private DownloadContext() {
    }

    /**
     * 当前的落盘目标；未设置时为 null
     *
     * @return 目标目录或目标文件
     */
    public static Path get() {
        return TARGET.get();
    }

    /**
     * 设置当前线程的落盘目标
     *
     * @param target 目标目录或目标文件；null 或空白字符串等同于 {@link #clear()}
     */
    public static void set(String target) {
        set(StringUtils.isBlank(target) ? null : Paths.get(target));
    }

    /**
     * 设置当前线程的落盘目标
     *
     * @param target 目标目录或目标文件；null 等同于 {@link #clear()}
     */
    public static void set(Path target) {
        if (target == null) {
            clear();
        } else {
            TARGET.set(target);
        }
    }

    /**
     * 清除当前线程的落盘目标
     */
    public static void clear() {
        TARGET.remove();
    }

    /**
     * 打开一个作用域：作用域内的落盘目标为 {@code target}，{@link Scope#close()} 时恢复为打开之前的值
     *
     * @param target 目标目录或目标文件；null 或空白字符串表示作用域内不设置目标
     * @return 作用域，配合 try-with-resources 使用
     */
    public static Scope scope(String target) {
        return scope(StringUtils.isBlank(target) ? null : Paths.get(target));
    }

    /**
     * 打开一个作用域：作用域内的落盘目标为 {@code target}，{@link Scope#close()} 时恢复为打开之前的值
     *
     * @param target 目标目录或目标文件；null 表示作用域内不设置目标
     * @return 作用域，配合 try-with-resources 使用
     */
    public static Scope scope(Path target) {
        return new Scope(target);
    }

    /**
     * 按当前目标与文件名算出实际落盘路径：目标是目录时拼接文件名，其它路径原样使用，未设置时用系统临时目录
     *
     * @param fileName 服务端给出的文件名（不含目录部分）
     * @return 落盘路径
     */
    public static Path resolve(String fileName) {
        Path target = TARGET.get();
        if (target == null) {
            return Paths.get(System.getProperty("java.io.tmpdir"), fileName);
        }
        return Files.isDirectory(target) ? target.resolve(fileName) : target;
    }

    /**
     * try-with-resources 作用域：构造时设置目标，关闭时恢复为之前的值
     */
    public static final class Scope implements AutoCloseable {

        private final Path previous;

        private boolean closed;

        private Scope(Path target) {
            this.previous = TARGET.get();
            set(target);
        }

        @Override
        public void close() {
            if (closed) {
                return;
            }
            closed = true;
            set(previous);
        }
    }
}
