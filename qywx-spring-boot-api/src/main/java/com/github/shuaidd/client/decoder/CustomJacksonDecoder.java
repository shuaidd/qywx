package com.github.shuaidd.client.decoder;

import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.AbstractBaseResponse;
import feign.Response;
import feign.jackson.JacksonDecoder;
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

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Object result = super.decode(response, type);
        if (result instanceof AbstractBaseResponse) {
            AbstractBaseResponse abstractBaseResponse = (AbstractBaseResponse) result;

            if (abstractBaseResponse.getErrCode().equals(ErrorCode.ERROR_CODE_0.getErrorCode())) {
                return result;
            }

            if (logger.isInfoEnabled()) {
                logger.info("获取到的返回结果状态--{}--{}", abstractBaseResponse.getErrCode(), abstractBaseResponse.getErrMsg());
            }

            throw new WeChatException(ErrorCode.errorCode(abstractBaseResponse.getErrCode()));
        }
        return result;
    }
}
