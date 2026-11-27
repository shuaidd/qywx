package com.github.shuaidd.service;

import com.github.shuaidd.QywxContext;
import com.github.shuaidd.client.AuthClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.auth.AuthUserInfoResponse;
import com.github.shuaidd.response.auth.UserSensitiveDetailResponse;
import com.github.shuaidd.response.auth.UserTfaInfoResponse;
import com.github.shuaidd.response.tool.ApiDomainIpResponse;
import com.github.shuaidd.response.auth.AuthenticationResponse;
import com.github.shuaidd.response.tool.OpenUserId2UserIdResponse;
import com.github.shuaidd.response.tool.TmpExternalUserIdConvertResponse;
import com.github.shuaidd.response.tool.TransformExternalUserIdResponse;
import com.github.shuaidd.resquest.auth.UserSensitiveRequest;
import com.github.shuaidd.resquest.auth.UserTfaInfoRequest;
import com.github.shuaidd.resquest.auth.UserTfaSuccRequest;
import com.github.shuaidd.resquest.tool.OpenUserId2UserIdRequest;
import com.github.shuaidd.resquest.tool.TmpExternalUserIdConvertRequest;
import com.github.shuaidd.resquest.tool.TransformExternalUserIdRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import java.util.List;

/**
 * 身份认证
 *
 * @author ddshuai
 * date 2019-04-06 21:08
 **/
public class AuthenticationService extends AbstractBaseService {

    private final AuthClient authClient;

    public AuthenticationService(QywxContext context, AuthClient authClient) {
        super(context);
        this.authClient = authClient;
    }

    /**
     * 获取访问用户身份
     *
     * @param code            授权code
     * @param applicationName 应用名称
     * @return AuthenticationResponse
     */
    public AuthenticationResponse getAuthentication(String code, String applicationName) {
        AuthenticationResponse response;
        if (StringUtils.isNotEmpty(code)) {
            response = addressBookClient.getAuthentication(code, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("应用: {},根据code:{},获取到用户身份：{}", applicationName, code, response);
            }
        } else {
            throw new ParamCheckException("code 不能为空，请检查！！！");
        }

        return response;
    }

    /**
     * 获取企业微信API域名IP段
     *
     * @param applicationName 应用名称
     * @return ip 列表
     */
    public List<String> apiDomainIp(String applicationName) {
        ApiDomainIpResponse apiDomainIpResponse = weChatClient.apiDomainIp(applicationName);
        return apiDomainIpResponse.getIpList();
    }

    /**
     * 获取企业微信服务器回调的ip段
     *
     * @param applicationName 应用名称
     * @return ip 列表
     */
    public List<String> callBackIp(String applicationName) {
        ApiDomainIpResponse apiDomainIpResponse = weChatClient.callBackIp(applicationName);
        return apiDomainIpResponse.getIpList();
    }

    /**
     * userid转换
     *
     * @param request         请求
     * @param applicationName 应用名
     * @return 转换结果
     */
    public OpenUserId2UserIdResponse openUserId2UserId(OpenUserId2UserIdRequest request, String applicationName) {
        return weChatClient.openUserId2UserId(request, applicationName);
    }

    /**
     * external_userid转换
     *
     * @param request         请求
     * @param applicationName 应用名
     * @return 转换结果
     */
    public TransformExternalUserIdResponse transformExternalUserId(TransformExternalUserIdRequest request, String applicationName) {
        return weChatClient.transformExternalUserId(request, applicationName);
    }

    /**
     * tmp_external_userid转换
     *
     * @param request         请求
     * @param applicationName 应用名
     * @return 转换结果
     */
    public TmpExternalUserIdConvertResponse convertTmpExternalUserId(TmpExternalUserIdConvertRequest request, String applicationName) {
        if (Objects.nonNull(request)
                && Objects.nonNull(request.getBusinessType())
                && Objects.nonNull(request.getUserType())
                && CollectionUtils.isNotEmpty(request.getTmpExternalUserIdList())) {
            TmpExternalUserIdConvertResponse response = weChatClient.convertTmpExternalUserId(request, applicationName);
            if (logger.isInfoEnabled()) {
                logger.info("tmp_external_userid转换成功：applicationName-{},result-{}", applicationName, response);
            }

            return response;
        }

        throw new ParamCheckException("businessType、userType和tmpExternalUserIdList不能为空，请检查！！！");
    }

    /**
     * 获取访问用户身份
     * @param code 授权码
     * @param applicationName 应用名
     * @return 用户信息
     */
    public AuthUserInfoResponse getUserInfo(String code, String applicationName) {
        return authClient.getUserInfo(code, applicationName);
    }

    /**
     * 获取访问用户敏感信息
     * @param userTicket ticket
     * @param applicationName 应用名
     * @return 用户信息
     */
    public UserSensitiveDetailResponse getUserDetail(String userTicket, String applicationName) {
        UserSensitiveRequest request = new UserSensitiveRequest();
        request.setUserTicket(userTicket);
        return authClient.getUserDetail(request, applicationName);
    }

    /**
     * 获取用户二次验证信息
     *
     * @param code            授权码
     * @param applicationName 应用名
     * @return 用户二次验证信息
     */
    public UserTfaInfoResponse getUserTfaInfo(String code, String applicationName) {
        if (StringUtils.isEmpty(code)) {
            throw new ParamCheckException("code 不能为空，请检查！！！");
        }

        UserTfaInfoRequest request = new UserTfaInfoRequest();
        request.setCode(code);
        return authClient.getUserTfaInfo(request, applicationName);
    }

    /**
     * 使用二次验证
     *
     * @param userId          用户编号
     * @param tfaCode         二次验证码
     * @param applicationName 应用名
     * @return BaseResponse
     */
    public BaseResponse useUserTfa(String userId, String tfaCode, String applicationName) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(tfaCode)) {
            throw new ParamCheckException("userId和tfaCode不能为空，请检查！！！");
        }

        UserTfaSuccRequest request = new UserTfaSuccRequest();
        request.setUserId(userId);
        request.setTfaCode(tfaCode);
        return authClient.useUserTfa(request, applicationName);
    }
}
