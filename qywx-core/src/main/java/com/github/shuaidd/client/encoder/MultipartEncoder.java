package com.github.shuaidd.client.encoder;

import com.github.shuaidd.client.annotation.Part;
import com.github.shuaidd.file.QywxFile;
import feign.MethodMetadata;
import feign.RequestTemplate;
import feign.Util;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URLConnection;
import java.util.Objects;
import java.util.UUID;

/**
 * 描述 文件上传的 multipart 编码：请求体为 {@link QywxFile} 时按 {@code multipart/form-data} 编码，其余请求委托给 JSON encoder
 * <p>
 * 替代 5.x 中依赖 Spring 的 {@code SpringFormEncoder}，报文格式逐字节保持一致：{@code \r\n} 分行、
 * {@code Content-Disposition: form-data; name=…; filename=…}、{@code Content-Type: <文件类型>}、
 * {@code Content-Transfer-Encoding: binary}，请求头为 {@code multipart/form-data; charset=UTF-8; boundary=…}（决策 #7）。
 * 表单字段名取自参数上的 {@link Part}，不再取调用方的表单字段名（决策 #15）。
 *
 * @author ddshuai
 * date 2026-11-26 00:04
 **/
public class MultipartEncoder implements Encoder {

    private static final String CRLF = "\r\n";

    private static final String BOUNDARY_PREFIX = "----qywx";

    private static final String DEFAULT_FILE_CONTENT_TYPE = "application/octet-stream";

    private final Encoder delegate;

    public MultipartEncoder(Encoder delegate) {
        this.delegate = Objects.requireNonNull(delegate, "delegate");
    }

    /**
     * {@link QywxFile} 按 multipart 编码，其它请求体交给 JSON encoder
     *
     * @param object   请求对象
     * @param bodyType 请求体在方法签名上的声明类型
     * @param template 请求模板
     * @throws EncodeException 缺少 {@link Part} 注解或写请求体失败
     */
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        if (!(object instanceof QywxFile)) {
            delegate.encode(object, bodyType, template);
            return;
        }

        QywxFile file = (QywxFile) object;
        String partName = partNameOf(template);
        String boundary = BOUNDARY_PREFIX + UUID.randomUUID().toString().replace("-", "");
        try {
            template.body(multipartBody(partName, boundary, file), Util.UTF_8);
        } catch (IOException e) {
            throw new EncodeException(e.getMessage(), e);
        }
        template.header("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);
    }

    /**
     * 取上传参数上的 {@link Part} 字段名
     */
    private static String partNameOf(RequestTemplate template) {
        MethodMetadata metadata = template.methodMetadata();
        Annotation[][] parameterAnnotations = metadata == null || metadata.method() == null
                ? new Annotation[0][0] : metadata.method().getParameterAnnotations();
        Integer bodyIndex = metadata == null ? null : metadata.bodyIndex();
        if (bodyIndex != null && bodyIndex < parameterAnnotations.length) {
            for (Annotation annotation : parameterAnnotations[bodyIndex]) {
                if (annotation instanceof Part && StringUtils.isNotEmpty(((Part) annotation).value())) {
                    return ((Part) annotation).value();
                }
            }
        }
        throw new EncodeException("上传参数缺少 @Part 注解，无法确定 multipart 字段名");
    }

    /**
     * 拼装 multipart 报文：头行 + 文件内容 + 结束边界
     */
    private static byte[] multipartBody(String partName, String boundary, QywxFile file) throws IOException {
        StringBuilder head = new StringBuilder();
        head.append("--").append(boundary).append(CRLF)
                .append("Content-Disposition: form-data; name=\"").append(partName)
                .append("\"; filename=\"").append(sanitizeFilename(file.getFilename())).append("\"").append(CRLF)
                .append("Content-Type: ").append(fileContentType(file)).append(CRLF)
                .append("Content-Transfer-Encoding: binary").append(CRLF).append(CRLF);

        ByteArrayOutputStream out = new ByteArrayOutputStream(
                head.length() + file.getContent().length + boundary.length() + 2 * CRLF.length() + 8);
        out.write(head.toString().getBytes(Util.UTF_8));
        out.write(file.getContent());
        out.write((CRLF + "--" + boundary + "--" + CRLF).getBytes(Util.UTF_8));
        return out.toByteArray();
    }

    /**
     * 文件类型：优先取调用方指定的，其次按文件名猜测，仍猜不到用 application/octet-stream
     */
    private static String fileContentType(QywxFile file) {
        if (StringUtils.isNotBlank(file.getContentType())) {
            return file.getContentType();
        }
        return StringUtils.defaultIfBlank(URLConnection.guessContentTypeFromName(file.getFilename()),
                DEFAULT_FILE_CONTENT_TYPE);
    }

    /**
     * 文件名里的引号与换行会破坏 Content-Disposition 头，直接去掉
     */
    private static String sanitizeFilename(String filename) {
        return filename.replace("\"", "").replace("\r", "").replace("\n", "");
    }
}
