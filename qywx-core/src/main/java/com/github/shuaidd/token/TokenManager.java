package com.github.shuaidd.token;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.config.QywxConfig;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 描述 访问令牌管理：按应用缓存 access_token，过期或在令牌失效后重新获取。
 * <p>
 * 替代 5.x 中 {@code TokenService} + Spring Cache + AOP 的组合：
 * <ul>
 * <li>缓存时间 = max(60, expires_in − 300) 秒（见 {@link TokenEntry}），不再"缓存到被清除为止"</li>
 * <li>同一应用同一时刻只允许一个线程调用 gettoken，其余线程等待后直接复用结果（防击穿）</li>
 * <li>令牌失效（40014/41001/42001）时由调用方 {@link #evict(String)} 后重试</li>
 * </ul>
 *
 * @author ddshuai
 * date 2026-11-25 22:39
 **/
public class TokenManager {

    private static final Logger logger = LoggerFactory.getLogger(TokenManager.class);

    private final QywxConfig config;

    private final TokenStore store;

    private final TokenFetcher fetcher;

    private final Clock clock;

    /**
     * 按应用名区分的取令牌锁；应用名须先通过配置校验，锁数量以配置的应用数为上限
     */
    private final ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    public TokenManager(QywxConfig config, TokenStore store, TokenFetcher fetcher) {
        this(config, store, fetcher, Clock.systemUTC());
    }

    TokenManager(QywxConfig config, TokenStore store, TokenFetcher fetcher, Clock clock) {
        this.config = Objects.requireNonNull(config, "config");
        this.store = Objects.requireNonNull(store, "store");
        this.fetcher = Objects.requireNonNull(fetcher, "fetcher");
        this.clock = Objects.requireNonNull(clock, "clock");
    }

    /**
     * 获取企业微信应用的 access token；缓存未过期时直接返回，否则调用 gettoken 并缓存
     *
     * @param applicationName 应用名称
     * @return String
     */
    public String getAccessToken(String applicationName) {
        ApplicationProperties application = requireApplication(applicationName);

        TokenEntry entry = store.get(applicationName);
        if (entry != null && !entry.isExpired(clock.millis())) {
            return entry.getAccessToken();
        }

        synchronized (lockFor(applicationName)) {
            // 持锁后复查：等待期间其他线程可能已经取回新令牌
            entry = store.get(applicationName);
            if (entry == null || entry.isExpired(clock.millis())) {
                entry = fetch(application);
                store.put(applicationName, entry);
            }
            return entry.getAccessToken();
        }
    }

    /**
     * 清除应用的 access token 缓存（令牌失效后重试前调用）
     *
     * @param applicationName 应用名称
     */
    public void evict(String applicationName) {
        store.evict(applicationName);
    }

    private TokenEntry fetch(ApplicationProperties application) {
        logger.info("调用接口获取AccessToken：应用名称-{}", application.getApplicationName());
        AccessTokenResponse response = fetcher.fetch(corpIdOf(application), application.getSecret());
        String accessToken = response == null ? null : response.getAccessToken();
        Integer expiresIn = response == null ? null : response.getExpiresIn();
        return TokenEntry.of(accessToken, expiresIn, clock.millis());
    }

    /**
     * 校验应用名称并返回应用配置；异常信息与 5.x 的 checkApplication / getApplicationSecret 一致
     */
    private ApplicationProperties requireApplication(String applicationName) {
        if (StringUtils.isEmpty(applicationName)) {
            throw new ParamCheckException("调用接口的应用名称不能为空");
        }

        ApplicationProperties application = config.findApplication(applicationName);
        if (application == null) {
            List<String> validAppNames = config.getApplicationList().stream()
                    .map(ApplicationProperties::getApplicationName)
                    .collect(Collectors.toList());
            throw new ParamCheckException("应用名称【" + applicationName + "】无效，不在有效配置列表之内-->" + validAppNames);
        }

        if (StringUtils.isEmpty(application.getSecret())) {
            throw new ParamCheckException(applicationName + "应用不存在密匙");
        }

        return application;
    }

    /**
     * 应用未单独配置企业号时回退到全局 corpId
     */
    private String corpIdOf(ApplicationProperties application) {
        return StringUtils.defaultIfEmpty(application.getCorpId(), config.getCorpId());
    }

    private Object lockFor(String applicationName) {
        return locks.computeIfAbsent(applicationName, key -> new Object());
    }

    /**
     * 取令牌的实际执行者；由入口装配层接到 client 的 gettoken 方法上
     */
    @FunctionalInterface
    public interface TokenFetcher {

        /**
         * 调用 gettoken
         *
         * @param corpId 企业号
         * @param secret 应用密匙
         * @return gettoken 响应
         */
        AccessTokenResponse fetch(String corpId, String secret);
    }
}
