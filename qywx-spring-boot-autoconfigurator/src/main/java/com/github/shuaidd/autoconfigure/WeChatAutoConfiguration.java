package com.github.shuaidd.autoconfigure;

import com.github.shuaidd.QywxClient;
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
import com.github.shuaidd.client.config.WeChatConfigurationProperties;
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
import com.github.shuaidd.support.WeChatContextHolder;
import com.github.shuaidd.support.WeChatManager;
import com.github.shuaidd.token.TokenManager;
import com.github.shuaidd.token.TokenStore;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

/**
 * 描述 企业微信 Spring 适配层配置：绑定 {@code qywx.*}、把 core 装配好的 27 个 client / 25 个 service 显式登记成 bean，
 * 并把回调配置登记到 {@link CallBackManager}
 * <p>
 * 由 5.x 的同名类迁移而来。5.x 靠 {@code @ComponentScan(basePackages = {"com.github.shuaidd.service",
 * "com.github.shuaidd.support", "com.github.shuaidd.aspect"})} 扫出 service / support，再靠 {@code @EnableWeChat} 上的
 * {@code @EnableFeignClients(basePackages = "com.github.shuaidd.client")} 注册 Feign client。6.0 起 core 不带任何 Spring
 * 构造型注解，扫描不会命中任何类；client 也是原生 OpenFeign 代理，不经 Spring Cloud OpenFeign。所以这里改为显式登记
 * 57 个 {@code @Bean}：全部 {@code @ConditionalOnMissingBean(返回类型)}（用户自带同类型 bean 即让位），bean 方法互不调用、
 * 一律参数注入（{@code @AutoConfiguration} 的元注解是 {@code @Configuration(proxyBeanMethods = false)}）。
 * <p>
 * 作为自动配置时声明 {@code afterName = {CacheAutoConfigurationNames.BOOT_4, CacheAutoConfigurationNames.LEGACY}}：
 * 用户项目里 Redis 等缓存 starter 提供的 {@link CacheManager} 先登记，适配层自带的本地 {@link ConcurrentMapCacheManager}
 * 才会让位。按名字而不是 class 声明、两代包名都列上，原因见 {@link CacheAutoConfigurationNames}。经 {@code @EnableWeChat}
 * 的 {@code @Import} 进来时走普通 import 顺序，{@code afterName} 不生效。
 * <p>
 * access_token 的三段接线：容器里有 {@link TokenStore} bean 直接用；没有则取 {@link CacheManager} 包成
 * {@link SpringCacheTokenStore}；两者都没有交给 core 自带的进程内 {@code InMemoryTokenStore}。
 * <p>
 * bean 名与 5.x 的差异：client 由 Feign 的全限定名改为简单类名首字母小写（{@code weChatClient}、{@code addressBookClient}…）；
 * {@code OAService} 叫 {@code oaService}、{@code WeChatCallCenterService} 叫 {@code callCenterService}
 * （5.x 扫描出来的是 {@code OAService} / {@code weChatCallCenterService}）。按类型注入不受影响；按名字
 * {@code @Qualifier} / {@code @Resource} 取这几个 bean 的项目要跟着改名。新增 {@link QywxClient} / {@link TokenManager}
 * 两个 bean，5.x 没有的 client 自己 {@code qywxClient.client(XxxClient.class)} 取。
 * <p>
 * 文件桥接见 {@link SpringFileBridge}：Spring 的 {@code MultipartFile} / {@code ResponseEntity} 与 core 的
 * {@link com.github.shuaidd.file.QywxFile} / {@link com.github.shuaidd.file.FileDownload} 互转。
 *
 * @author ddshuai
 * date 2019-04-03 15:46
 * @author shuaidongdong
 */
@AutoConfiguration(afterName = {CacheAutoConfigurationNames.BOOT_4, CacheAutoConfigurationNames.LEGACY})
@EnableConfigurationProperties(WeChatConfigurationProperties.class)
public class WeChatAutoConfiguration implements InitializingBean {

    private final WeChatConfigurationProperties properties;

    public WeChatAutoConfiguration(WeChatConfigurationProperties properties) {
        this.properties = properties;
    }

    /**
     * 本地缓存兜底：只建 {@link SpringCacheTokenStore#CACHE_NAME} 一个缓存，用户项目里有 Redis 等缓存 starter 提供的
     * {@link CacheManager} 时让位
     */
    @ConditionalOnMissingBean(CacheManager.class)
    @Bean
    public CacheManager concurrentMapCacheManager() {
        ConcurrentMapCacheManager concurrentMapCacheManager = new ConcurrentMapCacheManager();
        concurrentMapCacheManager.setAllowNullValues(true);
        concurrentMapCacheManager.setCacheNames(Collections.singletonList(SpringCacheTokenStore.CACHE_NAME));
        return concurrentMapCacheManager;
    }

    /**
     * 静态入口 {@link WeChatContextHolder} 的 bean 形态：{@link QywxClient} 构造时已经静态登记过 WeChatManager，这里只是让用户能注入
     */
    @ConditionalOnMissingBean(WeChatContextHolder.class)
    @Bean
    public WeChatContextHolder weChatContextHolder() {
        return new WeChatContextHolder();
    }

    /**
     * 装配 {@link QywxClient}（27 个 client + 25 个 service 一次装好）。access_token 三段接线：容器里有 {@link TokenStore} bean
     * 直接用；没有则取 {@link CacheManager} 包成 {@link SpringCacheTokenStore}；两者都没有交给 core 的进程内 {@code InMemoryTokenStore}。
     * 容器里有多个 {@link CacheManager} 又没标 {@code @Primary} 时 {@code ObjectProvider#getIfAvailable()} 抛
     * {@code NoUniqueBeanDefinitionException}，刻意不吞：配了两个缓存却没说用哪个，在启动期大声失败。
     * {@code properties} 已经通过编程方式设过 {@code tokenStore} 时不再覆盖；本方法会把选中的 store 写回 {@code properties} 单例
     */
    @ConditionalOnMissingBean(QywxClient.class)
    @Bean
    public QywxClient qywxClient(WeChatConfigurationProperties properties, ObjectProvider<TokenStore> tokenStores,
                                 ObjectProvider<CacheManager> cacheManagers) {
        if (properties.getTokenStore() == null) {
            TokenStore store = tokenStores.getIfAvailable();
            if (store == null) {
                CacheManager cacheManager = cacheManagers.getIfAvailable();
                if (cacheManager != null) {
                    store = new SpringCacheTokenStore(cacheManager);
                }
            }
            if (store != null) {
                properties.setTokenStore(store);
            }
        }
        return QywxClient.create(properties);
    }

    /**
     * access_token 的取用 / 刷新入口（{@link QywxClient#tokenManager()}）；想让 token 走自己的逻辑时覆盖这个 bean
     */
    @ConditionalOnMissingBean(TokenManager.class)
    @Bean
    public TokenManager tokenManager(QywxClient qywxClient) {
        return qywxClient.tokenManager();
    }

    /**
     * 25 个 service 的门面（{@link QywxClient#weChatManager()}）；{@link WeChatContextHolder#getWeChatManager()} 静态拿到的也是它
     */
    @ConditionalOnMissingBean(WeChatManager.class)
    @Bean
    public WeChatManager weChatManager(QywxClient qywxClient) {
        return qywxClient.weChatManager();
    }

    /**
     * {@link AddressBookService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(AddressBookService.class)
    @Bean
    public AddressBookService addressBookService(QywxClient qywxClient) {
        return qywxClient.weChatManager().addressBookService();
    }

    /**
     * {@link TokenService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(TokenService.class)
    @Bean
    public TokenService tokenService(QywxClient qywxClient) {
        return qywxClient.weChatManager().tokenService();
    }

    /**
     * {@link AuthenticationService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(AuthenticationService.class)
    @Bean
    public AuthenticationService authenticationService(QywxClient qywxClient) {
        return qywxClient.weChatManager().authenticationService();
    }

    /**
     * {@link MediaService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(MediaService.class)
    @Bean
    public MediaService mediaService(QywxClient qywxClient) {
        return qywxClient.weChatManager().mediaService();
    }

    /**
     * {@link ApplicationService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(ApplicationService.class)
    @Bean
    public ApplicationService applicationService(QywxClient qywxClient) {
        return qywxClient.weChatManager().applicationService();
    }

    /**
     * {@link MessageService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(MessageService.class)
    @Bean
    public MessageService messageService(QywxClient qywxClient) {
        return qywxClient.weChatManager().messageService();
    }

    /**
     * {@link OAService}：5.x 扫描出来的 bean 名是 {@code OAService}，这里改为驼峰的 {@code oaService}（按类型注入不受影响）
     */
    @ConditionalOnMissingBean(OAService.class)
    @Bean
    public OAService oaService(QywxClient qywxClient) {
        return qywxClient.weChatManager().oaService();
    }

    /**
     * {@link CustomContactService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(CustomContactService.class)
    @Bean
    public CustomContactService customContactService(QywxClient qywxClient) {
        return qywxClient.weChatManager().customContactService();
    }

    /**
     * {@link EfficiencyToolService}：{@code WeChatManager} 上的访问器沿用历史命名 {@code efficiencyTool()}，bean 名与 5.x 扫描结果相同
     */
    @ConditionalOnMissingBean(EfficiencyToolService.class)
    @Bean
    public EfficiencyToolService efficiencyToolService(QywxClient qywxClient) {
        return qywxClient.weChatManager().efficiencyTool();
    }

    /**
     * {@link WeChatCallCenterService}：5.x 扫描出来的 bean 名是 {@code weChatCallCenterService}，这里改为 {@code callCenterService}（按类型注入不受影响）
     */
    @ConditionalOnMissingBean(WeChatCallCenterService.class)
    @Bean
    public WeChatCallCenterService callCenterService(QywxClient qywxClient) {
        return qywxClient.weChatManager().callCenterService();
    }

    /**
     * {@link ServiceProviderService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(ServiceProviderService.class)
    @Bean
    public ServiceProviderService serviceProviderService(QywxClient qywxClient) {
        return qywxClient.weChatManager().serviceProviderService();
    }

    /**
     * {@link EnterpriseEmailService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(EnterpriseEmailService.class)
    @Bean
    public EnterpriseEmailService enterpriseEmailService(QywxClient qywxClient) {
        return qywxClient.weChatManager().enterpriseEmailService();
    }

    /**
     * {@link ElectronicInvoiceService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(ElectronicInvoiceService.class)
    @Bean
    public ElectronicInvoiceService electronicInvoiceService(QywxClient qywxClient) {
        return qywxClient.weChatManager().electronicInvoiceService();
    }

    /**
     * {@link EnterpriseInterConnectionService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(EnterpriseInterConnectionService.class)
    @Bean
    public EnterpriseInterConnectionService enterpriseInterConnectionService(QywxClient qywxClient) {
        return qywxClient.weChatManager().enterpriseInterConnectionService();
    }

    /**
     * {@link CommonService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(CommonService.class)
    @Bean
    public CommonService commonService(QywxClient qywxClient) {
        return qywxClient.weChatManager().commonService();
    }

    /**
     * {@link MeetingService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(MeetingService.class)
    @Bean
    public MeetingService meetingService(QywxClient qywxClient) {
        return qywxClient.weChatManager().meetingService();
    }

    /**
     * {@link FrontEndToolService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(FrontEndToolService.class)
    @Bean
    public FrontEndToolService frontEndToolService(QywxClient qywxClient) {
        return qywxClient.weChatManager().frontEndToolService();
    }

    /**
     * {@link DocumentService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(DocumentService.class)
    @Bean
    public DocumentService documentService(QywxClient qywxClient) {
        return qywxClient.weChatManager().documentService();
    }

    /**
     * {@link SmartSheetService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(SmartSheetService.class)
    @Bean
    public SmartSheetService smartSheetService(QywxClient qywxClient) {
        return qywxClient.weChatManager().smartSheetService();
    }

    /**
     * {@link PaymentService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(PaymentService.class)
    @Bean
    public PaymentService paymentService(QywxClient qywxClient) {
        return qywxClient.weChatManager().paymentService();
    }

    /**
     * {@link SchoolService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(SchoolService.class)
    @Bean
    public SchoolService schoolService(QywxClient qywxClient) {
        return qywxClient.weChatManager().schoolService();
    }

    /**
     * {@link AdvancedFeatureService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(AdvancedFeatureService.class)
    @Bean
    public AdvancedFeatureService advancedFeatureService(QywxClient qywxClient) {
        return qywxClient.weChatManager().advancedFeatureService();
    }

    /**
     * {@link GovernmentCommunicationService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(GovernmentCommunicationService.class)
    @Bean
    public GovernmentCommunicationService governmentCommunicationService(QywxClient qywxClient) {
        return qywxClient.weChatManager().governmentCommunicationService();
    }

    /**
     * {@link HrService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(HrService.class)
    @Bean
    public HrService hrService(QywxClient qywxClient) {
        return qywxClient.weChatManager().hrService();
    }

    /**
     * {@link SecurityService}：bean 名与 5.x 扫描出来的相同
     */
    @ConditionalOnMissingBean(SecurityService.class)
    @Bean
    public SecurityService securityService(QywxClient qywxClient) {
        return qywxClient.weChatManager().securityService();
    }

    /** {@link WeChatClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WeChatClient.class)
    @Bean
    public WeChatClient weChatClient(QywxClient qywxClient) {
        return qywxClient.client(WeChatClient.class);
    }

    /** {@link WeChatMediaClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WeChatMediaClient.class)
    @Bean
    public WeChatMediaClient weChatMediaClient(QywxClient qywxClient) {
        return qywxClient.client(WeChatMediaClient.class);
    }

    /** {@link FrontEndToolsClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(FrontEndToolsClient.class)
    @Bean
    public FrontEndToolsClient frontEndToolsClient(QywxClient qywxClient) {
        return qywxClient.client(FrontEndToolsClient.class);
    }

    /** {@link MeetingClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(MeetingClient.class)
    @Bean
    public MeetingClient meetingClient(QywxClient qywxClient) {
        return qywxClient.client(MeetingClient.class);
    }

    /** {@link ServiceProviderClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(ServiceProviderClient.class)
    @Bean
    public ServiceProviderClient serviceProviderClient(QywxClient qywxClient) {
        return qywxClient.client(ServiceProviderClient.class);
    }

    /** {@link PaymentUploadClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(PaymentUploadClient.class)
    @Bean
    public PaymentUploadClient paymentUploadClient(QywxClient qywxClient) {
        return qywxClient.client(PaymentUploadClient.class);
    }

    /** {@link AdvancedFeatureClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(AdvancedFeatureClient.class)
    @Bean
    public AdvancedFeatureClient advancedFeatureClient(QywxClient qywxClient) {
        return qywxClient.client(AdvancedFeatureClient.class);
    }

    /** {@link AuthClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(AuthClient.class)
    @Bean
    public AuthClient authClient(QywxClient qywxClient) {
        return qywxClient.client(AuthClient.class);
    }

    /** {@link ElectronicInvoiceClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(ElectronicInvoiceClient.class)
    @Bean
    public ElectronicInvoiceClient electronicInvoiceClient(QywxClient qywxClient) {
        return qywxClient.client(ElectronicInvoiceClient.class);
    }

    /** {@link EnterpriseInterConnectionClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(EnterpriseInterConnectionClient.class)
    @Bean
    public EnterpriseInterConnectionClient enterpriseInterConnectionClient(QywxClient qywxClient) {
        return qywxClient.client(EnterpriseInterConnectionClient.class);
    }

    /** {@link HrClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(HrClient.class)
    @Bean
    public HrClient hrClient(QywxClient qywxClient) {
        return qywxClient.client(HrClient.class);
    }

    /** {@link GovernmentCommunicationClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(GovernmentCommunicationClient.class)
    @Bean
    public GovernmentCommunicationClient governmentCommunicationClient(QywxClient qywxClient) {
        return qywxClient.client(GovernmentCommunicationClient.class);
    }

    /** {@link LinkedCorpClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(LinkedCorpClient.class)
    @Bean
    public LinkedCorpClient linkedCorpClient(QywxClient qywxClient) {
        return qywxClient.client(LinkedCorpClient.class);
    }

    /** {@link MessageClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(MessageClient.class)
    @Bean
    public MessageClient messageClient(QywxClient qywxClient) {
        return qywxClient.client(MessageClient.class);
    }

    /** {@link WorkApplicationClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WorkApplicationClient.class)
    @Bean
    public WorkApplicationClient workApplicationClient(QywxClient qywxClient) {
        return qywxClient.client(WorkApplicationClient.class);
    }

    /** {@link PaymentClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(PaymentClient.class)
    @Bean
    public PaymentClient paymentClient(QywxClient qywxClient) {
        return qywxClient.client(PaymentClient.class);
    }

    /** {@link SecurityClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(SecurityClient.class)
    @Bean
    public SecurityClient securityClient(QywxClient qywxClient) {
        return qywxClient.client(SecurityClient.class);
    }

    /** {@link WechatSmartSheetClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WechatSmartSheetClient.class)
    @Bean
    public WechatSmartSheetClient wechatSmartSheetClient(QywxClient qywxClient) {
        return qywxClient.client(WechatSmartSheetClient.class);
    }

    /** {@link EnterpriseEmailClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(EnterpriseEmailClient.class)
    @Bean
    public EnterpriseEmailClient enterpriseEmailClient(QywxClient qywxClient) {
        return qywxClient.client(EnterpriseEmailClient.class);
    }

    /** {@link WeChatCallCenterClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WeChatCallCenterClient.class)
    @Bean
    public WeChatCallCenterClient weChatCallCenterClient(QywxClient qywxClient) {
        return qywxClient.client(WeChatCallCenterClient.class);
    }

    /** {@link WorkOaClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WorkOaClient.class)
    @Bean
    public WorkOaClient workOaClient(QywxClient qywxClient) {
        return qywxClient.client(WorkOaClient.class);
    }

    /** {@link EfficiencyToolClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(EfficiencyToolClient.class)
    @Bean
    public EfficiencyToolClient efficiencyToolClient(QywxClient qywxClient) {
        return qywxClient.client(EfficiencyToolClient.class);
    }

    /** {@link WechatDocClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(WechatDocClient.class)
    @Bean
    public WechatDocClient wechatDocClient(QywxClient qywxClient) {
        return qywxClient.client(WechatDocClient.class);
    }

    /** {@link SchoolClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(SchoolClient.class)
    @Bean
    public SchoolClient schoolClient(QywxClient qywxClient) {
        return qywxClient.client(SchoolClient.class);
    }

    /** {@link AddressBookClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(AddressBookClient.class)
    @Bean
    public AddressBookClient addressBookClient(QywxClient qywxClient) {
        return qywxClient.client(AddressBookClient.class);
    }

    /** {@link ExternalContactClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(ExternalContactClient.class)
    @Bean
    public ExternalContactClient externalContactClient(QywxClient qywxClient) {
        return qywxClient.client(ExternalContactClient.class);
    }

    /** {@link MeetingAdvancedClient}：原生 OpenFeign 代理，bean 名由 5.x 的接口全限定名改为简单类名首字母小写 */
    @ConditionalOnMissingBean(MeetingAdvancedClient.class)
    @Bean
    public MeetingAdvancedClient meetingAdvancedClient(QywxClient qywxClient) {
        return qywxClient.client(MeetingAdvancedClient.class);
    }

    @Override
    public void afterPropertiesSet() {
        CallBackManager.properties(properties.getCallbackList());
    }
}
