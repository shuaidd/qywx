package com.github.shuaidd.client.decoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.AbstractBaseResponse;
import feign.Response;
import feign.Util;
import feign.jackson.JacksonDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * 描述 处理token失效的问题
 *
 * @author ddshuai
 * date 2021-07-16 12:30
 **/
public class CustomJacksonDecoder extends JacksonDecoder {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Object result = doDecode(response, type);
        if (result instanceof AbstractBaseResponse abstractBaseResponse) {
            if (abstractBaseResponse.getErrCode().equals(ErrorCode.ERROR_CODE_0.getErrorCode())) {
                return result;
            }

            if (logger.isInfoEnabled()) {
                logger.info("获取到的返回结果状态--{}--{}", abstractBaseResponse.getErrCode(), abstractBaseResponse.getErrMsg());
            }

            ErrorCode errorCode =  ErrorCode.errorCode(abstractBaseResponse.getErrCode());
            if (errorCode == ErrorCode.UNKNOWN) {
                throw WeChatException.unknown(abstractBaseResponse.getErrCode(), abstractBaseResponse.getErrMsg());
            }
            throw new WeChatException(abstractBaseResponse.getErrMsg(),errorCode);
        }
        return result;
    }

    public Object doDecode(Response response, Type type) throws IOException {
        if (response.status() == 404) {
            return Util.emptyValueOf(type);
        } else if (response.body() == null) {
            return null;
        } else {
            InputStream in = response.body().asInputStream();
            try {
                String res = IOUtils.toString(in, StandardCharsets.UTF_8);
                if (logger.isDebugEnabled()) {
                    logger.debug("从接口 {} 获取到企业微信的响应数据如下\n {}",response.request().requestTemplate().path(),res);
                }
                return objectMapper.readValue(res, objectMapper.constructType(type));
            } catch (RuntimeJsonMappingException var5) {
                if (var5.getCause() != null && var5.getCause() instanceof IOException) {
                    throw (IOException) var5.getCause();
                } else {
                    throw var5;
                }
            }
        }
    }
}
