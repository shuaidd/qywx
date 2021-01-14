package com.github.shuaidd.service;

import com.github.shuaidd.response.AuthenticationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 身份认证
 *
 * @author ddshuai
 * date 2019-04-06 21:08
 **/
@Service
public class AuthenticationService extends AbstractBaseService {

    /**
     * 获取访问用户身份
     *
     * @param code 授权code
     * @param applicationName 应用名称
     * @return AuthenticationResponse
     */
    public final AuthenticationResponse getAuthentication(String code, String applicationName) {
        checkApplication(applicationName);
        AuthenticationResponse response = null;
        if (StringUtils.isNotEmpty(code)) {
            response = weChatClient.getAuthentication(code, applicationName);
            if (isSuccess(response)) {
                logger.info("应用: {},根据code:{},获取到用户身份：{}", applicationName, code, response);
            }
        }

        return response;
    }
}
