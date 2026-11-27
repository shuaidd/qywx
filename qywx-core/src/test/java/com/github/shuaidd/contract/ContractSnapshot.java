package com.github.shuaidd.contract;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.shuaidd.file.QywxFile;
import com.github.shuaidd.json.Date2LongSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * 描述 读取 5.2.0 契约快照，并把参数与请求转换成可回放的断言
 *
 * @author ddshuai
 * date 2026-11-26 00:30
 **/
public final class ContractSnapshot {

    private static final ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final ConcurrentHashMap<String, JsonNode> CLIENTS = new ConcurrentHashMap<>();

    private static final Pattern TOKEN = Pattern.compile("access_token=tok-(\\d+)");

    private static final Pattern BOUNDARY = Pattern.compile("boundary=([^;]+)");

    /** 编译期未带 -parameters 时构造参数名形如 arg0，不能据此匹配快照里的属性名 */
    private static final Pattern ARG_NAME = Pattern.compile("arg\\d+");

    private static final Map<String, Class<?>> PRIMITIVES = new LinkedHashMap<>();

    /** 按类型缓存的序列化属性，避免逐条契约重复内省 */
    private static final ConcurrentHashMap<JavaType, List<BeanPropertyDefinition>> PROPERTIES =
            new ConcurrentHashMap<>();

    static {
        PRIMITIVES.put("boolean", boolean.class);
        PRIMITIVES.put("byte", byte.class);
        PRIMITIVES.put("char", char.class);
        PRIMITIVES.put("short", short.class);
        PRIMITIVES.put("int", int.class);
        PRIMITIVES.put("long", long.class);
        PRIMITIVES.put("float", float.class);
        PRIMITIVES.put("double", double.class);
    }

    private ContractSnapshot() {
    }

    /**
     * 取完整 id（Client.method 或 Client.method#scenario）对应的契约条目；条目自身的 id 也是完整形式
     */
    public static JsonNode entry(String id) {
        int dot = id.indexOf('.');
        assertTrue(dot > 0, "契约 id 必须包含 client 与 method：" + id);
        String client = id.substring(0, dot);
        JsonNode entries = CLIENTS.computeIfAbsent(client, ContractSnapshot::load);
        for (JsonNode entry : entries) {
            if (id.equals(entry.path("id").asText())) {
                return entry;
            }
        }
        throw new AssertionError("找不到契约条目：" + id);
    }

    /**
     * 取指定 client 的全部契约条目（不可变，按快照顺序）；P1.6 的参数化回放与 P2.7 的全量回放共用
     */
    public static List<JsonNode> entries(String client) {
        List<JsonNode> entries = new ArrayList<>();
        for (JsonNode entry : CLIENTS.computeIfAbsent(client, ContractSnapshot::load)) {
            entries.add(entry);
        }
        return Collections.unmodifiableList(entries);
    }

    /**
     * 按快照中的参数顺序构造调用参数
     */
    public static Object[] args(JsonNode entry) {
        JsonNode params = entry.get("params");
        Object[] args = new Object[params == null || params.isNull() ? 0 : params.size()];
        if (params == null || params.isNull()) {
            return args;
        }
        for (int i = 0; i < params.size(); i++) {
            args[i] = argument(params.get(i));
        }
        return args;
    }

    /**
     * 比对契约条目与回放客户端记录的请求；offset 用于跳过预热阶段的请求
     */
    public static void assertMatches(JsonNode entry, RecordingClient recording) {
        assertMatches(entry, recording, 0);
    }

    public static void assertMatches(JsonNode entry, RecordingClient recording, int offset) {
        JsonNode expectedRequests = entry.get("requests");
        int expectedCount = expectedRequests == null || expectedRequests.isNull() ? 0 : expectedRequests.size();
        assertEquals(expectedCount, recording.requestCount() - offset, "请求数量");
        if (expectedCount == 0) {
            return;
        }
        for (int i = 0; i < expectedCount; i++) {
            assertRequest(expectedRequests.get(i), recording.request(offset + i), "第 " + i + " 个请求：");
        }
    }

    /**
     * 5.2.0 会把 downloadFilePath 作为 query 与请求头发出；core 使用 {@code DownloadContext} 带外传递，
     * 因此回放下载落盘条目时只去掉这两处历史行为后再比对。
     */
    public static void assertMatchesWithoutDownloadFilePath(JsonNode entry, RecordingClient recording, int offset) {
        ObjectNode expected = entry.deepCopy();
        ObjectNode request = (ObjectNode) expected.path("requests").path(0);
        request.put("query", request.path("query").asText().replaceFirst("downloadFilePath=[^&]*&", ""));
        ((ObjectNode) request.path("headers")).remove("downloadFilePath");
        assertMatches(expected, recording, offset);
    }

    /**
     * 把 Feign 请求头转成小写键、逗号连接值的 Map
     */
    public static Map<String, String> headerValues(Map<String, Collection<String>> headers) {
        Map<String, String> values = new LinkedHashMap<>();
        if (headers == null) {
            return values;
        }
        for (Map.Entry<String, Collection<String>> header : headers.entrySet()) {
            if (header.getKey() == null || header.getValue() == null) {
                continue;
            }
            StringBuilder joined = new StringBuilder();
            for (String value : header.getValue()) {
                if (joined.length() > 0) {
                    joined.append(',');
                }
                joined.append(value);
            }
            values.put(header.getKey().toLowerCase(java.util.Locale.ROOT), joined.toString());
        }
        return values;
    }

    private static JsonNode load(String client) {
        String resource = "/contract/5.2.0/" + client + ".json";
        try (InputStream in = ContractSnapshot.class.getResourceAsStream(resource)) {
            assertNotNull(in, "找不到契约快照：" + resource);
            return MAPPER.readTree(in);
        } catch (IOException e) {
            throw new AssertionError("读取契约快照失败：" + resource, e);
        }
    }

    /**
     * 取 multipart 请求头里的 boundary；非 multipart 请求返回 null
     */
    private static String multipartBoundary(String contentType, String message) {
        if (contentType == null || !contentType.startsWith("multipart/form-data")) {
            return null;
        }
        Matcher matcher = BOUNDARY.matcher(contentType);
        assertTrue(matcher.find(), message + "multipart Content-Type 缺少 boundary");
        return matcher.group(1);
    }

    /**
     * 按快照中的参数类型与录制值重建调用参数。录制值是 5.2.0 录制器 {@code valueToTree} 得到的线缆形态，
     * 这里按录制器 {@code fill()} 的逆过程反射还原，不经 Jackson 反序列化模型类——模型里保留着
     * 无无参构造器、{@code Long} 字段误挂日期反序列化器等 5.2.0 时期的历史形态，直接反序列化会失败或走样
     */
    private static Object argument(JsonNode param) {
        String kind = param.path("kind").asText();
        if ("file".equals(kind)) {
            byte[] content = Base64.getDecoder().decode(param.get("contentBase64").asText());
            return new QywxFile(content, param.get("filename").asText(), param.get("contentType").asText());
        }
        JsonNode value = param.get("value");
        if (value == null || value.isNull()) {
            return null;
        }
        String type = param.get("type").asText();
        try {
            return rebuild(javaType(type), value, false, false);
        } catch (Exception e) {
            throw new AssertionError("构造契约参数失败：" + type, e);
        }
    }

    /**
     * 把快照里的类型名还原成 JavaType：原始类型按名称映射，普通类按类名加载，泛型与数组交给 Jackson 的规范名解析
     */
    private static JavaType javaType(String type) throws ClassNotFoundException {
        Class<?> primitive = PRIMITIVES.get(type);
        if (primitive != null) {
            return MAPPER.getTypeFactory().constructType(primitive);
        }
        try {
            return MAPPER.getTypeFactory().constructType(Class.forName(type, false, ContractSnapshot.class.getClassLoader()));
        } catch (ClassNotFoundException e) {
            try {
                return MAPPER.getTypeFactory().constructFromCanonical(type);
            } catch (IllegalArgumentException notCanonical) {
                throw e;
            }
        }
    }

    /**
     * 逆着录制器 {@code fill()} 重建一个值。{@code seconds} 表示该位置的日期按秒记录
     * （{@code Date2LongSerializer}），{@code contentSeconds} 表示容器元素按秒记录；原始类型返回包装值，
     * 交由反射调用自动拆箱
     */
    private static Object rebuild(JavaType type, JsonNode node, boolean seconds, boolean contentSeconds) throws Exception {
        if (node == null || node.isNull()) {
            return null;
        }
        Class<?> raw = type.getRawClass();
        if (raw == String.class) {
            return node.asText();
        }
        if (raw == Object.class) {
            return MAPPER.treeToValue(node, Object.class);
        }
        if (raw == Integer.class || raw == int.class) {
            return node.intValue();
        }
        if (raw == Long.class || raw == long.class) {
            return node.longValue();
        }
        if (raw == Short.class || raw == short.class) {
            return node.shortValue();
        }
        if (raw == Byte.class || raw == byte.class) {
            return (byte) node.intValue();
        }
        if (raw == Float.class || raw == float.class) {
            return node.floatValue();
        }
        if (raw == Double.class || raw == double.class) {
            return node.doubleValue();
        }
        if (raw == Boolean.class || raw == boolean.class) {
            return node.booleanValue();
        }
        if (raw == Character.class || raw == char.class) {
            String text = node.asText();
            return text.isEmpty() ? '\0' : text.charAt(0);
        }
        if (raw == BigDecimal.class) {
            return node.decimalValue();
        }
        if (raw == BigInteger.class) {
            return node.bigIntegerValue();
        }
        if (Date.class.isAssignableFrom(raw)) {
            return date(raw, node, seconds);
        }
        if (raw.isEnum()) {
            try {
                return MAPPER.treeToValue(node, raw);
            } catch (Exception e) {
                // 录制器恒取第一个枚举常量，线缆形态只由 @JsonValue 决定，解析不出时取首常量等价
                return raw.getEnumConstants()[0];
            }
        }
        if (Map.class.isAssignableFrom(raw)) {
            JavaType keyType = type.containedTypeOrUnknown(0);
            JavaType valueType = type.containedTypeOrUnknown(1);
            Map<Object, Object> map = newMap(raw);
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                map.put(mapKey(keyType, field.getKey()), rebuild(valueType, field.getValue(), contentSeconds, false));
            }
            return map;
        }
        if (Collection.class.isAssignableFrom(raw)) {
            JavaType elementType = type.containedTypeOrUnknown(0);
            Collection<Object> collection = newCollection(raw);
            for (JsonNode element : node) {
                collection.add(rebuild(elementType, element, contentSeconds, false));
            }
            return collection;
        }
        if (raw.isArray()) {
            Class<?> component = raw.getComponentType();
            if (component == byte.class && node.isTextual()) {
                return MAPPER.treeToValue(node, byte[].class);
            }
            if (component == char.class && node.isTextual()) {
                return node.asText().toCharArray();
            }
            Object array = Array.newInstance(component, node.size());
            for (int i = 0; i < node.size(); i++) {
                Array.set(array, i, rebuild(type.getContentType(), node.get(i), contentSeconds, false));
            }
            return array;
        }
        if (!node.isObject()) {
            return MAPPER.treeToValue(node, type);
        }
        return populate(type, node);
    }

    /**
     * 录制器写入的是 {@code new Date(1700000000000L)}，经 {@code Date2LongSerializer} 记录成秒，这里换算回毫秒；
     * 文本形态的日期（带格式化配置时）交回 Jackson
     */
    private static Date date(Class<?> raw, JsonNode node, boolean seconds) throws Exception {
        if (!node.isNumber()) {
            return (Date) MAPPER.treeToValue(node, raw);
        }
        long millis = node.asLong() * (seconds ? 1000L : 1L);
        if (Timestamp.class.isAssignableFrom(raw)) {
            return new Timestamp(millis);
        }
        if (java.sql.Date.class.isAssignableFrom(raw)) {
            return new java.sql.Date(millis);
        }
        return new Date(millis);
    }

    /**
     * Map 键：快照里恒为字符串，非 String 键类型按 Jackson 规则转换，转不了时原样保留
     */
    private static Object mapKey(JavaType keyType, String key) throws Exception {
        Class<?> raw = keyType.getRawClass();
        if (raw == String.class || raw == Object.class) {
            return key;
        }
        try {
            return MAPPER.treeToValue(TextNode.valueOf(key), keyType);
        } catch (Exception e) {
            return key;
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<Object, Object> newMap(Class<?> raw) throws Exception {
        if (raw == Map.class || raw == AbstractMap.class) {
            return new LinkedHashMap<>();
        }
        if (SortedMap.class.isAssignableFrom(raw) || NavigableMap.class.isAssignableFrom(raw)) {
            return new TreeMap<>();
        }
        if (raw == ConcurrentMap.class) {
            return new ConcurrentHashMap<>();
        }
        return (Map<Object, Object>) instantiate0(raw);
    }

    @SuppressWarnings("unchecked")
    private static Collection<Object> newCollection(Class<?> raw) throws Exception {
        if (List.class.isAssignableFrom(raw) || raw == Collection.class || raw == Iterable.class) {
            return new ArrayList<>();
        }
        if (SortedSet.class.isAssignableFrom(raw) || NavigableSet.class.isAssignableFrom(raw)) {
            return new TreeSet<>();
        }
        if (Set.class.isAssignableFrom(raw)) {
            return new LinkedHashSet<>();
        }
        if (Deque.class.isAssignableFrom(raw) || Queue.class.isAssignableFrom(raw)) {
            return new ArrayDeque<>();
        }
        return (Collection<Object>) instantiate0(raw);
    }

    private static Object instantiate0(Class<?> raw) throws Exception {
        Constructor<?> constructor = raw.getDeclaredConstructor();
        constructor.setAccessible(true);
        return newInstance(constructor, new Object[0]);
    }

    /**
     * 建实例后按序列化属性逐字段赋值：属性名取 {@code getName()} 以兼容 {@code @JsonProperty} 改名，
     * 类型以属性声明为准；字段赋不了值时退回 setter，没有字段的属性只走 setter
     */
    private static Object populate(JavaType type, JsonNode node) throws Exception {
        Class<?> raw = type.getRawClass();
        List<BeanPropertyDefinition> properties = properties(type);
        Map<String, BeanPropertyDefinition> byField = new HashMap<>();
        for (BeanPropertyDefinition property : properties) {
            AnnotatedMember field = property.getField();
            byField.put(field == null ? property.getInternalName() : field.getName(), property);
        }
        Object instance = instantiate(raw, node, properties);
        for (Class<?> c = raw; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                BeanPropertyDefinition property = byField.get(field.getName());
                JavaType fieldType = MAPPER.getTypeFactory().constructType(field.getGenericType());
                JsonNode value = node.get(field.getName());
                boolean seconds = false;
                boolean contentSeconds = false;
                if (property != null) {
                    JsonNode named = node.get(property.getName());
                    if (named != null) {
                        value = named;
                    }
                    JavaType primary = property.getPrimaryType();
                    if (primary != null && primary.getRawClass() != Object.class) {
                        fieldType = primary;
                    }
                    seconds = usesSeconds(property, false);
                    contentSeconds = usesSeconds(property, true);
                }
                if (value == null) {
                    continue;
                }
                Object rebuilt = rebuild(fieldType, value, seconds, contentSeconds);
                try {
                    field.setAccessible(true);
                    field.set(instance, rebuilt);
                } catch (Exception e) {
                    // 与录制器一致：赋不了值的字段保持默认值，再试一次 setter
                    setBySetter(raw, property == null ? field.getName() : property.getInternalName(), instance, rebuilt);
                }
            }
        }
        for (BeanPropertyDefinition property : properties) {
            if (property.getField() != null) {
                continue;
            }
            JsonNode value = node.get(property.getName());
            if (value == null) {
                continue;
            }
            JavaType propertyType = property.getPrimaryType();
            Object rebuilt = rebuild(propertyType == null ? MAPPER.getTypeFactory().constructType(Object.class) : propertyType,
                    value, usesSeconds(property, false), usesSeconds(property, true));
            setBySetter(raw, property.getInternalName(), instance, rebuilt);
        }
        return instance;
    }

    /**
     * 按属性内部名拼 setXxx，在类层次中找单参方法调用；参数类型不符时同样视为赋不了值
     */
    private static void setBySetter(Class<?> raw, String internalName, Object instance, Object value) throws Exception {
        if (internalName == null || internalName.isEmpty()) {
            return;
        }
        String setterName = "set" + Character.toUpperCase(internalName.charAt(0)) + internalName.substring(1);
        for (Class<?> c = raw; c != null && c != Object.class; c = c.getSuperclass()) {
            for (Method method : c.getDeclaredMethods()) {
                if (setterName.equals(method.getName()) && method.getParameterCount() == 1) {
                    method.setAccessible(true);
                    try {
                        method.invoke(instance, value);
                    } catch (IllegalArgumentException e) {
                        // 录制器也赋不了值的字段保持默认值
                    }
                    return;
                }
            }
        }
    }

    private static List<BeanPropertyDefinition> properties(JavaType type) {
        return PROPERTIES.computeIfAbsent(type, key -> MAPPER.getSerializationConfig().introspect(key).findProperties());
    }

    /**
     * 字段、访问器、设置器任一处声明了 {@code @JsonSerialize(using/contentUsing = Date2LongSerializer)} 即按秒记录
     */
    private static boolean usesSeconds(BeanPropertyDefinition property, boolean content) {
        AnnotatedMember[] members = {property.getField(), property.getAccessor(), property.getMutator()};
        for (AnnotatedMember member : members) {
            if (member == null) {
                continue;
            }
            JsonSerialize serialize = member.getAnnotation(JsonSerialize.class);
            if (serialize != null && (content ? serialize.contentUsing() : serialize.using()) == Date2LongSerializer.class) {
                return true;
            }
        }
        return false;
    }

    /**
     * 实例化：优先无参构造器，否则 {@code @JsonCreator} 构造器，再否则参数最少的构造器（与录制器一致）
     */
    private static Object instantiate(Class<?> raw, JsonNode node, List<BeanPropertyDefinition> properties) throws Exception {
        Constructor<?> constructor = pickConstructor(raw, 0);
        if (constructor == null) {
            for (Constructor<?> candidate : raw.getDeclaredConstructors()) {
                if (candidate.isAnnotationPresent(JsonCreator.class)) {
                    constructor = candidate;
                    break;
                }
            }
        }
        if (constructor == null) {
            constructor = pickConstructor(raw, -1);
        }
        if (constructor == null) {
            throw new IllegalStateException("没有可用构造器：" + raw.getName());
        }
        constructor.setAccessible(true);
        return newInstance(constructor, constructorArgs(constructor, node, properties));
    }

    /** 取参数数量为 count 的构造器；count 小于 0 时取参数最少的构造器；没有则返回 null */
    private static Constructor<?> pickConstructor(Class<?> raw, int count) {
        Constructor<?> best = null;
        for (Constructor<?> candidate : raw.getDeclaredConstructors()) {
            if (count >= 0) {
                if (candidate.getParameterCount() == count) {
                    return candidate;
                }
                continue;
            }
            if (best == null || candidate.getParameterCount() < best.getParameterCount()) {
                best = candidate;
            }
        }
        return best;
    }

    /**
     * 构造参数取值：依次按 {@code @JsonProperty} 名、真实参数名、类型匹配尚未使用且快照有值的属性；
     * model 未带 -parameters，参数名形如 arg0，类型匹配的顺序与 Lombok 生成的构造器参数顺序一致
     */
    private static Object[] constructorArgs(Constructor<?> constructor, JsonNode node,
                                            List<BeanPropertyDefinition> properties) throws Exception {
        Parameter[] parameters = constructor.getParameters();
        Object[] args = new Object[parameters.length];
        Set<String> used = new HashSet<>();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            JavaType parameterType = MAPPER.getTypeFactory().constructType(parameter.getParameterizedType());
            JsonProperty annotation = parameter.getAnnotation(JsonProperty.class);
            String name = annotation == null ? parameter.getName() : annotation.value();
            BeanPropertyDefinition property = null;
            JsonNode value = null;
            if (name != null && !ARG_NAME.matcher(name).matches()) {
                value = node.get(name);
                if (value != null) {
                    used.add(name);
                }
            }
            if (value == null) {
                property = matchProperty(properties, used, node, parameterType);
                if (property != null) {
                    used.add(property.getName());
                    used.add(property.getInternalName());
                    value = node.get(property.getName());
                }
            }
            if (value == null || value.isNull()) {
                args[i] = placeholder(parameter, parameterType);
            } else {
                args[i] = rebuild(parameterType, value, property != null && usesSeconds(property, false),
                        property != null && usesSeconds(property, true));
            }
        }
        return args;
    }

    private static BeanPropertyDefinition matchProperty(List<BeanPropertyDefinition> properties, Set<String> used,
                                                        JsonNode node, JavaType parameterType) {
        Class<?> parameterRaw = wrapped(parameterType.getRawClass());
        for (BeanPropertyDefinition property : properties) {
            if (used.contains(property.getName()) || used.contains(property.getInternalName())
                    || !node.has(property.getName())) {
                continue;
            }
            JavaType propertyType = property.getPrimaryType();
            if (propertyType != null && parameterRaw.isAssignableFrom(wrapped(propertyType.getRawClass()))) {
                return property;
            }
        }
        return null;
    }

    /**
     * 快照里没有可用值时沿用录制器的占位值：String 用 "f_"+参数名，原始类型用录制器同款默认值
     */
    private static Object placeholder(Parameter parameter, JavaType parameterType) {
        Class<?> raw = parameterType.getRawClass();
        if (raw == String.class) {
            return "f_" + parameter.getName();
        }
        if (raw == int.class || raw == Integer.class) {
            return 7;
        }
        if (raw == long.class || raw == Long.class) {
            return 8L;
        }
        if (raw == boolean.class || raw == Boolean.class) {
            return Boolean.TRUE;
        }
        if (raw == double.class || raw == Double.class) {
            return 1.5d;
        }
        if (raw == float.class || raw == Float.class) {
            return 2.5f;
        }
        if (raw == short.class || raw == Short.class) {
            return (short) 3;
        }
        if (raw == byte.class || raw == Byte.class) {
            return (byte) 4;
        }
        if (raw == char.class || raw == Character.class) {
            return 'c';
        }
        return null;
    }

    private static Class<?> wrapped(Class<?> raw) {
        if (!raw.isPrimitive()) {
            return raw;
        }
        if (raw == int.class) {
            return Integer.class;
        }
        if (raw == long.class) {
            return Long.class;
        }
        if (raw == boolean.class) {
            return Boolean.class;
        }
        if (raw == double.class) {
            return Double.class;
        }
        if (raw == float.class) {
            return Float.class;
        }
        if (raw == short.class) {
            return Short.class;
        }
        if (raw == byte.class) {
            return Byte.class;
        }
        if (raw == char.class) {
            return Character.class;
        }
        return raw;
    }

    /**
     * 调用构造器，并把构造器内部抛出的异常原样抛出，避免包装后丢失真实原因
     */
    private static Object newInstance(Constructor<?> constructor, Object[] args) throws Exception {
        try {
            return constructor.newInstance(args);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Exception) {
                throw (Exception) cause;
            }
            throw e;
        }
    }

    private static void assertRequest(JsonNode expected, feign.Request actual, String message) {
        assertEquals(expected.path("method").asText(), actual.method(), message + "method");

        String relative = actual.url().substring(RecordingClient.TARGET_URL.length());
        int queryIndex = relative.indexOf('?');
        String actualPath = queryIndex < 0 ? relative : relative.substring(0, queryIndex);
        String actualQuery = queryIndex < 0 ? "" : relative.substring(queryIndex + 1);
        assertEquals(expected.path("path").asText(), RecordingClient.PUBLIC_PATH + actualPath, message + "path");

        Matcher matcher = TOKEN.matcher(actualQuery);
        StringBuffer normalized = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(normalized, "access_token={{TOKEN_" + matcher.group(1) + "}}");
        }
        matcher.appendTail(normalized);
        assertEquals(expected.path("query").asText(), normalized.toString(), message + "query");

        Map<String, String> expectedHeaders = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> fields = expected.path("headers").fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            expectedHeaders.put(field.getKey().toLowerCase(java.util.Locale.ROOT), field.getValue().asText());
        }
        Map<String, String> actualHeaders = headerValues(actual.headers());
        actualHeaders.remove("content-length");
        //boundary 是运行时随机值：先从原始请求头取出，再把请求头里的值换回占位符后与快照比对
        String boundary = multipartBoundary(actualHeaders.get("content-type"), message);
        String expectedContentType = expectedHeaders.get("content-type");
        if (expectedContentType != null && expectedContentType.contains("{{BOUNDARY}}")) {
            assertNotNull(boundary, message + "缺少 multipart Content-Type");
            actualHeaders.put("content-type", actualHeaders.get("content-type").replace(boundary, "{{BOUNDARY}}"));
        }
        assertEquals(expectedHeaders, actualHeaders, message + "headers");

        JsonNode expectedBody = expected.get("body");
        byte[] actualBody = actual.body();
        if (expectedBody == null || expectedBody.isNull() || expectedBody.asText().isEmpty()) {
            assertTrue(actualBody == null || actualBody.length == 0, message + "应无请求体");
            return;
        }
        assertNotNull(actualBody, message + "缺少请求体");
        String expectedText = expectedBody.asText();
        if (boundary != null) {
            expectedText = expectedText.replace("{{BOUNDARY}}", boundary);
            assertEquals(expectedText, new String(actualBody, StandardCharsets.ISO_8859_1), message + "body");
        } else {
            assertEquals(expectedText.replace("\r\n", "\n"),
                    new String(actualBody, StandardCharsets.UTF_8).replace("\r\n", "\n"), message + "body");
        }
    }
}
