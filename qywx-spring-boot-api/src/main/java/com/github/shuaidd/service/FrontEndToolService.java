package com.github.shuaidd.service;

import com.github.shuaidd.client.config.ApplicationProperties;
import com.github.shuaidd.dto.tool.FrontEndTicket;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 描述 前端SDK管理
 *
 * @author huangweilong
 **/
@Service
public class FrontEndToolService extends AbstractBaseService {

    /**
     * 获取企业微信应用的前端ticket
     *
     * @param applicationName 应用名称
     * @return String
     */
    public FrontEndTicket getAppTicket(String applicationName) {
        if (logger.isInfoEnabled()) {
            logger.info("调用接口获取前端ticket：应用名称-{}", applicationName);
        }
        ApplicationProperties app = getApp(applicationName);

        if (Objects.isNull(properties)) {
            logger.error("{} 未配置的应用", applicationName);
            throw  new ParamCheckException("未配置的应用");
        }
        AccessTokenResponse accessTokenResponse = weChatClient.getAccessToken(StringUtils.defaultString(app.getCorpId(),properties.getCorpId()), app.getSecret());
        FrontEndTicket appTicket = frontEndToolsClient.getAppTicket(accessTokenResponse.getAccessToken(), applicationName);
        return appTicket;
    }
}
