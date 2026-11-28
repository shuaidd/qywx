package com.github.shuaidd;

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
import com.github.shuaidd.client.MeetingAdvancedClient;
import com.github.shuaidd.client.MeetingClient;
import com.github.shuaidd.client.MessageClient;
import com.github.shuaidd.client.PaymentClient;
import com.github.shuaidd.client.PaymentUploadClient;
import com.github.shuaidd.client.SchoolClient;
import com.github.shuaidd.client.SecurityClient;
import com.github.shuaidd.client.ServiceProviderClient;
import com.github.shuaidd.client.WeChatCallCenterClient;
import com.github.shuaidd.client.WeChatMediaClient;
import com.github.shuaidd.client.WechatDocClient;
import com.github.shuaidd.client.WechatSmartSheetClient;
import com.github.shuaidd.client.WorkApplicationClient;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.service.AbstractBaseService;
import com.github.shuaidd.service.AuthenticationService;
import com.github.shuaidd.service.CommonService;
import com.github.shuaidd.service.FrontEndToolService;
import com.github.shuaidd.service.OAService;
import feign.Request;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 service 层迁入 core 后的装配守卫：包内类清单、每个 service 的构造器形态（首参 {@link QywxContext}、其余参数为各自的 client），
 * 以及经上下文里的基础 client 真实发起请求的报文
 * <p>
 * 23 个 service 由 5.x 机械迁移而来（codemod {@code p3-1-copy.py} 与 {@code p3-2-copy.py}），这里用反射把「构造器签名 + 父类」锁成结构守卫，
 * 再用 {@code RecordingClient} 记录 Feign 实际发出的请求，证明依赖改走 {@link QywxContext} 后地址、应用名与 access_token 的注入与 5.x 一致。
 *
 * @author ddshuai
 * date 2026-11-27 22:10
 **/
public class ServiceWiringTest {

    private static final String SERVICE_PACKAGE = "com.github.shuaidd.service";

    private static final String BASE = "http://contract.invalid/cgi-bin/";

    private static final String GET_TOKEN_URL = BASE + "gettoken?corpid=corp-contract&corpsecret=secret-contract";

    /**
     * 23 个机械迁移的 service 与各自构造器需要的 client 接口（顺序即构造器参数顺序）；AddressBook/Token 不在此列
     */
    private static final Map<String, Class<?>[]> SERVICES = new LinkedHashMap<>();

    static {
        SERVICES.put("AdvancedFeatureService", new Class<?>[]{AdvancedFeatureClient.class});
        SERVICES.put("ApplicationService", new Class<?>[]{WorkApplicationClient.class});
        SERVICES.put("AuthenticationService", new Class<?>[]{AuthClient.class});
        SERVICES.put("CommonService", new Class<?>[0]);
        SERVICES.put("CustomContactService", new Class<?>[]{ExternalContactClient.class});
        SERVICES.put("DocumentService", new Class<?>[]{WechatDocClient.class});
        SERVICES.put("EfficiencyToolService", new Class<?>[]{EfficiencyToolClient.class});
        SERVICES.put("ElectronicInvoiceService", new Class<?>[]{ElectronicInvoiceClient.class});
        SERVICES.put("EnterpriseEmailService", new Class<?>[]{EnterpriseEmailClient.class});
        SERVICES.put("EnterpriseInterConnectionService", new Class<?>[]{EnterpriseInterConnectionClient.class});
        SERVICES.put("FrontEndToolService", new Class<?>[]{FrontEndToolsClient.class});
        SERVICES.put("GovernmentCommunicationService", new Class<?>[]{GovernmentCommunicationClient.class});
        SERVICES.put("HrService", new Class<?>[]{HrClient.class});
        SERVICES.put("MediaService", new Class<?>[]{WeChatMediaClient.class});
        SERVICES.put("MeetingService", new Class<?>[]{MeetingClient.class, MeetingAdvancedClient.class});
        SERVICES.put("MessageService", new Class<?>[]{MessageClient.class});
        SERVICES.put("OAService", new Class<?>[0]);
        SERVICES.put("PaymentService", new Class<?>[]{PaymentClient.class, PaymentUploadClient.class});
        SERVICES.put("SchoolService", new Class<?>[]{SchoolClient.class});
        SERVICES.put("SecurityService", new Class<?>[]{SecurityClient.class});
        SERVICES.put("ServiceProviderService", new Class<?>[]{ServiceProviderClient.class});
        SERVICES.put("SmartSheetService", new Class<?>[]{WechatSmartSheetClient.class, WechatDocClient.class});
        SERVICES.put("WeChatCallCenterService", new Class<?>[]{WeChatCallCenterClient.class});
    }

    /**
     * service 包里只有基类、TokenService 与 23 个机械迁移的 service（AddressBookService 依赖 MediaService 与 RawDownloader，
     * 另在 SpecialServiceWiringTest 里验证）：多一个或少一个都在这里失败
     */
    @Test
    public void servicePackageContainsExactlyTheMigratedClasses() throws IOException, URISyntaxException {
        Set<String> expected = new TreeSet<>(SERVICES.keySet());
        expected.add("AbstractBaseService");
        expected.add("TokenService");
        expected.add("AddressBookService");

        assertEquals(expected, compiledClassNames(SERVICE_PACKAGE));
    }

    /**
     * 每个 service 恰有一个 public 构造器：首参为 QywxContext，其余参数恰为各自的 client 接口，父类为 AbstractBaseService；
     * 并用上下文与真实 Feign 代理实际实例化一遍
     */
    @Test
    public void everyServiceTakesContextFirstAndExtendsBaseClass() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());
        List<Executable> checks = new ArrayList<>();
        for (Map.Entry<String, Class<?>[]> entry : SERVICES.entrySet()) {
            checks.add(() -> verifyService(client, entry.getKey(), entry.getValue()));
        }
        assertAll(checks);
    }

    /**
     * 上下文里的 4 个基础 client：weChatClient 与入口访问器是同一个代理，其余 3 个也是 Feign 代理
     */
    @Test
    public void contextHoldsFeignProxiesForBaseClients() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());
        QywxContext context = client.context();

        assertSame(client.weChatClient(), context.weChatClient());
        assertTrue(Proxy.isProxyClass(context.workOaClient().getClass()));
        assertTrue(Proxy.isProxyClass(context.addressBookClient().getClass()));
        assertTrue(Proxy.isProxyClass(context.linkedCorpClient().getClass()));
        assertThrows(NullPointerException.class, () -> new QywxContext(null, client.weChatClient(),
                context.workOaClient(), context.addressBookClient(), context.linkedCorpClient()));
    }

    /**
     * OAService 没有自己的 client：getCorpCheckInOption 经上下文的 workOaClient 发 POST，应用名与 access_token 按既有规则拼进查询串
     */
    @Test
    public void oaServicePostsThroughContextWorkOaClient() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        recording.respond(200, ContractFeign.tokenBody("tok-1"));

        assertNotNull(new OAService(client.context()).getCorpCheckInOption(ContractFeign.APP));

        assertEquals(2, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request request = recording.request(1);
        assertEquals("POST", request.httpMethod().name());
        assertEquals(BASE + "checkin/getcorpcheckinoption?app=contract&access_token=tok-1", request.url());
        assertEquals("contract", ContractSnapshot.headerValues(request.headers()).get("app"));
    }

    /**
     * AuthenticationService 用上下文的 addressBookClient 取用户身份（authClient 只用于其余方法）
     */
    @Test
    public void authenticationServiceUsesContextAddressBookClient() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        AuthenticationService service = new AuthenticationService(client.context(), client.proxy(AuthClient.class));

        assertNotNull(service.getAuthentication("code-1", ContractFeign.APP));

        assertEquals(2, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request request = recording.request(1);
        assertEquals("GET", request.httpMethod().name());
        assertEquals(BASE + "user/getuserinfo?code=code-1&app=contract&access_token=tok-1", request.url());
    }

    /**
     * FrontEndToolService 先用上下文的 weChatClient 直接取号（不经缓存），再用自己持有的 frontEndToolsClient 取 ticket：
     * 拦截器按应用名另行取号，于是该请求的查询串里出现两个 access_token（5.x 快照即如此）
     */
    @Test
    public void frontEndToolServiceFetchesTokenDirectlyThenThroughInterceptor() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        recording.respond(200, ContractFeign.tokenBody("tok-2"));
        FrontEndToolService service = new FrontEndToolService(client.context(), client.proxy(FrontEndToolsClient.class));

        assertNotNull(service.getAppTicket(ContractFeign.APP));

        assertEquals(3, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        assertEquals(GET_TOKEN_URL, recording.request(1).url());
        Request request = recording.request(2);
        assertEquals("GET", request.httpMethod().name());
        assertEquals(BASE + "get_jsapi_ticket?access_token=tok-1&access_token=tok-2&app=contract", request.url());
    }

    /**
     * CommonService 只继承基类：checkApplication 对已配置的应用放行，对未知应用抛 ParamCheckException 并列出有效应用
     */
    @Test
    public void commonServiceValidatesApplicationName() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());
        CommonService service = new CommonService(client.context());

        service.checkApplication(ContractFeign.APP);

        ParamCheckException error = assertThrows(ParamCheckException.class, () -> service.checkApplication("other"));
        assertTrue(error.getMessage().contains("[contract]"), error.getMessage());
        assertThrows(ParamCheckException.class, () -> service.checkApplication(""));
    }

    private static void verifyService(QywxClient client, String name, Class<?>[] expectedClients) throws Exception {
        Class<?> type = Class.forName(SERVICE_PACKAGE + "." + name);
        Constructor<?>[] constructors = type.getConstructors();
        assertEquals(1, constructors.length, name + " 的 public 构造器数量");
        Constructor<?> constructor = constructors[0];
        Class<?>[] parameters = constructor.getParameterTypes();
        assertTrue(parameters.length >= 1, name + " 构造器缺少 QywxContext");
        assertEquals(QywxContext.class, parameters[0], name + " 构造器首参");
        assertArrayEquals(expectedClients, Arrays.copyOfRange(parameters, 1, parameters.length), name + " 构造器的 client 参数");
        assertEquals(AbstractBaseService.class, type.getSuperclass(), name + " 的父类");

        Object[] arguments = new Object[parameters.length];
        arguments[0] = client.context();
        for (int i = 1; i < parameters.length; i++) {
            arguments[i] = client.proxy(parameters[i]);
        }
        assertNotNull(constructor.newInstance(arguments), name + " 实例化");
    }

    /**
     * 列出 classpath 上某个包目录里编译出的类名（只看目录，jar 内的类由 P3.4 的静态守卫负责）
     */
    private static Set<String> compiledClassNames(String packageName) throws IOException, URISyntaxException {
        String resource = packageName.replace('.', '/');
        Set<String> names = new TreeSet<>();
        Enumeration<URL> urls = ServiceWiringTest.class.getClassLoader().getResources(resource);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            if (!"file".equals(url.getProtocol())) {
                continue;
            }
            File[] classes = new File(url.toURI()).listFiles((dir, fileName) -> fileName.endsWith(".class"));
            if (classes == null) {
                continue;
            }
            for (File clazz : classes) {
                String fileName = clazz.getName();
                names.add(fileName.substring(0, fileName.length() - ".class".length()));
            }
        }
        return names;
    }
}
