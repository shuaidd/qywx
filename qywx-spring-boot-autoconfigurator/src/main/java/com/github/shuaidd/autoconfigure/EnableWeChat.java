package com.github.shuaidd.autoconfigure;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shuaidongdong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({WeChatAutoConfiguration.class})
@EnableCaching
@EnableAspectJAutoProxy
@EnableFeignClients(basePackages = "com.github.shuaidd.client")
public @interface EnableWeChat {
}
