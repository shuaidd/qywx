package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.token.TokenManager;

import java.util.Objects;

/**
 * 描述 访问令牌管理：按应用名获取、清除 access_token，不依赖 Spring Cache 与 AOP
 * <p>
 * 由 5.x 的同名 service 迁移而来。5.x 用 {@code @Cacheable}/{@code @CacheEvict} 把令牌缓存在 Spring Cache，
 * core 改为构造器注入 {@link TokenManager}，参数校验、缓存与并发防击穿都在 manager 内完成；
 * 与其余 service 一样继承 {@link AbstractBaseService}，依赖经 {@link QywxContext} 注入。
 *
 * @author ddshuai
 * date 2019-04-03 16:10
 **/
public class TokenService extends AbstractBaseService {

    private final TokenManager tokenManager;

    public TokenService(QywxContext context, TokenManager tokenManager) {
        super(context);
        this.tokenManager = Objects.requireNonNull(tokenManager, "tokenManager");
    }

    /**
     * 获取企业微信应用的access token；缓存命中时不发起网络请求
     *
     * @param applicationName 应用名称
     * @return String
     */
    public String getAccessToken(String applicationName) {
        return tokenManager.getAccessToken(applicationName);
    }

    /**
     * 清除应用AccessToken缓存，下次获取时重新取号
     *
     * @param cacheApplicationName 应用名称
     */
    public void clearAccessToken(String cacheApplicationName) {
        tokenManager.evict(cacheApplicationName);
    }
}
