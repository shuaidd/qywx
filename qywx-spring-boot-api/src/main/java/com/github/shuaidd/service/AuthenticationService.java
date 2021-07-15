package com.github.shuaidd.service;

import com.github.shuaidd.response.ApiDomainIpResponse;
import com.github.shuaidd.response.AuthenticationResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 获取企业微信API域名IP段
     * @param applicationName 应用名称
     * @return ip 列表
     */
    public List<String> apiDomainIp(String applicationName){
        ApiDomainIpResponse apiDomainIpResponse = weChatClient.apiDomainIp(applicationName);
        getResponse(apiDomainIpResponse);
        return apiDomainIpResponse.getIpList();
    }

    /**
     * 获取企业微信服务器回调的ip段
     * @param applicationName 应用名称
     * @return ip 列表
     */
    public List<String> callBackIp(String applicationName){
        ApiDomainIpResponse apiDomainIpResponse = weChatClient.callBackIp(applicationName);
        getResponse(apiDomainIpResponse);
        return apiDomainIpResponse.getIpList();
    }
}
