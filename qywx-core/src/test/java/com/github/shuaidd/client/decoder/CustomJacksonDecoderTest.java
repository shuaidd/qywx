package com.github.shuaidd.client.decoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.material.WeChatMediaUploadResponse;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import feign.Request;
import feign.Response;
import feign.Util;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link CustomJacksonDecoder} 的单元测试：业务错误码转换、未知属性、404 与响应体缺失、关闭响应流
 *
 * @author ddshuai
 * date 2026-11-26 02:22
 **/
class CustomJacksonDecoderTest {

    private static final Request REQUEST = Request.create(Request.HttpMethod.GET,
            "http://contract.invalid/cgi-bin/get_api_domain_ip", Collections.emptyMap(), null, Util.UTF_8, null);

    private final CustomJacksonDecoder decoder = new CustomJacksonDecoder();

    /**
     * errcode 为 0 时返回响应，未知属性不报错
     */
    @Test
    void returnsResponseWhenErrCodeIsZero() throws IOException {
        Object result = decoder.decode(response(200,
                "{\"errcode\":0,\"errmsg\":\"ok\",\"ip_list\":[\"1.2.3.4\"],\"unknown_field\":1}"),
                ApiDomainIpResponse.class);

        assertTrue(result instanceof ApiDomainIpResponse);
        ApiDomainIpResponse response = (ApiDomainIpResponse) result;
        assertEquals("0", response.getErrCode());
        assertEquals("ok", response.getErrMsg());
        assertEquals(Collections.singletonList("1.2.3.4"), response.getIpList());
    }

    /**
     * 已知错误码：抛出带枚举错误码的 WeChatException
     */
    @Test
    void throwsWeChatExceptionForKnownErrCode() {
        WeChatException exception = assertThrows(WeChatException.class, () -> decoder.decode(
                response(200, "{\"errcode\":40001,\"errmsg\":\"invalid credential\"}"), BaseResponse.class));

        assertEquals(ErrorCode.ERROR_CODE_40001, exception.getErrorCode());
        assertEquals("40001", exception.getCode());
        assertEquals("invalid credential", exception.getMessage());
        assertEquals(ErrorCode.ERROR_CODE_40001.getErrorDesc(), exception.getMsg());
    }

    /**
     * 枚举里没有的错误码：抛出 UNKNOWN 的 WeChatException，并保留响应里的原始码
     */
    @Test
    void throwsUnknownWeChatExceptionForUnlistedErrCode() {
        WeChatException exception = assertThrows(WeChatException.class, () -> decoder.decode(
                response(200, "{\"errcode\":99999999,\"errmsg\":\"weird\"}"), BaseResponse.class));

        assertEquals(ErrorCode.UNKNOWN, exception.getErrorCode());
        assertEquals("99999999", exception.getCode());
        assertEquals("weird", exception.getMessage());
    }

    /**
     * 响应没有 errcode 时按成功处理（偏差 #14）
     */
    @Test
    void treatsMissingErrCodeAsSuccess() throws IOException {
        Object result = decoder.decode(response(200, "{\"errmsg\":\"ok\"}"), BaseResponse.class);

        assertTrue(result instanceof BaseResponse);
        assertNull(((BaseResponse) result).getErrCode());
        assertEquals("ok", ((BaseResponse) result).getErrMsg());
    }

    /**
     * 注入的 ObjectMapper 也会关闭 FAIL_ON_UNKNOWN_PROPERTIES
     */
    @Test
    void disablesFailOnUnknownPropertiesOnInjectedMapper() throws IOException {
        ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Object result = new CustomJacksonDecoder(mapper).decode(
                response(200, "{\"errcode\":0,\"unknown_field\":1}"), BaseResponse.class);

        assertTrue(result instanceof BaseResponse);
        assertFalse(mapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

    /**
     * 404：返回类型的空值；集合给空集合，普通对象给 null
     */
    @Test
    void returnsEmptyValueForNotFound() throws IOException {
        assertEquals(Collections.emptyList(), decoder.decode(response(404, "{\"errcode\":0}"), List.class));
        assertNull(decoder.decode(response(404, "{\"errcode\":0}"), BaseResponse.class));
    }

    /**
     * 没有响应体时返回 null
     */
    @Test
    void returnsNullWhenBodyIsMissing() throws IOException {
        assertNull(decoder.decode(response(200, null), BaseResponse.class));
    }

    /**
     * 响应流读取完毕后关闭
     */
    @Test
    void closesResponseBodyStream() throws IOException {
        AtomicBoolean closed = new AtomicBoolean();
        byte[] content = "{\"errcode\":0,\"errmsg\":\"ok\"}".getBytes(Util.UTF_8);
        Response response = Response.builder()
                .status(200)
                .reason("contract")
                .headers(Collections.emptyMap())
                .request(REQUEST)
                .body(new ByteArrayInputStream(content) {
                    @Override
                    public void close() throws IOException {
                        closed.set(true);
                        super.close();
                    }
                }, content.length)
                .build();

        decoder.decode(response, BaseResponse.class);

        assertTrue(closed.get());
    }

    /**
     * 临时素材上传响应的 created_at 是秒级时间戳字符串（文档 90253）：按秒解析成 Date
     * <p>
     * 回归：该字段曾缺少 {@code Long2DateDeserializer}，Jackson 默认把秒当毫秒，解析出来落在 1970 年
     */
    @Test
    void parsesMediaUploadCreatedAtAsSeconds() throws IOException {
        Object result = decoder.decode(response(200,
                "{\"errcode\":0,\"errmsg\":\"ok\",\"type\":\"image\",\"media_id\":\"MEDIA_ID\",\"created_at\":\"1795868555\"}"),
                WeChatMediaUploadResponse.class);

        assertTrue(result instanceof WeChatMediaUploadResponse);
        WeChatMediaUploadResponse response = (WeChatMediaUploadResponse) result;
        assertEquals("image", response.getType());
        assertEquals("MEDIA_ID", response.getMediaId());
        assertEquals(new Date(1795868555000L), response.getCreatedAt());
    }

    private static Response response(int status, String body) {
        Response.Builder builder = Response.builder()
                .status(status)
                .reason("contract")
                .headers(Collections.emptyMap())
                .request(REQUEST);
        if (body != null) {
            builder.body(body, Util.UTF_8);
        }
        return builder.build();
    }
}
