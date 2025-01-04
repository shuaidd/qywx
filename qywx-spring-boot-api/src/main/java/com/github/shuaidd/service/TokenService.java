package com.github.shuaidd.service;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 描述 访问令牌管理
 *
 * @author ddshuai
 * date 2019-04-04 17:28
 **/
@Service
public class TokenService extends AbstractBaseService {

    /**
     * 获取企业微信应用的access token
     *
     * @param applicationName 应用名称
     * @return String
     */
    @Cacheable(value = "qywx", key = "'qywx_access_token_'+#applicationName")
    public String getAccessToken(String applicationName) {
        if (logger.isInfoEnabled()) {
            logger.info("调用接口获取AccessToken：应用名称-{}", applicationName);
        }
        checkApplication(applicationName);

        ApplicationProperties app = getApp(applicationName);

        if (Objects.isNull(properties)) {
            logger.error("{} 未配置的应用", applicationName);
            throw  new ParamCheckException("未配置的应用");
        }

        AccessTokenResponse accessTokenResponse = weChatClient.getAccessToken(StringUtils.defaultIfEmpty(app.getCorpId(),properties.getCorpId()), app.getSecret());
        return accessTokenResponse.getAccessToken();
    }

    /**
     * 清除应用AccessToken缓存
     *
     * @param cacheApplicationName 应用名称
     */
    @CacheEvict(value = "qywx", key = "'qywx_access_token_'+#cacheApplicationName")
    public void clearAccessToken(String cacheApplicationName) {

    }
}
