package com.github.shuaidd.client.decoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.AbstractBaseResponse;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 描述 把企业微信的响应反序列化为返回类型，并把业务错误码转成 {@link WeChatException}
 * <p>
 * 替代 5.x 中继承 {@code feign.jackson.JacksonDecoder} 的同名类，规则保持一致：
 * <ul>
 * <li>404 返回返回类型的空值，没有响应体时返回 null</li>
 * <li>响应继承 {@link AbstractBaseResponse} 时校验 errcode：为 0 返回，已知错误码抛 {@code WeChatException(errMsg, errorCode)}，
 * 未知错误码抛 {@code WeChatException.unknown(code, msg)}</li>
 * <li>未知属性不报错</li>
 * </ul>
 * 与 5.x 的差异：errcode 为 null 时视为成功（5.x 在此 NPE，偏差 #14）。
 *
 * @author ddshuai
 * date 2026-11-26 00:03
 **/
public class CustomJacksonDecoder implements Decoder {

    private static final Logger logger = LoggerFactory.getLogger(CustomJacksonDecoder.class);

    private final ObjectMapper objectMapper;

    public CustomJacksonDecoder() {
        this(new ObjectMapper());
    }

    public CustomJacksonDecoder(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper")
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 反序列化响应并校验业务状态
     *
     * @param response 响应
     * @param type     返回类型
     * @return 反序列化结果
     * @throws IOException 读取响应体失败
     */
    @Override
    public Object decode(Response response, Type type) throws IOException {
        Object result = doDecode(response, type);
        if (result instanceof AbstractBaseResponse) {
            AbstractBaseResponse baseResponse = (AbstractBaseResponse) result;
            String errCode = baseResponse.getErrCode();
            //响应模型没有带 errcode 时按成功处理
            if (errCode == null || ErrorCode.ERROR_CODE_0.getErrorCode().equals(errCode)) {
                return result;
            }

            if (logger.isInfoEnabled()) {
                logger.info("获取到的返回结果状态--{}--{}", errCode, baseResponse.getErrMsg());
            }

            ErrorCode errorCode = ErrorCode.errorCode(errCode);
            if (errorCode == ErrorCode.UNKNOWN) {
                throw WeChatException.unknown(errCode, baseResponse.getErrMsg());
            }
            throw new WeChatException(baseResponse.getErrMsg(), errorCode);
        }
        return result;
    }

    /**
     * 读取响应体并反序列化；流在读取完毕后关闭
     */
    private Object doDecode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        }
        if (response.body() == null) {
            return null;
        }

        String body;
        try (InputStream in = response.body().asInputStream()) {
            body = IOUtils.toString(in, Util.UTF_8);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("从接口 {} 获取到企业微信的响应数据如下\n {}", requestPath(response), body);
        }
        return objectMapper.readValue(body, objectMapper.constructType(type));
    }

    /**
     * 取请求路径用于日志；测试或自定义 Response 可能没有 request
     */
    private static String requestPath(Response response) {
        if (response.request() == null || response.request().requestTemplate() == null) {
            return null;
        }
        return response.request().requestTemplate().path();
    }
}
