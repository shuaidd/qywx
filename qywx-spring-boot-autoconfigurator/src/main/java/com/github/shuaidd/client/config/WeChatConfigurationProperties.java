package com.github.shuaidd.client.config;

import com.github.shuaidd.config.QywxConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 描述 {@code qywx.*} 配置项在 Spring 环境下的载体：继承纯 Java 的 {@link QywxConfig}，
 * 由 Spring Boot 绑定后直接交给 core 装配客户端
 * <p>
 * 由 5.x 的同名类迁入适配层模块，类名与所在包保持不变；6.0 起不再自带字段，改为继承 {@link QywxConfig}，
 * 键名与 5.x 完全一致（{@code corp-id}、{@code url}、{@code public-path}、{@code debug-mode}、
 * {@code application-list[]}、{@code callback-list[]}），宽松绑定规则不变（{@code agentId} 与 {@code agent-id} 等价）。
 * 与 5.x 的差异只有四点：
 * <ol>
 * <li>新增两个可选项 {@code qywx.connect-timeout-millis} / {@code qywx.read-timeout-millis}
 * （默认 10000 / 60000，与纯 Java 入口一致，也与 5.x Feign 的默认超时相同）；</li>
 * <li>{@code url} / {@code public-path} 未配置时不再是 null，而是 core 的默认值
 * （{@value QywxConfig#DEFAULT_URL} / {@value QywxConfig#DEFAULT_PUBLIC_PATH}）；5.x 的 client 占位符
 * {@code ${qywx.url:...}} / {@code ${qywx.public-path:...}} 以同值兜底，实际请求地址不变；</li>
 * <li>{@code application-list} / {@code callback-list} 未配置时是空列表而不是 null
 * （{@code CallBackManager} 对两者同等处理，行为不变）；</li>
 * <li>{@code tokenStore} 是编程式配置项，没有对应的 {@code qywx.*} 键，Spring 下保持 null（进程内缓存）。</li>
 * </ol>
 *
 * @author ddshuai
 * date 2019-04-03 15:47
 * @author shuaidongdong
 **/
@ConfigurationProperties(prefix = "qywx")
public class WeChatConfigurationProperties extends QywxConfig {
}
