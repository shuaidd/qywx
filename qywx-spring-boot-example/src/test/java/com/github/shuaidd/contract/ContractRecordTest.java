package com.github.shuaidd.contract;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.shuaidd.Application;
import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.support.WeChatManager;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 契约录制器（仅用于迁移基线，不访问真实企业微信）。
 * <p>
 * 以 5.2.0（Spring 版）实际发出的 HTTP 请求为基线：反射遍历全部 client 接口方法，
 * 用假参数调用本地伪服务器，把 method / path / query / headers / body / 结果摘要
 * 录制到 qywx-core 的测试资源目录，供 6.0.0 纯 Java 实现逐条回放比对。
 * <p>
 * 通过 spring.config.name=contract-record 隔离，不加载含真实凭据的 application.yml。
 * 重新生成基线需临时把 example 的 starter 版本指回 5.2.0。
 */
@SpringBootTest(classes = Application.class, properties = {
        "spring.config.name=contract-record",
        "spring.cache.type=simple",
        "qywx.debug-mode=false",
        "logging.level.com.github.shuaidd=WARN"
})
class ContractRecordTest {

    private static final Logger LOG = LoggerFactory.getLogger(ContractRecordTest.class);

    private static final String APP = "contract";
    private static final String TOKEN_PREFIX = "TOKEN_";
    private static final byte[] DOWNLOAD_BYTES = "CONTRACT-DOWNLOAD-CONTENT".getBytes(StandardCharsets.UTF_8);
    private static final String FILE_CONTENT = "CONTRACT-FILE-CONTENT";
    private static final String FILE_NAME = "contract.bin";
    private static final Pattern BOUNDARY = Pattern.compile("boundary=\"?([^\";]+)\"?");
    private static final Pattern TOKEN_QUERY = Pattern.compile("access_token=[^&]*");
    private static final Pattern TMPDIR_QUERY = Pattern.compile("downloadFilePath=[^&]*");

    private static final HttpServer SERVER = startServer();
    private static final List<RequestRecord> REQUESTS = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicInteger TOKEN_SEQ = new AtomicInteger();
    private static final AtomicInteger RETRY_FIRED = new AtomicInteger();

    /** 串行录制：调用线程阻塞等待响应期间，handler 线程读取当前调用上下文。 */
    private static volatile CallContext current;
    /** 场景 B：该调用的首个业务请求返回 42001。 */
    private static volatile String retryCallId;
    /** 场景 C：该调用的下载响应带 error-code 头。 */
    private static volatile String downloadErrorCallId;

    @DynamicPropertySource
    static void dynamicProps(DynamicPropertyRegistry registry) {
        registry.add("qywx.url", () -> "http://127.0.0.1:" + SERVER.getAddress().getPort());
    }

    @Autowired
    private ApplicationContext context;

    @Autowired
    private WeChatManager weChatManager;

    private final ObjectMapper mapper = new ObjectMapper();
    /** 参数值统一转 JSON 树：回放端按参数类型反序列化即可重建等价的 dummy 参数。 */
    private final ObjectMapper paramMapper = new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    @Test
    void record() throws Exception {
        List<Class<?>> clients = discoverClients();
        Map<String, List<Map<String, Object>>> byClient = new TreeMap<>();
        int expected = 0;
        for (Class<?> client : clients) {
            expected += recordClient(client, byClient);
        }
        recordScenarios(byClient);
        write(byClient, expected);
    }

    private List<Class<?>> discoverClients() throws Exception {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }
        };
        // 只要 client 包（不含子包）下的接口：ServiceProviderClient 不继承 CommonClient，不能按 CommonClient 过滤
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile("com\\.github\\.shuaidd\\.client\\.[A-Z]\\w*")));
        List<Class<?>> clients = new ArrayList<>();
        for (BeanDefinition definition : provider.findCandidateComponents("com.github.shuaidd.client")) {
            Class<?> type = Class.forName(definition.getBeanClassName());
            if (CommonClient.class.equals(type) || !hasMapping(type)) {
                continue;
            }
            clients.add(type);
        }
        clients.sort(Comparator.comparing(Class::getSimpleName));
        LOG.info("发现 client 接口 {} 个", clients.size());
        return clients;
    }

    private static boolean hasMapping(Class<?> type) {
        for (Method method : type.getDeclaredMethods()) {
            if (method.isAnnotationPresent(GetMapping.class) || method.isAnnotationPresent(PostMapping.class)) {
                return true;
            }
        }
        return false;
    }

    private int recordClient(Class<?> client, Map<String, List<Map<String, Object>>> byClient) throws Exception {
        Object bean = context.getBean(client);
        String simpleName = client.getSimpleName();
        List<Method> methods = Arrays.stream(client.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(GetMapping.class) || m.isAnnotationPresent(PostMapping.class))
                .sorted(Comparator.comparing(Method::toGenericString))
                .collect(Collectors.toList());
        for (Method method : methods) {
            Invocation invocation = prepare(client, method, bean);
            Map<String, Object> entry = invoke(simpleName, invocation);
            byClient.computeIfAbsent(simpleName, k -> new ArrayList<>()).add(entry);
        }
        return methods.size();
    }

    private Invocation prepare(Class<?> client, Method method, Object bean) throws Exception {
        String id = client.getSimpleName() + "." + method.getName();
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        List<Map<String, Object>> recordedParams = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
            RequestPart requestPart = parameter.getAnnotation(RequestPart.class);
            String name = requestParam != null ? requestParam.value()
                    : requestPart != null ? requestPart.value() : parameter.getName();
            if (requestPart != null || MultipartFile.class.isAssignableFrom(parameter.getType())) {
                String partName = name.isEmpty() ? "file" : name;
                byte[] content = FILE_CONTENT.getBytes(StandardCharsets.UTF_8);
                args[i] = new MockMultipartFile(partName, FILE_NAME, "image/png", content);
                Map<String, Object> recorded = new LinkedHashMap<>();
                recorded.put("type", parameter.getParameterizedType().getTypeName());
                recorded.put("kind", "file");
                recorded.put("partName", partName);
                recorded.put("filename", FILE_NAME);
                recorded.put("contentType", "image/png");
                recorded.put("contentBase64", Base64.getEncoder().encodeToString(content));
                recordedParams.add(recorded);
                continue;
            }
            Object value = dummyValue(parameter.getType(), parameter.getParameterizedType(), name);
            args[i] = value;
            recordedParams.add(valueParam(parameter.getParameterizedType().getTypeName(),
                    "downloadFilePath".equals(name) ? "{{TMPDIR}}" : value));
        }
        boolean download = File.class.equals(method.getReturnType())
                || method.getReturnType().getTypeName().endsWith("ResponseEntity");
        return new Invocation(id, method, bean, args, recordedParams, download, "default");
    }

    private Map<String, Object> valueParam(String type, Object value) {
        Map<String, Object> recorded = new LinkedHashMap<>();
        recorded.put("type", type);
        recorded.put("kind", "value");
        recorded.put("value", value == null ? null : paramMapper.valueToTree(value));
        return recorded;
    }

    private Map<String, Object> invoke(String clientName, Invocation invocation) {
        int start = REQUESTS.size();
        current = new CallContext(invocation.id, invocation.download);
        Object result = null;
        Throwable thrown = null;
        try {
            result = invocation.method.invoke(invocation.bean, invocation.args);
        } catch (InvocationTargetException e) {
            thrown = e.getCause();
        } catch (Throwable t) {
            thrown = t;
        } finally {
            current = null;
        }
        List<RequestRecord> records;
        synchronized (REQUESTS) {
            records = new ArrayList<>(REQUESTS.subList(start, REQUESTS.size()));
        }
        Map<String, Integer> tokens = new LinkedHashMap<>();
        List<Map<String, Object>> requests = new ArrayList<>();
        for (RequestRecord record : records) {
            requests.add(record.toMap(tokens));
        }

        Map<String, Object> entry = new LinkedHashMap<>();
        entry.put("id", invocation.id);
        entry.put("scenario", invocation.scenario);
        entry.put("client", clientName);
        entry.put("method", invocation.method.getName());
        entry.put("returnType", invocation.method.getGenericReturnType().getTypeName());
        entry.put("params", invocation.params);
        entry.put("result", resultSummary(result, thrown, invocation));
        entry.put("requests", requests);
        return entry;
    }

    private Map<String, Object> resultSummary(Object result, Throwable thrown, Invocation invocation) {
        Map<String, Object> summary = new LinkedHashMap<>();
        if (thrown == null) {
            summary.put("kind", "ok");
            summary.put("type", result == null ? "null" : result.getClass().getName());
            if (result instanceof File) {
                summary.put("fileName", ((File) result).getName());
            }
            return summary;
        }
        summary.put("kind", "throw");
        summary.put("type", thrown.getClass().getName());
        // 非 WeChatException（录制器或 5.2.0 自身的缺陷）也保留 message，便于判定 6.0.0 的预期行为
        if (!(thrown instanceof WeChatException)) {
            summary.put("msg", thrown.getMessage() == null ? null : truncate(thrown.getMessage()));
        }
        if (thrown instanceof WeChatException) {
            WeChatException e = (WeChatException) thrown;
            summary.put("errorCode", e.getErrorCode() == null ? null : e.getErrorCode().getErrorCode());
            summary.put("msg", e.getMessage());
        }
        // Feign 可能把 WeChatException 包在 DecodeException 里，保留根因便于比对
        Throwable root = thrown;
        while (root.getCause() != null && root.getCause() != root) {
            root = root.getCause();
        }
        if (root instanceof WeChatException && root != thrown) {
            WeChatException e = (WeChatException) root;
            summary.put("rootType", e.getClass().getName());
            summary.put("rootErrorCode", e.getErrorCode() == null ? null : e.getErrorCode().getErrorCode());
        }
        if (root != thrown && root.getMessage() != null) {
            summary.put("rootType", root.getClass().getName());
            summary.put("rootMsg", truncate(root.getMessage()));
        }
        return summary;
    }

    private static String truncate(String message) {
        return message.length() > 500 ? message.substring(0, 500) + "..." : message;
    }

    private static String normalizeQuery(String query, Map<String, Integer> tokens) {
        String q = query == null ? "" : query;
        Matcher matcher = TOKEN_QUERY.matcher(q);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            Integer n = tokens.get("token");
            int next = n == null ? 1 : n + 1;
            tokens.put("token", next);
            matcher.appendReplacement(sb, Matcher.quoteReplacement("access_token={{TOKEN_" + next + "}}"));
        }
        matcher.appendTail(sb);
        return TMPDIR_QUERY.matcher(sb.toString()).replaceAll("downloadFilePath={{TMPDIR}}");
    }

    private static String normalizeContentType(String contentType) {
        if (contentType == null) {
            return null;
        }
        Matcher matcher = BOUNDARY.matcher(contentType);
        if (matcher.find()) {
            return contentType.replace(matcher.group(1), "{{BOUNDARY}}");
        }
        return contentType;
    }

    private static String normalizeBody(byte[] body, String contentType) {
        if (body == null || body.length == 0) {
            return null;
        }
        String ct = contentType == null ? "" : contentType;
        if (ct.startsWith("multipart/form-data")) {
            String text = new String(body, StandardCharsets.ISO_8859_1);
            Matcher matcher = BOUNDARY.matcher(ct);
            if (matcher.find()) {
                text = text.replace(matcher.group(1), "{{BOUNDARY}}");
            }
            return text;
        }
        return new String(body, StandardCharsets.UTF_8);
    }

    @SuppressWarnings("unchecked")
    private Object dummyValue(Class<?> type, java.lang.reflect.Type generic, String name) throws Exception {
        if (String.class.equals(type)) {
            if ("app".equals(name)) {
                return APP;
            }
            if ("downloadFilePath".equals(name)) {
                return System.getProperty("java.io.tmpdir");
            }
            if ("corpid".equals(name)) {
                return "corp-contract";
            }
            if ("corpsecret".equals(name)) {
                return "secret-contract";
            }
            return "v_" + name;
        }
        return fill(type, generic, 0, new HashSet<>());
    }

    @SuppressWarnings("unchecked")
    private Object fill(Class<?> type, java.lang.reflect.Type generic, int depth, Set<Class<?>> path) throws Exception {
        if (type == Integer.class || type == int.class) {
            return 7;
        }
        if (type == Long.class || type == long.class) {
            return 8L;
        }
        if (type == Boolean.class || type == boolean.class) {
            return Boolean.TRUE;
        }
        if (type == Double.class || type == double.class) {
            return 1.5d;
        }
        if (type == Float.class || type == float.class) {
            return 2.5f;
        }
        if (type == Short.class || type == short.class) {
            return (short) 3;
        }
        if (type == Byte.class || type == byte.class) {
            return (byte) 4;
        }
        if (type == Character.class || type == char.class) {
            return 'c';
        }
        if (Date.class.isAssignableFrom(type)) {
            return new Date(1700000000000L);
        }
        if (BigDecimal.class.isAssignableFrom(type)) {
            return new BigDecimal("12.34");
        }
        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }
        if (Map.class.isAssignableFrom(type)) {
            // 按声明的泛型值类型填充，避免 Map<String, List<String>> 这类字段序列化失败
            java.lang.reflect.Type valueType = typeArgument(generic, 1);
            Class<?> valueRaw = rawClass(valueType);
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("k", valueRaw == null ? "v" : fill(valueRaw, valueType, depth + 1, path));
            return map;
        }
        if (Collection.class.isAssignableFrom(type)) {
            java.lang.reflect.Type elementType = typeArgument(generic, -1);
            Class<?> element = rawClass(elementType);
            Object item = element == null ? null : fill(element, elementType, depth + 1, path);
            if (Set.class.isAssignableFrom(type)) {
                return item == null ? new HashSet<>() : new HashSet<>(Collections.singletonList(item));
            }
            return item == null ? new ArrayList<>() : new ArrayList<>(Collections.singletonList(item));
        }
        if (type.isArray()) {
            Object array = java.lang.reflect.Array.newInstance(type.getComponentType(), 1);
            Object item = fill(type.getComponentType(), type.getComponentType(), depth + 1, path);
            java.lang.reflect.Array.set(array, 0, item);
            return array;
        }
        if (Object.class.equals(type)) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("k", "v");
            return map;
        }
        if (!type.getName().startsWith("com.github.shuaidd") || depth > 4 || path.contains(type)) {
            return null;
        }
        Object instance;
        try {
            instance = type.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            // 无无参构造器（如 MsgMarkdown(String content)、ConvertUserIdOpenIdRequest）：
            // 改用参数最少的构造器，String 参数用 "f_"+参数名，其他类型递归填充
            instance = newInstanceViaShortestConstructor(type, depth, path);
            if (instance == null) {
                return null;
            }
        }
        Set<Class<?>> nextPath = new HashSet<>(path);
        nextPath.add(type);
        for (Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                java.lang.reflect.Type fieldGeneric = field.getGenericType();
                Class<?> fieldType = field.getType();
                Object value;
                if (String.class.equals(fieldType)) {
                    value = "f_" + field.getName();
                } else {
                    value = fill(fieldType, fieldGeneric, depth + 1, nextPath);
                }
                try {
                    field.setAccessible(true);
                    field.set(instance, value);
                } catch (Exception ignored) {
                    // final 字段等无法赋值时保持默认值
                }
            }
        }
        return instance;
    }

    /** 无无参构造器时，用参数最少的构造器构造实例（参数按类型递归填充，失败返回 null）。 */
    private Object newInstanceViaShortestConstructor(Class<?> type, int depth, Set<Class<?>> path) {
        java.lang.reflect.Constructor<?> best = null;
        for (java.lang.reflect.Constructor<?> c : type.getDeclaredConstructors()) {
            if (best == null || c.getParameterCount() < best.getParameterCount()) {
                best = c;
            }
        }
        if (best == null) {
            return null;
        }
        Set<Class<?>> nextPath = new HashSet<>(path);
        nextPath.add(type);
        Class<?>[] paramTypes = best.getParameterTypes();
        java.lang.reflect.Type[] paramGenerics = best.getGenericParameterTypes();
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            try {
                if (String.class.equals(paramTypes[i])) {
                    args[i] = "f_" + best.getParameters()[i].getName();
                } else {
                    args[i] = fill(paramTypes[i], paramGenerics[i], depth + 1, nextPath);
                }
            } catch (Exception e) {
                return null;
            }
        }
        try {
            best.setAccessible(true);
            return best.newInstance(args);
        } catch (Exception e) {
            return null;
        }
    }

    /** 取第 index 个类型参数（须为 Class 或 ParameterizedType）；index=-1 表示第一个可用位置。 */
    private java.lang.reflect.Type typeArgument(java.lang.reflect.Type generic, int index) {
        if (generic instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.Type[] args = ((java.lang.reflect.ParameterizedType) generic).getActualTypeArguments();
            for (int i = 0; i < args.length; i++) {
                if ((args[i] instanceof Class || args[i] instanceof java.lang.reflect.ParameterizedType)
                        && (index < 0 || i == index)) {
                    return args[i];
                }
            }
        }
        return null;
    }

    private static Class<?> rawClass(java.lang.reflect.Type type) {
        if (type instanceof Class) {
            return (Class<?>) type;
        }
        if (type instanceof java.lang.reflect.ParameterizedType) {
            java.lang.reflect.Type raw = ((java.lang.reflect.ParameterizedType) type).getRawType();
            return raw instanceof Class ? (Class<?>) raw : null;
        }
        return null;
    }

    private void recordScenarios(Map<String, List<Map<String, Object>>> byClient) throws Exception {
        // 场景 B：access_token 失效（42001）→ 期望 X, gettoken, X，且重试后 token 变化
        Class<?> weChatClient = Class.forName("com.github.shuaidd.client.WeChatClient");
        Object weChatBean = context.getBean(weChatClient);
        Method apiDomainIp = findMethod(weChatClient, "apiDomainIp", 1);
        Invocation retry = prepare(weChatClient, apiDomainIp, weChatBean);
        Invocation retryScenario = new Invocation(retry.id + "#token-expired", retry.method, retry.bean,
                retry.args, retry.params, false, "token-expired");
        retryCallId = retryScenario.id;
        RETRY_FIRED.set(0);
        Map<String, Object> retryEntry = invoke("WeChatClient", retryScenario);
        retryCallId = null;
        byClient.computeIfAbsent("WeChatClient", k -> new ArrayList<>()).add(retryEntry);

        // 场景 C：下载响应带 error-code → WeChatException，不重试
        Class<?> mediaClient = Class.forName("com.github.shuaidd.client.WeChatMediaClient");
        Object mediaBean = context.getBean(mediaClient);
        Method download = findMethod(mediaClient, "download", 2);
        Invocation error = prepare(mediaClient, download, mediaBean);
        Invocation errorScenario = new Invocation(error.id + "#download-error", error.method, error.bean,
                error.args, error.params, true, "download-error");
        downloadErrorCallId = errorScenario.id;
        Map<String, Object> errorEntry = invoke("WeChatMediaClient", errorScenario);
        downloadErrorCallId = null;
        byClient.computeIfAbsent("WeChatMediaClient", k -> new ArrayList<>()).add(errorEntry);

        // 场景 D：debug-mode=true → query 追加 debug=1
        Method callBackIp = findMethod(weChatClient, "callBackIp", 1);
        Invocation debug = prepare(weChatClient, callBackIp, weChatBean);
        Invocation debugScenario = new Invocation(debug.id + "#debug-mode", debug.method, debug.bean,
                debug.args, debug.params, false, "debug-mode");
        weChatManager.properties().setDebugMode(true);
        try {
            byClient.computeIfAbsent("WeChatClient", k -> new ArrayList<>()).add(invoke("WeChatClient", debugScenario));
        } finally {
            weChatManager.properties().setDebugMode(false);
        }
    }

    private static Method findMethod(Class<?> type, String name, int parameterCount) {
        for (Method method : type.getDeclaredMethods()) {
            if (method.getName().equals(name) && method.getParameterCount() == parameterCount) {
                return method;
            }
        }
        throw new IllegalStateException("method not found: " + name + "/" + parameterCount);
    }

    private void write(Map<String, List<Map<String, Object>>> byClient, int expected) throws IOException {
        Path dir = Paths.get(System.getProperty("contract.output.dir",
                "../qywx-core/src/test/resources/contract/5.2.0"));
        Files.createDirectories(dir);
        int total = 0;
        int thrown = 0;
        for (Map.Entry<String, List<Map<String, Object>>> e : byClient.entrySet()) {
            total += e.getValue().size();
            for (Map<String, Object> entry : e.getValue()) {
                if ("throw".equals(((Map<String, Object>) entry.get("result")).get("kind"))) {
                    thrown++;
                }
            }
            Files.write(dir.resolve(e.getKey() + ".json"), mapper.writeValueAsBytes(e.getValue()));
        }
        Map<String, Object> manifest = new LinkedHashMap<>();
        manifest.put("version", "5.2.0");
        manifest.put("mappedMethods", expected);
        manifest.put("entries", total);
        manifest.put("thrownEntries", thrown);
        manifest.put("clients", new ArrayList<>(byClient.keySet()));
        Files.write(dir.resolve("manifest.json"), mapper.writeValueAsBytes(manifest));
        LOG.info("契约录制完成：client={}，映射方法={}，条目={}（抛异常条目 {}），输出目录={}",
                byClient.size(), expected, total, thrown, dir.toAbsolutePath());
    }

    private static HttpServer startServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
            server.setExecutor(Executors.newCachedThreadPool());
            server.createContext("/", ContractRecordTest::handle);
            server.start();
            return server;
        } catch (IOException e) {
            throw new IllegalStateException("无法启动契约录制伪服务器", e);
        }
    }

    private static void handle(HttpExchange exchange) throws IOException {
        try {
            RequestRecord record = new RequestRecord();
            record.method = exchange.getRequestMethod();
            record.path = exchange.getRequestURI().getPath();
            record.query = exchange.getRequestURI().getRawQuery();
            record.app = exchange.getRequestHeaders().getFirst("app");
            record.contentType = exchange.getRequestHeaders().getFirst("Content-Type");
            record.downloadFilePath = exchange.getRequestHeaders().getFirst("downloadFilePath");
            record.body = exchange.getRequestBody().readAllBytes();
            REQUESTS.add(record);

            CallContext context = current;
            byte[] out;
            String contentType;
            if (record.path.endsWith("/gettoken")) {
                out = ("{\"errcode\":0,\"errmsg\":\"ok\",\"access_token\":\"" + TOKEN_PREFIX
                        + TOKEN_SEQ.incrementAndGet() + "\",\"expires_in\":7200}").getBytes(StandardCharsets.UTF_8);
                contentType = "application/json; charset=utf-8";
            } else if (context != null && context.download) {
                if (context.id.equals(downloadErrorCallId)) {
                    exchange.getResponseHeaders().set("error-code", "40007");
                    exchange.getResponseHeaders().set("error-msg", "invalid media id");
                }
                exchange.getResponseHeaders().set("Content-Disposition",
                        "attachment; filename=\"" + FILE_NAME + "\"; filename*=UTF-8''" + FILE_NAME);
                out = DOWNLOAD_BYTES;
                contentType = "application/octet-stream";
            } else if (context != null && context.id.equals(retryCallId) && RETRY_FIRED.getAndIncrement() == 0) {
                out = ("{\"errcode\":42001,\"errmsg\":\"invalid credential, access_token is invalid or not latest\"}")
                        .getBytes(StandardCharsets.UTF_8);
                contentType = "application/json; charset=utf-8";
            } else {
                out = "{\"errcode\":0,\"errmsg\":\"ok\"}".getBytes(StandardCharsets.UTF_8);
                contentType = "application/json; charset=utf-8";
            }
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, out.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(out);
            }
        } finally {
            exchange.close();
        }
    }

    @AfterAll
    static void cleanUp() throws IOException {
        SERVER.stop(0);
        Path tmp = Paths.get(System.getProperty("java.io.tmpdir"));
        List<Path> leftovers = new ArrayList<>();
        try (java.util.stream.Stream<Path> files = Files.list(tmp)) {
            leftovers.addAll(files.filter(p -> p.getFileName().toString().endsWith(FILE_NAME))
                    .collect(Collectors.toList()));
        }
        for (Path file : leftovers) {
            try {
                Files.deleteIfExists(file);
            } catch (IOException e) {
                // 5.2.0 的 WeChatMediaDecoder 未关闭 FileOutputStream，Windows 下文件在 JVM 退出前保持锁定；
                // 这是迁移时要修复的缺陷，此处只告警，不让清理失败掩盖录制结果。
                LOG.warn("临时下载文件仍被占用，留给系统清理: {}", file);
            }
        }
    }

    private static final class CallContext {
        private final String id;
        private final boolean download;

        private CallContext(String id, boolean download) {
            this.id = id;
            this.download = download;
        }
    }

    private static final class Invocation {
        private final String id;
        private final Method method;
        private final Object bean;
        private final Object[] args;
        private final List<Map<String, Object>> params;
        private final boolean download;
        private final String scenario;

        private Invocation(String id, Method method, Object bean, Object[] args,
                           List<Map<String, Object>> params, boolean download, String scenario) {
            this.id = id;
            this.method = method;
            this.bean = bean;
            this.args = args;
            this.params = params;
            this.download = download;
            this.scenario = scenario;
        }
    }

    private static final class RequestRecord {
        private String method;
        private String path;
        private String query;
        private String app;
        private String contentType;
        private String downloadFilePath;
        private byte[] body;

        private Map<String, Object> toMap(Map<String, Integer> tokens) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("method", method);
            map.put("path", path);
            map.put("query", normalizeQuery(query, tokens));
            Map<String, String> headers = new LinkedHashMap<>();
            if (app != null) {
                headers.put("app", app);
            }
            if (contentType != null) {
                headers.put("content-type", normalizeContentType(contentType));
            }
            if (downloadFilePath != null) {
                headers.put("downloadFilePath", "{{TMPDIR}}");
            }
            map.put("headers", headers);
            map.put("body", normalizeBody(body, contentType));
            return map;
        }
    }
}