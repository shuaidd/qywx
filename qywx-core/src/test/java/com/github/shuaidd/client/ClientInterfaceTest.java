package com.github.shuaidd.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.contract.RecordingClient;
import com.github.shuaidd.response.BaseResponse;
import feign.Feign;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 已迁移到 qywx-core 的 client 接口的守卫测试
 * <p>
 * 迁移由 codemod 分批完成（P2.2–P2.6），每批把新落地的接口追加到 {@link #MIGRATED}：
 * <ul>
 * <li>逐个接口在 {@link QywxContract} 下创建代理：{@code target} 会解析全部方法的契约，
 * 模板变量与 {@code @Param} 不对应、落盘目标参数以外的未引用参数都会在这一步抛错</li>
 * <li>显式清单必须与 {@code com.github.shuaidd.client} 包里的接口一一对应，防止新增的 client 漏测；
 * 清单里的每个接口都必须出现在 5.2.0 契约快照的 manifest 中</li>
 * <li>5.x 的 {@code @RequestParam(required = false)} 为 null 时 Spring MVC 契约不发送该查询参数；
 * 原生 Feign 对 null 的 {@code @Param} 同样整体省略（{@code RequestTemplate#resolve}），用例锁定这一等价性</li>
 * </ul>
 * 请求形态（路径、查询参数、请求头、请求体）的逐条比对由 P2.7 的契约回放负责，这里不做重复断言。
 *
 * @author ddshuai
 * date 2026-11-26 07:26
 **/
class ClientInterfaceTest {

    private static final String CLIENT_PACKAGE_RESOURCE = "com/github/shuaidd/client";

    private static final String MANIFEST = "/contract/5.2.0/manifest.json";

    /** 常量接口，不是可以创建代理的 client */
    private static final Set<String> NOT_CLIENTS = Collections.singleton(CommonClient.class.getSimpleName());

    /** 已迁移的 client 接口（按迁移批次追加） */
    private static final List<Class<?>> MIGRATED = Collections.unmodifiableList(Arrays.<Class<?>>asList(
            // 批 1
            WeChatClient.class, WeChatMediaClient.class, FrontEndToolsClient.class, MeetingClient.class,
            ServiceProviderClient.class, PaymentUploadClient.class, AdvancedFeatureClient.class, AuthClient.class,
            ElectronicInvoiceClient.class, EnterpriseInterConnectionClient.class, HrClient.class,
            // 批 2
            GovernmentCommunicationClient.class, LinkedCorpClient.class, MessageClient.class, WorkApplicationClient.class,
            PaymentClient.class, SecurityClient.class, WechatSmartSheetClient.class, EnterpriseEmailClient.class));

    /**
     * 5.x 写法：{@code @RequestParam(value = "id", required = false) Integer id}，即可选的查询参数
     */
    interface OptionalQueryApi extends CommonClient {

        @RequestLine("GET /department/list?id={id}&app={app}")
        @Headers(HEAD)
        BaseResponse departmentList(@Param("id") Integer id, @Param("app") String app);
    }

    static Stream<Class<?>> migrated() {
        return MIGRATED.stream();
    }

    /**
     * 每个已迁移的接口都能在 QywxContract 下创建代理（即全部方法的契约解析通过）
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("migrated")
    void createsProxyUnderQywxContract(Class<?> type) {
        Object proxy = Feign.builder().contract(new QywxContract()).target(type, "http://localhost");

        assertNotNull(proxy);
        assertTrue(type.isInstance(proxy));
    }

    /**
     * 显式清单与 client 包里的接口一一对应：新增 client 后没有追加进清单时失败
     */
    @Test
    void migratedListMatchesInterfacesInClientPackage() throws IOException, URISyntaxException {
        assertEquals(clientInterfacesInPackage(), names(MIGRATED),
                "MIGRATED 必须与 com.github.shuaidd.client 包里的接口一一对应，新增 client 后要把它追加进清单");
    }

    /**
     * 清单里的每个接口都有 5.2.0 契约快照（全部迁移完成后清单与 manifest 相等）
     */
    @Test
    void everyMigratedInterfaceHasAContractSnapshot() throws IOException {
        assertTrue(manifestClients().containsAll(names(MIGRATED)), "清单里的接口都应出现在 5.2.0 契约快照中");
    }

    /**
     * 可选查询参数为 null 时整体省略，非 null 时按模板顺序出现（与 5.x Spring MVC 契约的行为一致）
     */
    @Test
    void omitsNullQueryParamLikeSpringMvcContract() {
        RecordingClient recording = new RecordingClient();
        OptionalQueryApi api = Feign.builder()
                .contract(new QywxContract())
                .client(recording)
                .decoder(new CustomJacksonDecoder())
                .target(OptionalQueryApi.class, RecordingClient.TARGET_URL);

        api.departmentList(null, "contract");
        api.departmentList(5, "contract");

        assertEquals(RecordingClient.TARGET_URL + "/department/list?app=contract", recording.request(0).url());
        assertEquals(RecordingClient.TARGET_URL + "/department/list?id=5&app=contract", recording.request(1).url());
    }

    /**
     * 扫描 classpath 上 {@code com.github.shuaidd.client} 包目录里的顶层 {@code *Client} 类型（不含内部类）
     */
    private static Set<String> clientInterfacesInPackage() throws IOException, URISyntaxException {
        Set<String> found = new TreeSet<>();
        Enumeration<URL> roots = ClientInterfaceTest.class.getClassLoader().getResources(CLIENT_PACKAGE_RESOURCE);
        while (roots.hasMoreElements()) {
            URL root = roots.nextElement();
            // surefire 与 IDE 都从目录加载 target/classes；换成其它协议时宁可失败，也不要让守卫静默失效
            assertEquals("file", root.getProtocol(), "守卫需要从目录读取 client 包：" + root);
            try (Stream<Path> files = Files.list(Paths.get(root.toURI()))) {
                files.map(path -> path.getFileName().toString())
                        .filter(name -> name.endsWith(".class") && !name.contains("$"))
                        .map(name -> name.substring(0, name.length() - ".class".length()))
                        .filter(name -> name.endsWith("Client") && !NOT_CLIENTS.contains(name))
                        .forEach(found::add);
            }
        }
        return found;
    }

    private static Set<String> manifestClients() throws IOException {
        try (InputStream in = ClientInterfaceTest.class.getResourceAsStream(MANIFEST)) {
            assertNotNull(in, "缺少契约快照 manifest：" + MANIFEST);
            JsonNode clients = new ObjectMapper().readTree(in).get("clients");
            Set<String> names = new TreeSet<>();
            clients.forEach(node -> names.add(node.asText()));
            return names;
        }
    }

    private static Set<String> names(List<Class<?>> types) {
        List<String> simple = new ArrayList<>();
        for (Class<?> type : types) {
            simple.add(type.getSimpleName());
        }
        return new TreeSet<>(simple);
    }
}
