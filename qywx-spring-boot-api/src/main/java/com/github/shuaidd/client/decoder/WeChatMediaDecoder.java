package com.github.shuaidd.client.decoder;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-06 17:37
 **/
public class WeChatMediaDecoder implements Decoder {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Decoder delegate = new JacksonDecoder();

    @Override
    public Object decode(Response response, Type type) throws FeignException, IOException {
        if (type instanceof sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl) {
            if (((sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl) type).getRawType() == ResponseEntity.class) {
                byte[] body = new byte[response.body().length()];
                int result = response.body().asInputStream().read(body, 0, response.body().length());
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                for (String key : response.headers().keySet()) {
                    headers.put(key, new LinkedList<>(response.headers().get(key)));
                }
                logger.info("下载文件大小为:{}",result);
                return new ResponseEntity<>(body, headers, HttpStatus.OK);
            } else {
                return delegate.decode(response, type);
            }
        } else {
            return delegate.decode(response, type);
        }
    }
}
