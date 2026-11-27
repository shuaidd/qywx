package com.github.shuaidd;

import com.github.shuaidd.client.AddressBookClient;
import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.client.LinkedCorpClient;
import com.github.shuaidd.client.ServiceProviderClient;
import com.github.shuaidd.client.WeChatClient;
import com.github.shuaidd.client.WorkOaClient;
import com.github.shuaidd.client.config.CallbackProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.contract.ContractFeign;
import com.github.shuaidd.contract.ContractFeign.RecordingStore;
import com.github.shuaidd.contract.ContractSnapshot;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.support.CallBackManager;
import com.github.shuaidd.support.WeChatContextHolder;
import feign.Request;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 QywxClient 入口装配单元测试：上下文持有同一配置与 client、经真实代理取号并缓存、URL 归一、拦截器注入、
 * client 按接口类型记忆化
 *
 * @author ddshuai
 * date 2026-11-26 06:45
 **/
public class QywxClientTest {

    private static final String GET_TOKEN_URL =
            "http://contract.invalid/cgi-bin/gettoken?corpid=corp-contract&corpsecret=secret-contract";

    /**
     * QywxClient.create 会登记两处进程级静态入口，测试里动过就清掉，避免污染其他用例
     */
    @AfterEach
    public void resetStaticEntrances() {
        WeChatContextHolder.setWeChatManager(null);
        CallBackManager.properties(new ArrayList<CallbackProperties>());
    }

    /**
     * 上下文持有创建时传入的配置与同一个 client 代理
     */
    @Test
    public void contextHoldsSameConfigAndClient() {
        QywxConfig config = ContractFeign.config(false);
        QywxClient client = QywxClient.create(config, new RecordingClient());

        assertSame(config, client.config());
        assertSame(config, client.context().properties());
        assertSame(client.weChatClient(), client.context().weChatClient());
        assertNotNull(client.tokenManager());
        assertNotNull(client.tokenService());
    }

    /**
     * 首次取号经真实代理发 gettoken（无请求头、无请求体），二次命中缓存，清除缓存后重新取号
     */
    @Test
    public void fetchesTokenThroughGetTokenProxyAndCachesIt() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);

        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        assertEquals("tok-1", client.tokenService().getAccessToken(ContractFeign.APP));

        assertEquals(1, recording.requestCount());
        Request request = recording.request(0);
        assertEquals("GET", request.method());
        assertEquals(GET_TOKEN_URL, request.url());
        assertTrue(ContractSnapshot.headerValues(request.headers()).isEmpty());
        assertTrue(request.body() == null || request.body().length == 0);

        assertEquals("tok-1", client.tokenService().getAccessToken(ContractFeign.APP));
        assertEquals(1, recording.requestCount());

        client.tokenService().clearAccessToken(ContractFeign.APP);
        recording.respond(200, ContractFeign.tokenBody("tok-2"));
        assertEquals("tok-2", client.tokenService().getAccessToken(ContractFeign.APP));
        assertEquals(2, recording.requestCount());
    }

    /**
     * 配置了 tokenStore 时，令牌的写入与清除都落到该组件
     */
    @Test
    public void writesTokensToConfiguredStore() {
        RecordingStore store = new RecordingStore();
        QywxConfig config = ContractFeign.config(false);
        config.setTokenStore(store);
        RecordingClient recording = new RecordingClient();
        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        QywxClient client = QywxClient.create(config, recording);

        assertEquals("tok-1", client.tokenService().getAccessToken(ContractFeign.APP));
        assertEquals(Collections.singletonList(ContractFeign.APP), store.puts());
        assertEquals("tok-1", store.get(ContractFeign.APP).getAccessToken());

        client.tokenService().clearAccessToken(ContractFeign.APP);
        assertEquals(Collections.singletonList(ContractFeign.APP), store.evictions());
        assertNull(store.get(ContractFeign.APP));
    }

    /**
     * url 末尾斜杠与 publicPath 两端斜杠都被归一，gettoken 与业务请求的地址不受影响
     */
    @Test
    public void normalizesUrlAndPublicPathSlashes() {
        QywxConfig config = ContractFeign.config(false);
        config.setUrl("http://contract.invalid/");
        config.setPublicPath("/cgi-bin/");
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(config, recording);

        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        assertEquals("tok-1", client.tokenService().getAccessToken(ContractFeign.APP));
        assertEquals(GET_TOKEN_URL, recording.request(0).url());

        assertNotNull(client.weChatClient().apiDomainIp(ContractFeign.APP));
        assertEquals("http://contract.invalid/cgi-bin/get_api_domain_ip?app=contract&access_token=tok-1",
                recording.request(1).url());
    }

    /**
     * 业务调用经拦截器自动取号并注入 access_token，应用名请求头照常发送
     */
    @Test
    public void appliesInterceptorToBusinessCalls() {
        RecordingClient recording = new RecordingClient();
        QywxClient client = QywxClient.create(ContractFeign.config(false), recording);

        recording.respond(200, ContractFeign.tokenBody("tok-1"));
        assertNotNull(client.weChatClient().apiDomainIp(ContractFeign.APP));

        assertEquals(2, recording.requestCount());
        assertEquals(GET_TOKEN_URL, recording.request(0).url());
        Request business = recording.request(1);
        assertEquals("GET", business.method());
        assertEquals("http://contract.invalid/cgi-bin/get_api_domain_ip?app=contract&access_token=tok-1",
                business.url());
        assertEquals("contract", ContractSnapshot.headerValues(business.headers()).get("app"));
    }

    /**
     * 配置为 null 时直接拒绝创建
     */
    @Test
    public void rejectsNullConfig() {
        assertThrows(NullPointerException.class, () -> QywxClient.create(null));
    }

    /**
     * client(Class) 按接口类型记忆化：与构造期装配的代理是同一实例，快捷访问器与 context 里的也是同一个；
     * proxy(Class) 则每次新建，不进缓存
     */
    @Test
    public void exposesAssembledClientsByType() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());

        assertSame(client.weChatClient(), client.client(WeChatClient.class));
        assertSame(client.context().workOaClient(), client.client(WorkOaClient.class));
        assertSame(client.context().addressBookClient(), client.client(AddressBookClient.class));
        assertSame(client.context().linkedCorpClient(), client.client(LinkedCorpClient.class));
        assertSame(client.client(ServiceProviderClient.class), client.client(ServiceProviderClient.class));
        assertNotSame(client.client(ServiceProviderClient.class), client.proxy(ServiceProviderClient.class));
    }

    /**
     * client(Class) 只认装配清单里的 27 个接口：常量接口 CommonClient 与无关类型抛 IllegalArgumentException，null 抛 NPE
     */
    @Test
    public void rejectsClientTypesOutsideTheAssembly() {
        QywxClient client = QywxClient.create(ContractFeign.config(false), new RecordingClient());

        assertThrows(IllegalArgumentException.class, () -> client.client(CommonClient.class));
        assertThrows(IllegalArgumentException.class, () -> client.client(Runnable.class));
        assertThrows(NullPointerException.class, () -> client.client(null));
    }
}
