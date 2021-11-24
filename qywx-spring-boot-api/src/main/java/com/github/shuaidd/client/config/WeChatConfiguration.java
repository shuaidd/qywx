package com.github.shuaidd.client.config;

import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.client.interceptor.WeChatInterceptor;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 描述 企业微信feign客户端配置
 *
 * @author ddshuai
 * date 2019-04-04 16:18
 **/
public class WeChatConfiguration {

    @Bean
    public Decoder decoder() {
        return new CustomJacksonDecoder();
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Feign.Builder builder(ApplicationContext applicationContext) {
        return new Feign.Builder().requestInterceptor(new WeChatInterceptor(applicationContext));
    }
}
