package com.github.shuaidd;

import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.client.QywxContract;
import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WorkOaClient;
import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.client.decoder.WeChatMediaDecoder;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.client.encoder.MultipartEncoder;
import com.github.shuaidd.client.interceptor.WeChatInterceptor;
import com.github.shuaidd.client.support.TokenRetryInvocationHandlerFactory;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.service.TokenService;
import com.github.shuaidd.token.InMemoryTokenStore;
import com.github.shuaidd.token.TokenManager;
import com.github.shuaidd.token.TokenStore;
import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Retryer;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 描述 企业微信客户端入口：纯 Java 环境下用它替代 5.x 的 Spring 自动装配，按配置装配 client 与 service
 * <p>
 * 用法：{@code QywxClient client = QywxClient.create(QywxConfig.builder()....build());}，
 * 业务调用经 {@link #weChatClient()} 发起。
 * <p>
 * 装配方式（与契约测试 {@code ContractFeign} 一致）：每个 client 都是原生 OpenFeign 代理——
 * {@code QywxContract}（下载落盘目标不进表单体）、按配置的超时、{@code NEVER_RETRY}（令牌失效重试只由
 * {@code TokenRetryInvocationHandlerFactory} 做，避免叠加）、缩进 JSON + multipart encoder、自定义 decoder、
 * {@code WeChatInterceptor} 注入 access_token。HTTP 默认走 feign-core 自带的 {@code Client.Default}
 * （JDK HttpURLConnection）；包内重载可注入自定义 {@link Client}。
 * <p>
 * access_token 缓存：配置了 {@code tokenStore} 就用它，否则用进程内缓存。取令牌时 {@link TokenManager}
 * 回调 {@link WeChatClient#getAccessToken}，而该 client 的拦截器与重试 handler 又依赖 manager，
 * 二者用 {@code AtomicReference} 解环：manager 先建（取号器延迟读取引用），代理建好后再登记。
 * <p>
 * 本阶段装配 {@link WeChatClient}、{@link TokenService} 与基类 {@code AbstractBaseService} 需要的 3 个 client
 * （{@link WorkOaClient}、{@link AddressBookClient}、{@link LinkedCorpClient}，经 {@link QywxContext} 交给 service）；
 * 其余 client 与 service 的访问器随后续步骤补齐，本类的入口保持不变。
 *
 * @author ddshuai
 * date 2026-11-26 06:40
 **/
public final class QywxClient {

    private final QywxConfig config;

    private final Client http;

    private final TokenManager tokenManager;

    private final WeChatClient weChatClient;

    private final QywxContext context;

    private final TokenService tokenService;

    private QywxClient(QywxConfig config, Client http) {
        this.config = Objects.requireNonNull(config, "config");
        Objects.requireNonNull(http, "http");
        this.http = http;
        TokenStore store = config.getTokenStore() == null ? new InMemoryTokenStore() : config.getTokenStore();
        //取号器要调 WeChatClient 代理，而代理的拦截器与重试 handler 又要 TokenManager：先建 manager，代理建好后再登记
        AtomicReference<WeChatClient> reference = new AtomicReference<WeChatClient>();
        this.tokenManager = new TokenManager(config, store,
                (corpId, secret) -> reference.get().getAccessToken(corpId, secret));
        this.weChatClient = client(WeChatClient.class, config, tokenManager, http);
        reference.set(weChatClient);
        this.context = new QywxContext(config, weChatClient,
                client(WorkOaClient.class, config, tokenManager, http),
                client(AddressBookClient.class, config, tokenManager, http),
                client(LinkedCorpClient.class, config, tokenManager, http));
        this.tokenService = new TokenService(context, tokenManager);
    }

    /**
     * 按配置创建客户端，HTTP 走 feign-core 自带的 {@code Client.Default}
     *
     * @param config 配置
     * @return QywxClient
     */
    public static QywxClient create(QywxConfig config) {
        return create(config, new Client.Default(null, null));
    }

    /**
     * 按配置创建客户端并注入 HTTP 实现（契约测试用假 client 替代真实网络）
     *
     * @param config 配置
     * @param http   HTTP 实现
     * @return QywxClient
     */
    static QywxClient create(QywxConfig config, Client http) {
        return new QywxClient(config, http);
    }

    /**
     * 装配一个原生 OpenFeign 代理：必装 {@link QywxContract}，不叠加 Feign 自带的重试
     */
    private static <T> T client(Class<T> type, QywxConfig config, TokenManager tokenManager, Client http) {
        return Feign.builder()
                .contract(new QywxContract())
                .client(http)
                .options(new Request.Options(config.getConnectTimeoutMillis(), TimeUnit.MILLISECONDS, config.getReadTimeoutMillis(), TimeUnit.MILLISECONDS, true))
                .retryer(Retryer.NEVER_RETRY)
                .encoder(new MultipartEncoder(JacksonJsonEncoder.indented()))
                .decoder(new WeChatMediaDecoder(new CustomJacksonDecoder()))
                .requestInterceptor(new WeChatInterceptor(tokenManager, config))
                .invocationHandlerFactory(new TokenRetryInvocationHandlerFactory(tokenManager, config))
                .target(type, config.baseUrl());
    }

    /**
     * 按与本客户端相同的装配方式再建一个 client 代理（service 层组装与测试用）
     *
     * @param type client 接口
     * @return 代理
     */
    <T> T proxy(Class<T> type) {
        return client(type, config, tokenManager, http);
    }

    public QywxConfig config() {
        return config;
    }

    public TokenManager tokenManager() {
        return tokenManager;
    }

    public WeChatClient weChatClient() {
        return weChatClient;
    }

    public QywxContext context() {
        return context;
    }

    public TokenService tokenService() {
        return tokenService;
    }
}
