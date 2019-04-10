package com.github.shuaidd.autoconfigure;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(WeChatAutoConfiguration.class)
@EnableFeignClients(basePackages = "com.travel.qywx.client")
public @interface EnableWeChat {
}
