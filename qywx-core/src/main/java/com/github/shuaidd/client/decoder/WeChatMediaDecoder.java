package com.github.shuaidd.client.decoder;

import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.file.DownloadContext;
import com.github.shuaidd.file.FileDownload;
import feign.Response;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * 描述 素材下载的响应解码：按 client 方法的返回类型决定「读进内存」还是「写入本地文件」，其余类型交给 {@link CustomJacksonDecoder}
 * <p>
 * 替代 5.x 中依赖 Spring {@code ResponseEntity} 的同名类。与 5.x 一致：
 * <ul>
 * <li>返回 {@link FileDownload}（5.x 为 {@code ResponseEntity<byte[]>}）：响应体读进内存，附带响应头</li>
 * <li>返回 {@link File}：写入本地文件。目标取自 {@link DownloadContext}：目标是已存在的目录时按服务端给出的文件名保存，
 * 否则把目标当作文件本身（与 5.x 的 {@code downloadFilePath} 语义一致），未设置时用系统临时目录</li>
 * <li>日志文案不变；响应头带 {@code error-code} 且不为 0 时抛 {@link WeChatException}，对外仍是 Feign 包装后的 {@code DecodeException}</li>
 * </ul>
 * 与 5.x 的差异：
 * <ul>
 * <li>文件名优先取 RFC 5987 的 {@code filename*}，其次 {@code filename}，只保留文件名部分（去掉目录与非法字符）；
 * 纯点名（{@code .}、{@code ..}）拒绝落盘；没有 Content-Disposition 时用 {@link #DEFAULT_FILE_NAME}（偏差 #5）</li>
 * <li>响应流与输出流读取后即关闭，并且边读边写：5.x 两个流都不关闭，Windows 上下载后的文件会被锁到 JVM 退出</li>
 * <li>目标路径不再从请求头读取（偏差 #4）</li>
 * <li>错误码不在枚举里时抛 {@code WeChatException.unknown}（保留原始码）；缺少 {@code error-msg} 时不再 NPE；响应头名称不区分大小写</li>
 * <li>读进内存与落盘之前先校验 {@code error-code}，下载出错时不读取响应体、不创建文件，并关闭响应体</li>
 * </ul>
 *
 * @author ddshuai
 * date 2026-11-26 04:20
 **/
public class WeChatMediaDecoder implements Decoder {

    private static final Logger logger = LoggerFactory.getLogger(WeChatMediaDecoder.class);

    /** 下载出错时企业微信放在响应头里的错误码 */
    public static final String ERROR_CODE = "error-code";

    /** 下载出错时企业微信放在响应头里的错误描述 */
    public static final String ERROR_MSG = "error-msg";

    /** 响应头没有给出文件名时使用的文件名 */
    public static final String DEFAULT_FILE_NAME = "download";

    /** 返回类型 {@code java.io.File} */
    public static final String FILE_TYPE = File.class.getTypeName();

    private static final String FILE_DOWNLOAD_TYPE = FileDownload.class.getTypeName();

    /** 只由点组成的文件名（{@code .}、{@code ..} 等）：落盘会指向目录本身，直接拒绝 */
    private static final Pattern DOT_NAME = Pattern.compile("\\.+");

    private final Decoder delegate;

    /**
     * 非下载类型的响应交给默认的 {@link CustomJacksonDecoder}
     */
    public WeChatMediaDecoder() {
        this(new CustomJacksonDecoder());
    }

    /**
     * @param delegate 非下载类型的响应交给它解码；null 时抛出 {@link NullPointerException}
     */
    public WeChatMediaDecoder(Decoder delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    /**
     * 按返回类型解码：{@link FileDownload} 读进内存，{@link File} 写入本地文件，其余交给 {@link CustomJacksonDecoder}
     * <p>
     * 下载分支出错时先关闭响应体再抛出：响应体已由这里接管，Feign 不会再读它
     *
     * @param response 响应
     * @param type     返回类型
     * @return 下载结果
     * @throws IOException 读取响应体或写入文件失败
     */
    @Override
    public Object decode(Response response, Type type) throws IOException {
        String typeName = type.getTypeName();
        if (!FILE_DOWNLOAD_TYPE.equals(typeName) && !FILE_TYPE.equals(typeName)) {
            return delegate.decode(response, type);
        }
        try {
            return FILE_DOWNLOAD_TYPE.equals(typeName) ? decodeInMemory(response) : decodeToFile(response);
        } catch (IOException | RuntimeException e) {
            //下载分支接管了响应体：出错时 Feign 不会再读它，这里负责关闭
            IOUtils.closeQuietly(response.body());
            throw e;
        }
    }

    /**
     * 下载到内存：先校验响应头，再把响应体读成字节数组
     */
    private FileDownload decodeInMemory(Response response) throws IOException {
        Map<String, List<String>> headers = checkDownloadHeaders(response);
        byte[] content = new byte[0];
        if (response.body() != null) {
            try (InputStream in = response.body().asInputStream()) {
                content = IOUtils.toByteArray(in);
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("下载的文件大小为--{}", content.length);
        }
        return new FileDownload(content, headers);
    }

    /**
     * 下载到本地文件：目标由 {@link DownloadContext} 给出，响应体边读边写，两个流用完即关
     */
    private File decodeToFile(Response response) throws IOException {
        Map<String, List<String>> headers = checkDownloadHeaders(response);
        String fileName = StringUtils.defaultIfBlank(
                FileDownload.parseFileName(first(headers, FileDownload.CONTENT_DISPOSITION)), DEFAULT_FILE_NAME);
        if (DOT_NAME.matcher(fileName).matches()) {
            throw new IOException("企业微信返回的文件名不合法：" + fileName);
        }

        Path target = DownloadContext.resolve(fileName);
        if (logger.isInfoEnabled()) {
            logger.info("待下载的文件路径是--{}---企业微信端文件名称-{}", target, fileName);
        }
        try (InputStream in = bodyStream(response); OutputStream out = Files.newOutputStream(target)) {
            IOUtils.copyLarge(in, out);
        }
        return target.toFile();
    }

    /**
     * 响应体输入流；没有响应体时给空流
     */
    private static InputStream bodyStream(Response response) throws IOException {
        return response.body() == null ? new ByteArrayInputStream(new byte[0]) : response.body().asInputStream();
    }

    /**
     * 复制响应头（键转小写）并逐个打印；{@code error-code} 不为 0 时抛出 {@link WeChatException}
     */
    private static Map<String, List<String>> checkDownloadHeaders(Response response) {
        Map<String, List<String>> headers = new LinkedHashMap<>();
        for (Map.Entry<String, Collection<String>> header : response.headers().entrySet()) {
            if (header.getKey() == null || header.getValue() == null) {
                continue;
            }
            List<String> values = new ArrayList<>(header.getValue());
            headers.put(header.getKey().toLowerCase(Locale.ROOT), values);
            if (logger.isInfoEnabled() && !values.isEmpty()) {
                logger.info("请求头信息--{}-->{}", header.getKey(), values.get(0));
            }
        }

        String errorCode = first(headers, ERROR_CODE);
        if (errorCode == null || ErrorCode.ERROR_CODE_0.getErrorCode().equals(errorCode)) {
            return headers;
        }

        String errorMsg = first(headers, ERROR_MSG);
        logger.error("文件下载失败-->error-code-->{}-error-msg--->{}", errorCode, errorMsg);
        ErrorCode known = ErrorCode.errorCode(errorCode);
        throw known == ErrorCode.UNKNOWN
                ? WeChatException.unknown(errorCode, errorMsg)
                : new WeChatException(errorMsg, known);
    }

    /**
     * 取响应头的首个值；没有时返回 null
     */
    private static String first(Map<String, List<String>> headers, String name) {
        List<String> values = headers.get(name);
        return values == null || values.isEmpty() ? null : values.get(0);
    }
}
