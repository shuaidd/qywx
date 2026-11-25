package com.github.shuaidd.client.support;

import com.github.shuaidd.client.CommonClient;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.enums.ErrorCode;
import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.token.TokenManager;
import feign.InvocationHandlerFactory;
import feign.InvocationHandlerFactory.MethodHandler;
import feign.Param;
import feign.Target;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述 令牌失效自动重试：包装 Feign 默认的 {@link InvocationHandlerFactory}，替代 5.x 中依赖 Spring AOP 的 {@code WeChatClientAspect}
 * <p>
 * 规则与 5.x 一致：调用抛出的异常（沿 cause 链）是 {@link WeChatException}，且错误码为
 * 40014 / 41001 / 42001 时，清除该应用的 access_token 缓存后重试一次；其余异常原样抛出，重试仍失败也原样抛出。
 * 与 5.x 的差异：应用名取自 {@code @Param("app")} 标注的参数，而不是「最后一个参数」；没有该参数的方法不重试。
 * 错误码以响应原始码为准：{@code WeChatException.unknown} 携带 42001 等码时同样重试（5.x 的切面只认枚举码，不重试）。
 *
 * @author ddshuai
 * date 2026-11-26 00:03
 **/
public class TokenRetryInvocationHandlerFactory implements InvocationHandlerFactory {

    private static final Logger logger = LoggerFactory.getLogger(TokenRetryInvocationHandlerFactory.class);

    /** 触发重试的错误码：40014 不合法的 access_token、41001 缺少 access_token 参数、42001 access_token 已过期 */
    private static final Set<String> RETRY_CODES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
            ErrorCode.ERROR_CODE_40014.getErrorCode(),
            ErrorCode.ERROR_CODE_41001.getErrorCode(),
            ErrorCode.ERROR_CODE_42001.getErrorCode())));

    /** 沿 cause 链查找 {@link WeChatException} 的最大层数，避免 cause 自引用导致死循环 */
    private static final int MAX_CAUSE_DEPTH = 10;

    private final InvocationHandlerFactory delegate = new InvocationHandlerFactory.Default();

    private final TokenManager tokenManager;

    private final QywxConfig config;

    /** 方法到 {@code @Param("app")} 参数下标的缓存，-1 表示方法没有应用名参数 */
    private final ConcurrentHashMap<Method, Integer> appParamIndexes = new ConcurrentHashMap<>();

    public TokenRetryInvocationHandlerFactory(TokenManager tokenManager, QywxConfig config) {
        this.tokenManager = Objects.requireNonNull(tokenManager, "tokenManager");
        this.config = Objects.requireNonNull(config, "config");
    }

    /**
     * 用带重试的 handler 包装 Feign 默认的 handler
     *
     * @param target   调用目标
     * @param dispatch 方法到调用器的映射
     * @return InvocationHandler
     */
    @Override
    public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
        return new RetryInvocationHandler(delegate.create(target, dispatch));
    }

    /**
     * 代理的实际调用入口：先按默认流程调用，令牌失效时重试一次
     */
    private final class RetryInvocationHandler implements InvocationHandler {

        private final InvocationHandler handler;

        private RetryInvocationHandler(InvocationHandler handler) {
            this.handler = handler;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return invokeObjectMethod(proxy, method, args);
            }

            boolean logTiming = Boolean.TRUE.equals(config.getDebugMode()) && logger.isDebugEnabled();
            long start = logTiming ? System.nanoTime() : 0L;
            try {
                return handler.invoke(proxy, method, args);
            } catch (Throwable throwable) {
                return retryIfNeeded(proxy, method, args, throwable);
            } finally {
                if (logTiming) {
                    logger.debug("调用企微接口耗时--{}秒", (System.nanoTime() - start) / 1_000_000_000.0);
                }
            }
        }

        /**
         * equals / hashCode / toString 交给被包装的 handler，语义与 Feign 默认代理保持一致
         */
        private Object invokeObjectMethod(Object proxy, Method method, Object[] args) {
            String name = method.getName();
            if ("equals".equals(name)) {
                Object other = args[0];
                if (other == proxy) {
                    return Boolean.TRUE;
                }
                if (other == null || !Proxy.isProxyClass(other.getClass())) {
                    return Boolean.FALSE;
                }
                InvocationHandler otherHandler = Proxy.getInvocationHandler(other);
                if (otherHandler instanceof RetryInvocationHandler) {
                    //对方同样是本类包装的代理：比较各自内部的 handler
                    return handler.equals(((RetryInvocationHandler) otherHandler).handler);
                }
                //普通 Feign 代理不认识本类的包装 handler，对方那一侧的 equals 必然为 false；
                //这里若按内层 handler 比较并返回 true，会破坏 equals 的对称性，因此统一按不相等处理
                return Boolean.FALSE;
            }
            if ("hashCode".equals(name)) {
                return handler.hashCode();
            }
            return handler.toString();
        }

        /**
         * 令牌失效且方法声明了应用名参数时重试一次，其余情况原样抛出
         */
        private Object retryIfNeeded(Object proxy, Method method, Object[] args, Throwable throwable) throws Throwable {
            WeChatException weChatException = findWeChatException(throwable);
            if (weChatException == null || !needRetry(weChatException)) {
                throw throwable;
            }

            int appIndex = appParamIndexOf(method);
            if (appIndex < 0) {
                //没有应用名参数就不知道该清除哪个应用的令牌，不重试
                throw throwable;
            }

            if (logger.isDebugEnabled()) {
                logger.debug("重试一次微信调用：{}", codeOf(weChatException));
            }

            Object applicationName = args == null || appIndex >= args.length ? null : args[appIndex];
            if (applicationName != null) {
                //清除掉缓存的accessToken 重新获取新的access_token
                tokenManager.evict(applicationName.toString());
            }

            //只重试一次；仍未成功时原样抛出，由调用方处理
            return handler.invoke(proxy, method, args);
        }

        /**
         * 沿 cause 链查找 WeChatException（Feign 会把 decoder 抛出的异常包成 DecodeException）
         */
        private static WeChatException findWeChatException(Throwable throwable) {
            Throwable current = throwable;
            for (int depth = 0; current != null && depth < MAX_CAUSE_DEPTH; depth++) {
                if (current instanceof WeChatException) {
                    return (WeChatException) current;
                }
                current = current.getCause();
            }
            return null;
        }

        private static boolean needRetry(WeChatException exception) {
            String code = codeOf(exception);
            return code != null && RETRY_CODES.contains(code);
        }

        /**
         * 错误码以响应里的原始码为准：枚举码缺失或为 UNKNOWN（枚举里没有这个码）时取原始码
         */
        private static String codeOf(WeChatException exception) {
            ErrorCode errorCode = exception.getErrorCode();
            if (errorCode == null || errorCode == ErrorCode.UNKNOWN) {
                return exception.getCode();
            }
            return errorCode.getErrorCode();
        }

        private int appParamIndexOf(Method method) {
            return appParamIndexes.computeIfAbsent(method, TokenRetryInvocationHandlerFactory::findAppParamIndex);
        }
    }

    /**
     * 查找标注了 {@code @Param("app")} 的参数下标
     *
     * @param method client 方法
     * @return 参数下标，没有则为 -1
     */
    private static int findAppParamIndex(Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            for (Annotation annotation : parameterAnnotations[i]) {
                if (annotation instanceof Param && CommonClient.HEAD_KEY.equals(((Param) annotation).value())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
