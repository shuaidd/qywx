package com.github.shuaidd.file;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * 描述 下载到内存的文件：替代 5.x 中 Spring 的 {@code ResponseEntity<byte[]>}，内容为字节数组，附带下载响应的响应头
 * <p>
 * 5.x 只用到 {@code getBody()} 与 {@code getHeaders()}，分别对应 {@link #getContent()} 与 {@link #getHeaders()}
 * （键统一转为小写，值保持多值）。状态码不再保留：5.x 固定按 200 返回，非 2xx 的响应由 Feign 直接抛出异常，不会进入 decoder。
 * <p>
 * 直接持有下载得到的字节数组，构造后请勿再修改其内容。
 *
 * @author ddshuai
 * date 2026-11-26 04:10
 **/
public class FileDownload {

    /** 响应头名称：Content-Disposition */
    public static final String CONTENT_DISPOSITION = "content-disposition";

    /** 响应头名称：Content-Type */
    public static final String CONTENT_TYPE = "content-type";

    private final byte[] content;

    private final Map<String, List<String>> headers;

    /**
     * @param content 下载内容；直接持有该数组，不做复制，构造后请勿再修改
     * @param headers 下载响应的响应头；null 表示没有响应头。键转为小写，值复制为不可变列表
     */
    public FileDownload(byte[] content, Map<String, ? extends Collection<String>> headers) {
        this.content = Objects.requireNonNull(content, "content");
        this.headers = copyHeaders(headers);
    }

    /**
     * 下载内容：没有响应体时为空数组。直接返回内部数组，请勿修改
     *
     * @return 下载内容
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 下载响应的全部响应头：键为小写，值为不可变列表
     *
     * @return 响应头
     */
    public Map<String, List<String>> getHeaders() {
        return headers;
    }

    /**
     * 取指定响应头的首个值；没有时返回 null。名称不区分大小写
     *
     * @param name 响应头名称
     * @return 首个值
     */
    public String getHeader(String name) {
        List<String> values = name == null ? null : headers.get(name.toLowerCase(Locale.ROOT));
        return values == null || values.isEmpty() ? null : values.get(0);
    }

    /**
     * Content-Type
     *
     * @return Content-Type；没有时为 null
     */
    public String getContentType() {
        return getHeader(CONTENT_TYPE);
    }

    /**
     * 服务端在 Content-Disposition 里给出的文件名（优先 RFC 5987 的 {@code filename*}）；没有时返回 null
     *
     * @return 文件名
     */
    public String getFileName() {
        return parseFileName(getHeader(CONTENT_DISPOSITION));
    }

    @Override
    public String toString() {
        return "FileDownload{size=" + content.length + ", contentType=" + getContentType() + '}';
    }

    /**
     * 从 Content-Disposition 解析文件名：优先 RFC 5987 的 {@code filename*=UTF-8''%E4%B8%AD.bin}，
     * 其次 {@code filename="…"}（兼容未加引号与反斜杠转义的写法）；结果只保留文件名部分，不含目录与各平台的非法字符
     *
     * @param contentDisposition Content-Disposition 响应头的值
     * @return 文件名；解析不到时为 null
     */
    public static String parseFileName(String contentDisposition) {
        if (StringUtils.isBlank(contentDisposition)) {
            return null;
        }
        Map<String, String> params = parameters(contentDisposition);
        String fileName = decodeExtended(params.get("filename*"));
        if (StringUtils.isBlank(fileName)) {
            fileName = params.get("filename");
        }
        if (StringUtils.isBlank(fileName)) {
            return null;
        }
        String sanitized = sanitize(fileName);
        return StringUtils.isBlank(sanitized) ? null : sanitized;
    }

    /**
     * 复制响应头：键转小写，值为不可变列表
     */
    private static Map<String, List<String>> copyHeaders(Map<String, ? extends Collection<String>> source) {
        Map<String, List<String>> copy = new LinkedHashMap<>();
        if (source == null) {
            return Collections.unmodifiableMap(copy);
        }
        for (Map.Entry<String, ? extends Collection<String>> header : source.entrySet()) {
            if (header.getKey() == null || header.getValue() == null) {
                continue;
            }
            copy.put(header.getKey().toLowerCase(Locale.ROOT),
                    Collections.unmodifiableList(new ArrayList<>(header.getValue())));
        }
        return Collections.unmodifiableMap(copy);
    }

    /**
     * 按分号拆出 name=value 参数（引号内的分号不拆）；键转小写，带引号的值去掉引号
     */
    private static Map<String, String> parameters(String header) {
        Map<String, String> params = new LinkedHashMap<>();
        StringBuilder current = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < header.length(); i++) {
            char c = header.charAt(i);
            if (c == '\\' && quoted && i + 1 < header.length()) {
                current.append(c).append(header.charAt(++i));
            } else if (c == '"') {
                quoted = !quoted;
                current.append(c);
            } else if (c == ';' && !quoted) {
                addParameter(params, current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        addParameter(params, current.toString());
        return params;
    }

    private static void addParameter(Map<String, String> params, String part) {
        int index = part.indexOf('=');
        if (index < 0) {
            return;
        }
        String value = part.substring(index + 1).trim();
        params.put(part.substring(0, index).trim().toLowerCase(Locale.ROOT),
                value.startsWith("\"") ? unquote(value) : value);
    }

    /**
     * 去掉首尾引号，只还原 {@code \"} 与 {@code \\} 两种转义：其它反斜杠原样保留（文件名里的 {@code \} 多半是 Windows 的目录分隔符，
     * 交给 {@link #sanitize(String)} 去掉目录），没有结束引号时按未加引号处理
     */
    private static String unquote(String value) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c == '"') {
                break;
            }
            if (c == '\\' && i + 1 < value.length()) {
                char next = value.charAt(i + 1);
                if (next == '"' || next == '\\') {
                    c = next;
                    i++;
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    /**
     * 解码 RFC 5987 的 ext-value（charset'lang'percent-encoded）；格式或字符集不合法时返回 null
     */
    private static String decodeExtended(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        int first = value.indexOf('\'');
        int second = first < 0 ? -1 : value.indexOf('\'', first + 1);
        if (second < 0) {
            return null;
        }
        String charset = value.substring(0, first);
        try {
            Charset encoding = charset.isEmpty() ? StandardCharsets.UTF_8 : Charset.forName(charset);
            //URLDecoder 把 + 当作空格，RFC 5987 里的 + 是字面量，先转义
            return URLDecoder.decode(value.substring(second + 1).replace("+", "%2B"), encoding.name());
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            return null;
        }
    }

    /**
     * 只保留文件名部分：去掉目录（/ 与 \ 都按分隔符处理）、控制字符与 Windows 不允许的字符 : * ? " < > |
     */
    static String sanitize(String fileName) {
        String name = fileName.replace('\\', '/');
        name = name.substring(name.lastIndexOf('/') + 1).trim();
        return name.replaceAll("[\\p{Cntrl}:*?\"<>|]", "_").trim();
    }
}
