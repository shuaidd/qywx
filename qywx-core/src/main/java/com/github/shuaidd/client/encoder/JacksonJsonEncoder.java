package com.github.shuaidd.client.encoder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import feign.RequestTemplate;
import feign.Util;
import feign.codec.EncodeException;
import feign.codec.Encoder;

import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 描述 把请求对象序列化为 JSON，按 client 选择两套输出格式（逐字节复刻 5.x 的线上请求，决策 #7）
 * <p>
 * 两套格式由工厂方法固定，encoder 内部不分支，装配时按 client 选择：
 * <ul>
 * <li>{@link #indented()}：25 个业务 client。5.x 走 feign-jackson 的默认 ObjectMapper：NON_NULL、缩进输出，
 * Content-Type 为 {@code application/x-www-form-urlencoded}（5.x 由 JDK 对 POST 请求默认补的头）。
 * 另外关闭 FAIL_ON_EMPTY_BEANS，修复无字段请求类无法发送的问题（偏差 #8）</li>
 * <li>{@link #compact()}：{@code ServiceProviderClient}。5.x 走 SpringEncoder + Boot 默认 ObjectMapper：紧凑 JSON、
 * Content-Type 为 {@code application/json}</li>
 * </ul>
 *
 * @author ddshuai
 * date 2026-11-26 00:04
 **/
public class JacksonJsonEncoder implements Encoder {

    /** 业务 client 的 Content-Type，与 5.x 线上请求保持一致 */
    public static final String FORM_CONTENT_TYPE = "application/x-www-form-urlencoded";

    /** ServiceProviderClient 的 Content-Type，与 5.x 线上请求保持一致 */
    public static final String JSON_CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper;

    private final String contentType;

    private JacksonJsonEncoder(ObjectMapper objectMapper, String contentType) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper");
        this.contentType = contentType;
    }

    /**
     * 业务 client 用的格式：NON_NULL + 缩进 JSON + {@code application/x-www-form-urlencoded}
     *
     * @return JacksonJsonEncoder
     */
    public static JacksonJsonEncoder indented() {
        return new JacksonJsonEncoder(new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS), FORM_CONTENT_TYPE);
    }

    /**
     * ServiceProviderClient 用的格式：紧凑 JSON + {@code application/json}
     *
     * @return JacksonJsonEncoder
     */
    public static JacksonJsonEncoder compact() {
        return new JacksonJsonEncoder(new ObjectMapper()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS), JSON_CONTENT_TYPE);
    }

    /**
     * 序列化请求体并写入 Content-Type（同名头只保留这一个值）
     *
     * @param object   请求对象
     * @param bodyType 请求体在方法签名上的声明类型
     * @param template 请求模板
     * @throws EncodeException 序列化失败
     */
    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
        try {
            byte[] body = objectMapper.writerFor(objectMapper.constructType(bodyType)).writeValueAsBytes(object);
            template.body(body, Util.UTF_8);
        } catch (JsonProcessingException e) {
            throw new EncodeException(e.getMessage(), e);
        }
        template.header("Content-Type", contentType);
    }
}
