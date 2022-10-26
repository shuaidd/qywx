package com.github.shuaidd.client.decoder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.AbstractBaseResponse;
import feign.Response;
import feign.jackson.JacksonDecoder;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

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
        Object result = super.decode(response, type);
        if (result instanceof AbstractBaseResponse) {
            AbstractBaseResponse abstractBaseResponse = (AbstractBaseResponse) result;

            if (abstractBaseResponse.getErrCode().equals(ErrorCode.ERROR_CODE_0.getErrorCode())) {
                if (logger.isDebugEnabled()) {
                    logger.debug("从接口 {} 获取到企业微信的响应数据--->\n {}",response.request().requestTemplate().path(),objectMapper.writeValueAsString(result));
                }
                return result;
            }

            if (logger.isInfoEnabled()) {
                logger.info("获取到的返回结果状态--{}--{}", abstractBaseResponse.getErrCode(), abstractBaseResponse.getErrMsg());
            }

            throw new WeChatException(abstractBaseResponse.getErrMsg(), ErrorCode.errorCode(abstractBaseResponse.getErrCode()));
        }
        return result;
    }
}
