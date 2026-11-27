package com.github.shuaidd;

import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.AdvancedFeatureClient;
import com.github.shuaidd.client.AuthClient;
import com.github.shuaidd.client.EfficiencyToolClient;
import com.github.shuaidd.client.ElectronicInvoiceClient;
import com.github.shuaidd.client.EnterpriseEmailClient;
import com.github.shuaidd.client.EnterpriseInterConnectionClient;
import com.github.shuaidd.client.ExternalContactClient;
import com.github.shuaidd.client.FrontEndToolsClient;
import com.github.shuaidd.client.GovernmentCommunicationClient;
import com.github.shuaidd.client.HrClient;
import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.client.MessageClient;
import com.github.shuaidd.client.PaymentClient;
import com.github.shuaidd.client.PaymentUploadClient;
import com.github.shuaidd.client.QywxContract;
import com.github.shuaidd.client.SchoolClient;
import com.github.shuaidd.client.SecurityClient;
import com.github.shuaidd.client.ServiceProviderClient;
import com.github.shuaidd.client.WeChatCallCenterClient;
import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.client.WechatSmartSheetClient;
import com.github.shuaidd.client.WorkApplicationClient;
import com.github.shuaidd.client.WorkOaClient;
import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.client.decoder.WeChatMediaDecoder;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.client.encoder.MultipartEncoder;
import com.github.shuaidd.client.interceptor.WeChatInterceptor;
import com.github.shuaidd.client.support.TokenRetryInvocationHandlerFactory;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.service.AddressBookService;
import com.github.shuaidd.service.AdvancedFeatureService;
import com.github.shuaidd.service.ApplicationService;
import com.github.shuaidd.service.AuthenticationService;
import com.github.shuaidd.service.CommonService;
import com.github.shuaidd.service.CustomContactService;
import com.github.shuaidd.service.DocumentService;
import com.github.shuaidd.service.EfficiencyToolService;
import com.github.shuaidd.service.ElectronicInvoiceService;
import com.github.shuaidd.service.EnterpriseEmailService;
import com.github.shuaidd.service.EnterpriseInterConnectionService;
import com.github.shuaidd.service.FrontEndToolService;
import com.github.shuaidd.service.GovernmentCommunicationService;
import com.github.shuaidd.service.HrService;
import com.github.shuaidd.service.MediaService;
import com.github.shuaidd.service.MeetingService;
import com.github.shuaidd.service.MessageService;
import com.github.shuaidd.service.OAService;
import com.github.shuaidd.service.PaymentService;
import com.github.shuaidd.service.SchoolService;
import com.github.shuaidd.service.SecurityService;
import com.github.shuaidd.service.ServiceProviderService;
import com.github.shuaidd.service.SmartSheetService;
import com.github.shuaidd.service.TokenService;
import com.github.shuaidd.service.WeChatCallCenterService;
import com.github.shuaidd.support.CallBackManager;
import com.github.shuaidd.support.RawDownloader;
import com.github.shuaidd.support.WeChatContextHolder;
import com.github.shuaidd.support.WeChatManager;
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
 * 业务调用经 {@link #weChatManager()} 取各 service，底层 client 经 {@link #weChatClient()} 等访问器获取。
 * <p>
 * 装配方式（与契约测试 {@code ContractFeign} 一致）：27 个 client 每个恰建一个原生 OpenFeign 代理——
 * {@code QywxContract}（下载落盘目标不进表单体）、按配置的超时、{@code NEVER_RETRY}（令牌失效重试只由
 * {@code TokenRetryInvocationHandlerFactory} 做，避免叠加）、multipart 包 JSON encoder、自定义 decoder、
 * {@code WeChatInterceptor} 注入 access_token。HTTP 默认走 feign-core 自带的 {@code Client.Default}
 * （JDK HttpURLConnection）；包内重载可注入自定义 {@link Client}。
 * <p>
 * 唯一的例外是 {@link ServiceProviderClient}：它的接口不传应用名（服务商凭证由请求体给出），
 * 所以不加拦截器、不取号，请求体按 5.x 的 {@code SpringEncoder} 复刻为紧凑 JSON + {@code application/json}，
 * 其余 client 仍是 5.x {@code feign-jackson} 的缩进 JSON。
 * <p>
 * 25 个 service 全部由 {@link QywxContext} 构造器注入（{@code WechatDocClient} 由 Document 与 SmartSheet 两个 service 共用），
 * 再汇总进 {@link WeChatManager}。装配完成后登记两处静态入口，与 5.x 的 {@code WeChatAutoConfiguration} 对齐：
 * {@link WeChatContextHolder#setWeChatManager}（静态取 manager）与 {@link CallBackManager#properties}（回调密钥）。
 * <p>
 * access_token 缓存：配置了 {@code tokenStore} 就用它，否则用进程内缓存。取令牌时 {@link TokenManager}
 * 回调 {@link WeChatClient#getAccessToken}，而该 client 的拦截器与重试 handler 又依赖 manager，
 * 二者用 {@code AtomicReference} 解环：manager 先建（取号器延迟读取引用），代理建好后再登记。
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

    private final WeChatManager weChatManager;

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

        //基类依赖之外的 client：每个恰建一个代理，WechatDocClient 由 Document 与 SmartSheet 两个 service 共用
        AuthenticationService authenticationService = new AuthenticationService(context, client(AuthClient.class, config, tokenManager, http));
        ApplicationService applicationService = new ApplicationService(context, client(WorkApplicationClient.class, config, tokenManager, http));
        MediaService mediaService = new MediaService(context, client(WeChatMediaClient.class, config, tokenManager, http));
        MessageService messageService = new MessageService(context, client(MessageClient.class, config, tokenManager, http));
        OAService oaService = new OAService(context);
        CustomContactService customContactService = new CustomContactService(context, client(ExternalContactClient.class, config, tokenManager, http));
        EfficiencyToolService efficiencyToolService = new EfficiencyToolService(context, client(EfficiencyToolClient.class, config, tokenManager, http));
        WeChatCallCenterService callCenterService = new WeChatCallCenterService(context, client(WeChatCallCenterClient.class, config, tokenManager, http));
        CommonService commonService = new CommonService(context);
        ServiceProviderService serviceProviderService = new ServiceProviderService(context, client(ServiceProviderClient.class, config, tokenManager, http));
        EnterpriseEmailService enterpriseEmailService = new EnterpriseEmailService(context, client(EnterpriseEmailClient.class, config, tokenManager, http));
        ElectronicInvoiceService electronicInvoiceService = new ElectronicInvoiceService(context, client(ElectronicInvoiceClient.class, config, tokenManager, http));
        EnterpriseInterConnectionService enterpriseInterConnectionService = new EnterpriseInterConnectionService(context, client(EnterpriseInterConnectionClient.class, config, tokenManager, http));
        MeetingService meetingService = new MeetingService(context,
                client(MeetingClient.class, config, tokenManager, http),
                client(MeetingAdvancedClient.class, config, tokenManager, http));
        FrontEndToolService frontEndToolService = new FrontEndToolService(context, client(FrontEndToolsClient.class, config, tokenManager, http));
        WechatDocClient wechatDocClient = client(WechatDocClient.class, config, tokenManager, http);
        DocumentService documentService = new DocumentService(context, wechatDocClient);
        SmartSheetService smartSheetService = new SmartSheetService(context,
                client(WechatSmartSheetClient.class, config, tokenManager, http), wechatDocClient);
        PaymentService paymentService = new PaymentService(context,
                client(PaymentClient.class, config, tokenManager, http),
                client(PaymentUploadClient.class, config, tokenManager, http));
        SchoolService schoolService = new SchoolService(context, client(SchoolClient.class, config, tokenManager, http));
        AdvancedFeatureService advancedFeatureService = new AdvancedFeatureService(context, client(AdvancedFeatureClient.class, config, tokenManager, http));
        GovernmentCommunicationService governmentCommunicationService = new GovernmentCommunicationService(context, client(GovernmentCommunicationClient.class, config, tokenManager, http));
        HrService hrService = new HrService(context, client(HrClient.class, config, tokenManager, http));
        SecurityService securityService = new SecurityService(context, client(SecurityClient.class, config, tokenManager, http));
        AddressBookService addressBookService = new AddressBookService(context, mediaService,
                new RawDownloader(http, options(config)));

        this.weChatManager = new WeChatManager(addressBookService, tokenService, config,
                authenticationService, applicationService, mediaService, messageService, oaService,
                customContactService, efficiencyToolService, callCenterService, commonService,
                serviceProviderService, enterpriseEmailService, electronicInvoiceService,
                enterpriseInterConnectionService, meetingService, frontEndToolService, documentService,
                smartSheetService, paymentService, schoolService, advancedFeatureService,
                governmentCommunicationService, hrService, securityService);
        WeChatContextHolder.setWeChatManager(weChatManager);
        CallBackManager.properties(config.getCallbackList());
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
        Feign.Builder builder = Feign.builder()
                .contract(new QywxContract())
                .client(http)
                .options(options(config))
                .retryer(Retryer.NEVER_RETRY)
                .invocationHandlerFactory(new TokenRetryInvocationHandlerFactory(tokenManager, config));
        if (type == ServiceProviderClient.class) {
            //服务商凭证接口不传应用名：不挂令牌拦截器、不取号；请求体按 5.x SpringEncoder 复刻为紧凑 JSON
            return builder
                    .encoder(new MultipartEncoder(JacksonJsonEncoder.compact()))
                    .decoder(new CustomJacksonDecoder())
                    .target(type, config.baseUrl());
        }
        return builder
                .encoder(new MultipartEncoder(JacksonJsonEncoder.indented()))
                .decoder(new WeChatMediaDecoder(new CustomJacksonDecoder()))
                .requestInterceptor(new WeChatInterceptor(tokenManager, config))
                .target(type, config.baseUrl());
    }

    /**
     * 按配置的超时生成 Feign 选项；{@code RawDownloader} 与各 client 代理共用同一份
     */
    private static Request.Options options(QywxConfig config) {
        return new Request.Options(config.getConnectTimeoutMillis(), TimeUnit.MILLISECONDS,
                config.getReadTimeoutMillis(), TimeUnit.MILLISECONDS, true);
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

    /**
     * 25 个 service 的统一入口（与 5.x 注入的 {@code WeChatManager} bean 等价）
     *
     * @return WeChatManager
     */
    public WeChatManager weChatManager() {
        return weChatManager;
    }
}