package com.github.shuaidd;

import com.github.shuaidd.callback.AesException;
import com.github.shuaidd.client.ServiceProviderClient;
import com.github.shuaidd.client.config.CallbackProperties;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import com.github.shuaidd.response.auth.ProviderTokenResponse;
import com.github.shuaidd.support.CallBackManager;
import com.github.shuaidd.support.WeChatContextHolder;
import com.github.shuaidd.support.WeChatManager;
import feign.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 {@link QywxClient} 完整装配的守卫：25 个 service 全部经 {@link WeChatManager} 可达、静态入口
 * （{@link WeChatContextHolder}/{@link CallBackManager}）在装配后登记，以及 {@code ServiceProviderClient}
 * 的特殊装配（不挂令牌拦截器、紧凑 JSON）经真实代理发出的报文
 *
 * @author ddshuai
 * date 2026-11-28 01:10
 **/
public class QywxAssemblyTest {

    private static final String GET_TOKEN_PATH = "/cgi-bin/gettoken";

    @AfterEach
    public void resetStaticEntrances() {
        //两处静态入口是进程级的，测试里动过就清掉，避免污染其他用例
        WeChatContextHolder.setWeChatManager(null);
        CallBackManager.properties(new ArrayList<CallbackProperties>());
    }

    /**
     * 装配完成后 25 个 service 全部可达，manager 持有的配置与 tokenService 就是客户端自己的那两份
     */
    @Test
    public void exposesEveryServiceThroughWeChatManager() {
        QywxConfig config = ContractFeign.config(false);
        QywxClient client = QywxClient.create(config, new RecordingClient());
        WeChatManager manager = client.weChatManager();

        assertNotNull(manager);
        assertSame(config, manager.properties());
        assertSame(client.tokenService(), manager.tokenService());
        assertAll(
                () -> assertNotNull(manager.addressBookService()),
                () -> assertNotNull(manager.authenticationService()),
                () -> assertNotNull(manager.applicationService()),
                () -> assertNotNull(manager.mediaService()),
                () -> assertNotNull(manager.messageService()),
                () -> assertNotNull(manager.oaService()),
                () -> assertNotNull(manager.customContactService()),
                () -> assertNotNull(manager.efficiencyTool()),
                () -> assertNotNull(manager.callCenterService()),
                () -> assertNotNull(manager.commonService()),
                () -> assertNotNull(manager.serviceProviderService()),
                () -> assertNotNull(manager.enterpriseEmailService()),
                () -> assertNotNull(manager.electronicInvoiceService()),
                () -> assertNotNull(manager.enterpriseInterConnectionService()),
                () -> assertNotNull(manager.meetingService()),
                () -> assertNotNull(manager.frontEndToolService()),
                () -> assertNotNull(manager.documentService()),
                () -> assertNotNull(manager.smartSheetService()),
                () -> assertNotNull(manager.paymentService()),
                () -> assertNotNull(manager.schoolService()),
                () -> assertNotNull(manager.advancedFeatureService()),
                () -> assertNotNull(manager.governmentCommunicationService()),
                () -> assertNotNull(manager.hrService()),
                () -> assertNotNull(manager.securityService()));
    }

    /**
     * 静态入口与 5.x 的自动装配对齐：Holder 里登记的就是这个 manager，CallBackManager 收到的是配置里的回调列表
     */
    @Test
    public void registersStaticEntrancesLikeTheAutoConfiguration() throws AesException {
        CallbackProperties callback = new CallbackProperties();
        callback.setApplicationName("cb-app");
        callback.setToken("token");
        callback.setEncodingAesKey("too-short");
        callback.setReceiveId("receive");
        QywxConfig config = ContractFeign.config(false);
        config.setCallbackList(new ArrayList<>(java.util.Collections.singletonList(callback)));
        QywxClient client = QywxClient.create(config, new RecordingClient());

        assertSame(client.weChatManager(), WeChatContextHolder.getWeChatManager());
        //已登记的应用会走到密钥校验（这里故意给非法 aesKey，证明列表确实生效），未登记的应用直接返回空串
        assertThrows(AesException.class, () -> CallBackManager.verifyUrl("cb-app", "sig", "ts", "nonce", "echo"));
        assertEquals("", CallBackManager.verifyUrl("not-configured", "sig", "ts", "nonce", "echo"));
    }

    /**
     * ServiceProviderClient 不走令牌通道：没有 app 头、没有 access_token、不取号，请求体是紧凑 JSON
     */
    @Test
    public void assemblesServiceProviderClientWithoutTokenChannel() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        ProviderTokenRequest request = new ProviderTokenRequest();
        request.setCorpId("provider-corp");
        request.setProviderSecret("provider-secret");

        ProviderTokenResponse response = client.weChatManager().serviceProviderService().getProviderToken(request);

        assertNotNull(response);
        assertEquals(1, recording.requestCount(), "不应先取号：ServiceProviderClient 只发一次请求");
        Request sent = recording.request(0);
        Map<String, String> headers = ContractSnapshot.headerValues(sent.headers());
        assertEquals("POST", sent.httpMethod().name());
        assertTrue(sent.url().endsWith("/service/get_provider_token"), sent.url());
        assertFalse(sent.url().contains("access_token"), sent.url());
        assertFalse(headers.containsKey("app"), "ServiceProviderClient 的接口不传应用名");
        assertEquals(JacksonJsonEncoder.JSON_CONTENT_TYPE, headers.get("content-type"));
        assertEquals("{\"corpid\":\"provider-corp\",\"provider_secret\":\"provider-secret\"}",
                new String(sent.body(), StandardCharsets.UTF_8));
    }

    /**
     * 普通业务 client 仍然走令牌通道：先 gettoken，再把 access_token 与 app 头带进业务请求
     */
    @Test
    public void keepsTokenChannelForBusinessClients() {
        RecordingClient recording = new RecordingClient();
        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);

        client.weChatManager().authenticationService().apiDomainIp(ContractFeign.APP);

        assertEquals(2, recording.requestCount());
        assertTrue(recording.request(0).url().contains(GET_TOKEN_PATH));
        Request business = recording.request(1);
        assertTrue(business.url().contains("access_token=tok-1"), business.url());
        assertEquals(ContractFeign.APP, ContractSnapshot.headerValues(business.headers()).get("app"));
    }

    /**
     * {@code proxy} 与装配走同一分支：ServiceProviderClient 经 proxy 再建一个代理也不挂令牌拦截器
     */
    @Test
    public void proxyFollowsTheSameBranchForServiceProviderClient() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);
        recording.respond(200, "{\"errcode\":0,\"errmsg\":\"ok\",\"provider_access_token\":\"p-1\",\"expires_in\":7200}");

        ServiceProviderClient proxy = client.proxy(ServiceProviderClient.class);
        ProviderTokenRequest request = new ProviderTokenRequest();
        request.setCorpId("c");
        request.setProviderSecret("s");
        assertNotNull(proxy.getProviderToken(request));

        assertEquals(1, recording.requestCount());
        assertFalse(ContractSnapshot.headerValues(recording.request(0).headers()).containsKey("app"));
    }
}