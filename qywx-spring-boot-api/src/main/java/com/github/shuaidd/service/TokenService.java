package com.github.shuaidd.service;

import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
        checkApplication(applicationName);
        logger.info("获取AccessToken：{}", applicationName);
        String accessToken = null;
        AccessTokenResponse accessTokenResponse = weChatClient.getAccessToken(properties.getCorpId(), getApplicationSecret(applicationName));
        if (isSuccess(accessTokenResponse)) {
            accessToken = accessTokenResponse.getAccessToken();
        }
        return accessToken;
    }

    /**
     * 清除应用AccessToken缓存
     *
     * @param applicationName 应用名称
     */
    @CacheEvict(value = "qywx", key = "'qywx_access_token_'+#applicationName")
    public void clearAccessToken(String applicationName) {

    }
}
