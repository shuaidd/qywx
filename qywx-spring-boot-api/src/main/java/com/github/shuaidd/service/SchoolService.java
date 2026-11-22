package com.github.shuaidd.service;

import com.github.shuaidd.client.SchoolClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.resquest.school.ConvertExternalUserToOpenIdRequest;
import com.github.shuaidd.resquest.school.SetChatCreateModeRequest;
import com.github.shuaidd.resquest.school.SetSchoolNotificationModeRequest;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.school.ChatCreateModeResponse;
import com.github.shuaidd.response.school.ExternalUserOpenIdResponse;
import com.github.shuaidd.response.school.SchoolAgentAllowScopeResponse;
import com.github.shuaidd.response.school.SchoolNotificationModeResponse;
import com.github.shuaidd.response.school.SchoolNotificationQrCodeResponse;
import com.github.shuaidd.response.school.SchoolUserInfoResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 家校沟通服务。REST 请求前完成基础业务参数校验，真实权限仍由企业微信平台控制。
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Service
public class SchoolService extends AbstractBaseService {

    private final SchoolClient schoolClient;

    public SchoolService(SchoolClient schoolClient) {
        this.schoolClient = schoolClient;
    }

    /**
     * 获取「学校通知」二维码
     *
     * @param applicationName 应用名称
     * @return 二维码链接
     */
    public SchoolNotificationQrCodeResponse getNotificationQrCode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getNotificationQrCode(applicationName);
    }

    /**
     * 设置关注「学校通知」的模式
     *
     * @param subscribeMode   关注模式
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setNotificationMode(Integer subscribeMode, String applicationName) {
        checkNotificationMode(subscribeMode);
        SetSchoolNotificationModeRequest request = new SetSchoolNotificationModeRequest();
        request.setSubscribeMode(subscribeMode);
        return schoolClient.setNotificationMode(request, applicationName);
    }

    /**
     * 获取关注「学校通知」的模式
     *
     * @param applicationName 应用名称
     * @return 关注模式
     */
    public SchoolNotificationModeResponse getNotificationMode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getNotificationMode(applicationName);
    }

    /**
     * 将微信外部联系人userid转为openid
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return openid
     */
    public ExternalUserOpenIdResponse convertExternalUserToOpenId(ConvertExternalUserToOpenIdRequest request, String applicationName) {
        if (Objects.isNull(request) || StringUtils.isEmpty(request.getExternalUserId())) {
            throw new ParamCheckException("external_userid 不能为空，请检查！！！");
        }
        return schoolClient.convertExternalUserToOpenId(request, applicationName);
    }

    /**
     * 获取班级群创建方式
     *
     * @param applicationName 应用名称
     * @return 创建方式
     */
    public ChatCreateModeResponse getChatCreateMode(String applicationName) {
        checkApplication(applicationName);
        return schoolClient.getChatCreateMode(applicationName);
    }

    /**
     * 设置班级群创建方式
     *
     * @param createMode      创建方式
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setChatCreateMode(Integer createMode, String applicationName) {
        if (Objects.isNull(createMode) || createMode < 0 || createMode > 1) {
            throw new ParamCheckException("create_mode 只能为0或1，请检查！！！");
        }
        SetChatCreateModeRequest request = new SetChatCreateModeRequest();
        request.setCreateMode(createMode);
        return schoolClient.setChatCreateMode(request, applicationName);
    }

    /**
     * 获取可在「学校通知-学校应用」使用应用的家长范围
     *
     * @param agentid         应用id
     * @param applicationName 应用名称
     * @return 可用范围
     */
    public SchoolAgentAllowScopeResponse getAgentAllowScope(Integer agentid, String applicationName) {
        if (Objects.isNull(agentid) || agentid <= 0) {
            throw new ParamCheckException("agentid 不能为空且必须大于0，请检查！！！");
        }
        return schoolClient.getAgentAllowScope(agentid, applicationName);
    }

    /**
     * 获取家校访问用户身份
     *
     * @param code            授权码
     * @param applicationName 应用名称
     * @return 家长或学生身份
     */
    public SchoolUserInfoResponse getSchoolUserInfo(String code, String applicationName) {
        if (StringUtils.isEmpty(code)) {
            throw new ParamCheckException("code 不能为空，请检查！！！");
        }
        if (code.length() > 512) {
            throw new ParamCheckException("code 长度不能超过512字节，请检查！！！");
        }
        return schoolClient.getSchoolUserInfo(code, applicationName);
    }

    private void checkNotificationMode(Integer subscribeMode) {
        if (Objects.isNull(subscribeMode) || subscribeMode < 1 || subscribeMode > 2) {
            throw new ParamCheckException("subscribe_mode 只能为1或2，请检查！！！");
        }
    }
}
