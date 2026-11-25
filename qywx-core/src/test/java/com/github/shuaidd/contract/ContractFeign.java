package com.github.shuaidd.contract;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.shuaidd.client.QywxContract;
import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.client.decoder.CustomJacksonDecoder;
import com.github.shuaidd.client.encoder.JacksonJsonEncoder;
import com.github.shuaidd.client.encoder.MultipartEncoder;
import com.github.shuaidd.client.interceptor.WeChatInterceptor;
import com.github.shuaidd.client.support.TokenRetryInvocationHandlerFactory;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.token.InMemoryTokenStore;
import com.github.shuaidd.token.TokenEntry;
import com.github.shuaidd.token.TokenManager;
import com.github.shuaidd.token.TokenStore;
import feign.Feign;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 描述 契约回放的装配辅助：按 core 的装配方式（QywxContract、multipart 包 JSON encoder、自定义 decoder、令牌拦截器、重试 handler）
 * 组装 Feign 客户端，并按契约条目反射发起调用
 * <p>
 * 契约快照里的参数（含文件内容）一律在运行时从快照读取，测试代码不写死。
 *
 * @author ddshuai
 * date 2026-11-26 00:30
 **/
public final class ContractFeign {

    public static final String CORP_ID = "corp-contract";

    public static final String SECRET = "secret-contract";

    public static final String APP = "contract";

    private ContractFeign() {
    }

    /**
     * 与快照录制时一致的配置：baseUrl 恰为 {@link RecordingClient#TARGET_URL}
     *
     * @param debugMode 是否调试模式
     * @return 配置
     */
    public static QywxConfig config(boolean debugMode) {
        ApplicationProperties application = new ApplicationProperties();
        application.setApplicationName(APP);
        application.setSecret(SECRET);
        return QywxConfig.builder()
                .corpId(CORP_ID)
                .url("http://contract.invalid")
                .debugMode(debugMode)
                .application(application)
                .build();
    }

    public static TokenManager tokenManager(CountingFetcher fetcher, QywxConfig config) {
        return tokenManager(fetcher, config, new InMemoryTokenStore());
    }

    public static TokenManager tokenManager(TokenManager.TokenFetcher fetcher, QywxConfig config, TokenStore store) {
        return new TokenManager(config, store, fetcher);
    }

    /**
     * 业务 client 的完整装配（默认缩进 JSON）
     */
    public static <T> T create(Class<T> type, QywxConfig config, TokenManager manager, RecordingClient recording) {
        return create(type, JacksonJsonEncoder.indented(), config, manager, recording);
    }

    /**
     * 带 token 拦截器的装配（decoder 为默认的 JSON decoder）
     */
    public static <T> T create(Class<T> type, Encoder encoder, QywxConfig config, TokenManager manager,
                               RecordingClient recording) {
        return create(type, new CustomJacksonDecoder(), encoder, config, manager, recording);
    }

    /**
     * 带 token 拦截器、指定 decoder 的装配（下载场景传 {@code WeChatMediaDecoder}）
     */
    public static <T> T create(Class<T> type, Decoder decoder, Encoder encoder, QywxConfig config, TokenManager manager,
                               RecordingClient recording) {
        return builder(config, recording)
                .decoder(decoder)
                .encoder(new MultipartEncoder(encoder))
                .requestInterceptor(new WeChatInterceptor(manager, config))
                .invocationHandlerFactory(new TokenRetryInvocationHandlerFactory(manager, config))
                .target(type, RecordingClient.TARGET_URL);
    }

    /**
     * 不带 token 拦截器：{@code ServiceProviderClient} 的接口不传应用名
     */
    public static <T> T createWithoutInterceptor(Class<T> type, Encoder encoder, QywxConfig config,
                                                 TokenManager manager, RecordingClient recording) {
        return builder(config, recording)
                .encoder(new MultipartEncoder(encoder))
                .invocationHandlerFactory(new TokenRetryInvocationHandlerFactory(manager, config))
                .target(type, RecordingClient.TARGET_URL);
    }

    /**
     * 只装配假 HTTP client 与 decoder，其余由调用方决定
     */
    public static Feign.Builder builder(QywxConfig config, RecordingClient recording) {
        assertEquals(RecordingClient.TARGET_URL, config.baseUrl(), "契约回放要求 baseUrl 恰为 RecordingClient.TARGET_URL");
        return Feign.builder()
                .contract(new QywxContract())
                .client(recording)
                .decoder(new CustomJacksonDecoder())
                .retryer(Retryer.NEVER_RETRY);
    }

    /**
     * 按契约条目的方法名与参数发起调用
     *
     * @param api   Feign 代理
     * @param entry 契约条目
     * @return 调用结果
     */
    public static Object call(Object api, JsonNode entry) {
        return invoke(api, entry, ContractSnapshot.args(entry));
    }

    /**
     * 按契约条目的方法名发起调用，并把指定下标的参数换成调用方给的值（快照里的值在回放时不适用时使用，
     * 例如 5.2.0 录制的 downloadFilePath 是录制时的临时目录）
     *
     * @param api      Feign 代理
     * @param entry    契约条目
     * @param index    参数下标
     * @param argument 替换后的实参
     * @return 调用结果
     */
    public static Object call(Object api, JsonNode entry, int index, Object argument) {
        Object[] args = ContractSnapshot.args(entry);
        assertTrue(index >= 0 && index < args.length, "参数下标超出契约条目的参数个数：" + index + "/" + args.length);
        args[index] = argument;
        return invoke(api, entry, args);
    }

    private static Object invoke(Object api, JsonNode entry, Object[] args) {
        String id = entry.path("id").asText();
        Method method = findMethod(api.getClass(), entry.path("method").asText(), args.length);
        try {
            return method.invoke(api, args);
        } catch (InvocationTargetException e) {
            throw new AssertionError("契约调用抛出异常：" + id, e.getCause());
        } catch (ReflectiveOperationException e) {
            throw new AssertionError("反射调用契约方法失败：" + id, e);
        }
    }

    private static Method findMethod(Class<?> type, String name, int parameterCount) {
        for (Method method : type.getMethods()) {
            if (method.getName().equals(name) && method.getParameterCount() == parameterCount) {
                return method;
            }
        }
        throw new AssertionError("找不到契约方法：" + name + "/" + parameterCount);
    }

    /**
     * 沿 cause 链查找指定类型的异常（最多 20 层），找不到返回 null
     *
     * @param throwable 异常
     * @param type      目标类型
     * @param <T>       目标类型
     * @return 目标异常
     */
    public static <T extends Throwable> T findCause(Throwable throwable, Class<T> type) {
        Throwable current = throwable;
        for (int depth = 0; current != null && depth < 20; depth++) {
            if (type.isInstance(current)) {
                return type.cast(current);
            }
            current = current.getCause();
        }
        return null;
    }

    /**
     * 每次取号返回 tok-1、tok-2…，并记录签发的令牌与取号次数
     */
    public static final class CountingFetcher implements TokenManager.TokenFetcher {

        private final List<String> issued = Collections.synchronizedList(new ArrayList<String>());

        @Override
        public AccessTokenResponse fetch(String corpId, String secret) {
            String accessToken;
            synchronized (issued) {
                accessToken = "tok-" + (issued.size() + 1);
                issued.add(accessToken);
            }
            return token(accessToken);
        }

        public int calls() {
            synchronized (issued) {
                return issued.size();
            }
        }

        /**
         * 按签发顺序返回令牌
         *
         * @return 令牌列表
         */
        public List<String> issued() {
            synchronized (issued) {
                return Collections.unmodifiableList(new ArrayList<String>(issued));
            }
        }
    }

    /**
     * 构造 gettoken 的响应体
     *
     * @param accessToken 令牌
     * @return 响应
     */
    public static AccessTokenResponse token(String accessToken) {
        AccessTokenResponse response = new AccessTokenResponse();
        response.setAccessToken(accessToken);
        response.setExpiresIn(7200);
        return response;
    }

    /**
     * 记录写入与清除应用名的内存缓存
     */
    public static final class RecordingStore extends InMemoryTokenStore {

        private final List<String> puts = new ArrayList<>();

        private final List<String> evictions = new ArrayList<>();

        @Override
        public void put(String applicationName, TokenEntry entry) {
            puts.add(applicationName);
            super.put(applicationName, entry);
        }

        @Override
        public void evict(String applicationName) {
            evictions.add(applicationName);
            super.evict(applicationName);
        }

        public List<String> puts() {
            return Collections.unmodifiableList(puts);
        }

        public List<String> evictions() {
            return Collections.unmodifiableList(evictions);
        }
    }
}
