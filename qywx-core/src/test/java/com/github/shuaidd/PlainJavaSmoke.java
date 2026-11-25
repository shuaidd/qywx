package com.github.shuaidd;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.resquest.addressbook.MobileHashCodeRequest;
import com.github.shuaidd.response.addressbook.MobileHashCodeResponse;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import feign.Util;
import feign.codec.DecodeException;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 描述 纯 Java 冒烟：不经 Spring、不经 JUnit，用 {@code java -cp} 直接运行，证明 qywx-core 在零 Spring 的 classpath 上
 * 能走通「取号并缓存 → 拦截器注入 access_token → POST 缩进 JSON → 令牌失效（42001）清除缓存并重试一次 → 重试仍失败原样抛出」
 * <p>
 * 场景对着进程内的 JDK {@code HttpServer} 假服务跑，HTTP 走 {@code QywxClient.create(config)} 默认的
 * {@code Client.Default}（HttpURLConnection），不调用真实企业微信接口。假服务按脚本逐条核对
 * method / 地址 / {@code app} 请求头 / Content-Type / 请求体，任何不符都记下来，最后统一断言。
 * <p>
 * 手工运行（surefire 的 {@code java.class.path} 是它的 booter jar，断言不到 Spring，所以 classpath 检查只放在 {@link #main}）：
 * <pre>
 * mvn -B -pl qywx-core -am test-compile dependency:build-classpath "-Dmdep.outputFile=target/smoke-classpath.txt" "-Dmdep.includeScope=runtime"
 * java -cp "qywx-core/target/classes;qywx-core/target/test-classes;&lt;target/smoke-classpath.txt 的内容&gt;" com.github.shuaidd.PlainJavaSmoke
 * </pre>
 *
 * @author ddshuai
 * date 2026-11-26 07:05
 **/
public final class PlainJavaSmoke {

    private static final String CORP_ID = "corp-smoke";

    private static final String APP = "smoke";

    private static final String SECRET = "secret-smoke";

    private static final String GET_TOKEN_TARGET = "/cgi-bin/gettoken?corpid=" + CORP_ID + "&corpsecret=" + SECRET;

    private static final String DOMAIN_IP_PATH = "/cgi-bin/get_api_domain_ip?app=" + APP + "&access_token=";

    private static final String HASH_PATH = "/cgi-bin/user/get_mobile_hashcode?app=" + APP + "&access_token=";

    private static final String IP_BODY = "{\"errcode\":0,\"errmsg\":\"ok\",\"ip_list\":[\"1.2.3.4\"]}";

    private static final String EXPIRED_BODY = "{\"errcode\":42001,\"errmsg\":\"access_token expired\"}";

    private static final String HASH_RESPONSE = "{\"errcode\":0,\"errmsg\":\"ok\",\"hashcode\":\"hash-smoke\"}";

    /** 与 5.x 线上一致的缩进 JSON；Jackson 的换行是系统换行符，假服务端已把 \r\n 归一为 \n */
    private static final String HASH_REQUEST = "{\n  \"mobile\" : \"13800000000\",\n  \"state\" : \"smoke\"\n}";

    private PlainJavaSmoke() {
    }

    /**
     * 纯 Java 入口：先确认 classpath 上没有 Spring，再跑冒烟场景
     *
     * @param args 未使用
     * @throws Exception 启动假服务或发起请求失败
     */
    public static void main(String[] args) throws Exception {
        assertNoSpringOnClasspath();
        run();
        System.out.println("PLAIN-JAVA SMOKE OK");
    }

    /**
     * 冒烟场景，JUnit 用例与 {@link #main} 共用
     *
     * @throws Exception 启动假服务或发起请求失败
     */
    public static void run() throws Exception {
        FakeServer server = new FakeServer(Arrays.asList(
                step("GET", GET_TOKEN_TARGET, null, null, null, tokenBody("tok-1")),
                step("GET", DOMAIN_IP_PATH + "tok-1", APP, null, null, IP_BODY),
                step("POST", HASH_PATH + "tok-1", APP, JacksonJsonEncoder.FORM_CONTENT_TYPE, HASH_REQUEST, HASH_RESPONSE),
                step("GET", DOMAIN_IP_PATH + "tok-1", APP, null, null, EXPIRED_BODY),
                step("GET", GET_TOKEN_TARGET, null, null, null, tokenBody("tok-2")),
                step("GET", DOMAIN_IP_PATH + "tok-2", APP, null, null, IP_BODY),
                step("GET", DOMAIN_IP_PATH + "tok-2", APP, null, null, EXPIRED_BODY),
                step("GET", GET_TOKEN_TARGET, null, null, null, tokenBody("tok-3")),
                step("GET", DOMAIN_IP_PATH + "tok-3", APP, null, null, EXPIRED_BODY)));
        try {
            QywxClient client = QywxClient.create(QywxConfig.builder()
                    .corpId(CORP_ID)
                    .url("http://127.0.0.1:" + server.port())
                    .application(application())
                    .build());

            //① 取号：第一次发 gettoken，第二次命中缓存
            String first = client.tokenService().getAccessToken(APP);
            String second = client.tokenService().getAccessToken(APP);
            check("tok-1".equals(first) && first.equals(second),
                    "取号与缓存命中：期望两次都是 tok-1，实际 " + first + " / " + second);
            check(server.received() == 1, "缓存命中：期望只发 1 次请求，实际 " + server.received());
            passed("token fetched and cached");

            //② 业务 GET：拦截器按请求头里的应用名取号并追加 access_token
            ApiDomainIpResponse ips = client.weChatClient().apiDomainIp(APP);
            check(Collections.singletonList("1.2.3.4").equals(ips.getIpList()),
                    "域名 IP：期望 [1.2.3.4]，实际 " + ips.getIpList());
            check(server.received() == 2, "业务 GET：期望累计 2 次请求，实际 " + server.received());
            passed("business GET with access_token");

            //③ 业务 POST：请求体为缩进 JSON，Content-Type 与 5.x 线上一致
            MobileHashCodeRequest request = new MobileHashCodeRequest();
            request.setMobile("13800000000");
            request.setState("smoke");
            MobileHashCodeResponse hash = client.weChatClient().getMobileHashcode(request, APP);
            check("hash-smoke".equals(hash.getHashcode()),
                    "手机号随机串：期望 hash-smoke，实际 " + hash.getHashcode());
            check(server.received() == 3, "业务 POST：期望累计 3 次请求，实际 " + server.received());
            passed("business POST with indented JSON");

            //④ 令牌失效：42001 → 清除缓存 → 重新取号（tok-2）→ 重试一次成功
            ApiDomainIpResponse retried = client.weChatClient().apiDomainIp(APP);
            check(Collections.singletonList("1.2.3.4").equals(retried.getIpList()),
                    "令牌失效重试：期望重试后拿到 IP 段，实际 " + retried.getIpList());
            check(server.received() == 6, "令牌失效重试：期望累计 6 次请求，实际 " + server.received());
            check("tok-2".equals(client.tokenService().getAccessToken(APP)),
                    "令牌失效重试：期望缓存换成 tok-2，实际 " + client.tokenService().getAccessToken(APP));
            check(server.received() == 6, "令牌失效重试：读缓存不应再发请求，实际 " + server.received() + " 次");
            passed("42001 retried once with a fresh token");

            //⑤ 重试仍失败：只重试一次，对外仍是 DecodeException 包 WeChatException，缓存里是重试时取到的新令牌
            RuntimeException failure = callExpectingFailure(client);
            check(failure != null, "重试仍失败：期望抛出异常，实际正常返回");
            check(failure instanceof DecodeException,
                    "重试仍失败：期望 feign.codec.DecodeException，实际 " + failure.getClass().getName());
            WeChatException weChat = findWeChatException(failure);
            check(weChat != null && ErrorCode.ERROR_CODE_42001.getErrorCode().equals(weChat.getCode()),
                    "重试仍失败：期望 cause 链里有 42001 的 WeChatException，实际 " + weChat);
            check(server.received() == 9, "重试仍失败：期望累计 9 次请求（只重试一次），实际 " + server.received());
            check("tok-3".equals(client.tokenService().getAccessToken(APP)),
                    "重试仍失败：期望缓存为重试时取到的 tok-3，实际 " + client.tokenService().getAccessToken(APP));
            check(server.received() == 9, "重试仍失败：读缓存不应再发请求，实际 " + server.received() + " 次");
            passed("second failure rethrown as-is");

            server.verify();
        } finally {
            server.stop();
        }
    }

    /**
     * 确认 classpath 上没有任何含 {@code org/springframework} 的目录或 jar
     *
     * @throws IOException 读取 jar 失败
     */
    static void assertNoSpringOnClasspath() throws IOException {
        String classPath = System.getProperty("java.class.path");
        if (classPath == null || classPath.trim().isEmpty()) {
            throw new IllegalStateException("java.class.path 为空，无法确认 classpath 上没有 Spring");
        }
        List<String> hits = new ArrayList<String>();
        int entries = 0;
        for (String entry : classPath.split(File.pathSeparator)) {
            if (entry.trim().isEmpty()) {
                continue;
            }
            entries++;
            if (containsSpring(entry)) {
                hits.add(entry);
            }
        }
        if (!hits.isEmpty()) {
            throw new IllegalStateException("classpath 含 Spring 构件：" + hits);
        }
        System.out.println("classpath checked: " + entries + " entries, no org/springframework");
    }

    private static boolean containsSpring(String entry) throws IOException {
        File file = new File(entry);
        if (file.isDirectory()) {
            return new File(file, "org/springframework").exists();
        }
        if (!file.isFile()) {
            return false;
        }
        try (ZipFile zip = new ZipFile(file)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                if (entries.nextElement().getName().startsWith("org/springframework/")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 调用业务接口并返回抛出的 RuntimeException，正常返回则为 null
     */
    private static RuntimeException callExpectingFailure(QywxClient client) {
        try {
            client.weChatClient().apiDomainIp(APP);
            return null;
        } catch (RuntimeException e) {
            return e;
        }
    }

    /**
     * 沿 cause 链查找 WeChatException，最多 10 层
     */
    private static WeChatException findWeChatException(Throwable failure) {
        Throwable current = failure;
        for (int depth = 0; current != null && depth < 10; depth++) {
            if (current instanceof WeChatException) {
                return (WeChatException) current;
            }
            current = current.getCause();
        }
        return null;
    }

    private static ApplicationProperties application() {
        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(APP);
        application.setSecret(SECRET);
        return application;
    }

    private static String tokenBody(String token) {
        return "{\"errcode\":0,\"errmsg\":\"ok\",\"access_token\":\"" + token + "\",\"expires_in\":7200}";
    }

    private static Step step(String method, String target, String app, String contentType, String body, String response) {
        return new Step(method, target, app, contentType, body, response);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void passed(String stage) {
        System.out.println("[smoke] " + stage + " ok");
    }

    /**
     * 脚本中的一条期望；请求头或请求体为 null 表示应当缺省
     */
    private static final class Step {

        private final String method;

        private final String target;

        private final String app;

        private final String contentType;

        private final String body;

        private final String response;

        private Step(String method, String target, String app, String contentType, String body, String response) {
            this.method = method;
            this.target = target;
            this.app = app;
            this.contentType = contentType;
            this.body = body;
            this.response = response;
        }

        private List<String> diff(HttpExchange exchange, String target, String body) {
            List<String> diffs = new ArrayList<String>();
            expect(diffs, "method", this.method, exchange.getRequestMethod());
            expect(diffs, "地址", this.target, target);
            expect(diffs, "请求头 app", this.app, exchange.getRequestHeaders().getFirst("app"));
            expect(diffs, "Content-Type", this.contentType, exchange.getRequestHeaders().getFirst("Content-Type"));
            expect(diffs, "请求体", this.body, body.isEmpty() ? null : body);
            return diffs;
        }

        private static void expect(List<String> diffs, String name, String expected, String actual) {
            if (!Objects.equals(expected, actual)) {
                diffs.add(name + " 期望 " + expected + "，实际 " + actual);
            }
        }
    }

    /**
     * 进程内假服务：按脚本顺序应答，并逐条核对收到的请求
     */
    private static final class FakeServer {

        private static final String UNEXPECTED_BODY = "{\"errcode\":-1,\"errmsg\":\"unexpected request\"}";

        private final HttpServer server;

        private final List<Step> script;

        private final AtomicInteger received = new AtomicInteger();

        private final List<String> mismatches = Collections.synchronizedList(new ArrayList<String>());

        private FakeServer(List<Step> script) throws IOException {
            this.script = script;
            this.server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
            this.server.createContext("/", this::handle);
            this.server.start();
        }

        private int port() {
            return server.getAddress().getPort();
        }

        private int received() {
            return received.get();
        }

        private void stop() {
            server.stop(0);
        }

        /**
         * 收到的请求数与脚本条数一致，且逐条核对无误；服务线程里不抛异常，统一在这里断言
         */
        private void verify() {
            check(script.size() == received(), "请求数：期望 " + script.size() + "，实际 " + received());
            check(mismatches.isEmpty(), "请求与脚本不符：" + mismatches);
        }

        private void handle(HttpExchange exchange) throws IOException {
            int index = received.getAndIncrement();
            Step step = index < script.size() ? script.get(index) : null;
            String body = new String(Util.toByteArray(exchange.getRequestBody()), StandardCharsets.UTF_8)
                    .replace("\r\n", "\n");
            String target = exchange.getRequestURI().getRawPath();
            String query = exchange.getRequestURI().getRawQuery();
            if (query != null) {
                target = target + "?" + query;
            }
            if (step == null) {
                mismatches.add("#" + index + " 超出脚本的请求：" + exchange.getRequestMethod() + " " + target);
            } else {
                List<String> diffs = step.diff(exchange, target, body);
                if (!diffs.isEmpty()) {
                    mismatches.add("#" + index + " 期望 " + step.method + " " + step.target + "，" + String.join("；", diffs));
                }
            }
            respond(exchange, step == null ? UNEXPECTED_BODY : step.response);
        }

        private static void respond(HttpExchange exchange, String body) throws IOException {
            byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json;charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(bytes);
            }
        }
    }
}
