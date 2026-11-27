package com.github.shuaidd.autoconfigure;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述 开启企业微信 Spring 适配：导入 {@link WeChatAutoConfiguration}
 * <p>
 * 由 5.x 的同名注解迁移而来，去掉了三个随之失效的开关：core 的 client 是原生 OpenFeign 的 {@code @RequestLine} 接口，
 * {@code @EnableFeignClients} 扫不到；令牌失效重试已由 core 的调用处理器完成，不再需要 {@code @EnableAspectJAutoProxy}；
 * core 内部没有 {@code @Cacheable} 等注解式缓存，不需要 {@code @EnableCaching} 开启的注解代理。
 *
 * @author shuaidongdong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({WeChatAutoConfiguration.class})
public @interface EnableWeChat {
}
