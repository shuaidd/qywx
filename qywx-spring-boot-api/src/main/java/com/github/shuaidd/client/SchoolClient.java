package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.school.ChatCreateModeResponse;
import com.github.shuaidd.response.school.ExternalUserOpenIdResponse;
import com.github.shuaidd.response.school.SchoolAgentAllowScopeResponse;
import com.github.shuaidd.response.school.SchoolNotificationModeResponse;
import com.github.shuaidd.response.school.SchoolNotificationQrCodeResponse;
import com.github.shuaidd.response.school.SchoolUserInfoResponse;
import com.github.shuaidd.resquest.school.ConvertExternalUserToOpenIdRequest;
import com.github.shuaidd.resquest.school.SetChatCreateModeRequest;
import com.github.shuaidd.resquest.school.SetSchoolNotificationModeRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 家校沟通服务端接口
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "SchoolClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface SchoolClient extends CommonClient {

    /**
     * 获取「学校通知」二维码
     *
     * @param app 应用名
     * @return 二维码链接
     */
    @GetMapping(value = "externalcontact/get_subscribe_qr_code", headers = HEAD)
    SchoolNotificationQrCodeResponse getNotificationQrCode(@RequestParam(HEAD_KEY) String app);

    /**
     * 设置关注「学校通知」的模式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "externalcontact/set_subscribe_mode", headers = HEAD)
    BaseResponse setNotificationMode(SetSchoolNotificationModeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取关注「学校通知」的模式
     *
     * @param app 应用名
     * @return 关注模式
     */
    @GetMapping(value = "externalcontact/get_subscribe_mode", headers = HEAD)
    SchoolNotificationModeResponse getNotificationMode(@RequestParam(HEAD_KEY) String app);

    /**
     * 将微信外部联系人userid转为openid
     *
     * @param request 请求体
     * @param app     应用名
     * @return openid
     */
    @PostMapping(value = "externalcontact/convert_to_openid", headers = HEAD)
    ExternalUserOpenIdResponse convertExternalUserToOpenId(ConvertExternalUserToOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取班级群创建方式
     *
     * @param app 应用名
     * @return 创建方式
     */
    @GetMapping(value = "school/get_chat_create_mode", headers = HEAD)
    ChatCreateModeResponse getChatCreateMode(@RequestParam(HEAD_KEY) String app);

    /**
     * 设置班级群创建方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return 基础响应
     */
    @PostMapping(value = "school/set_chat_create_mode", headers = HEAD)
    BaseResponse setChatCreateMode(SetChatCreateModeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取可在「学校通知-学校应用」使用应用的家长范围
     *
     * @param agentid 应用id
     * @param app     应用名
     * @return 可用范围
     */
    @GetMapping(value = "school/agent/get_allow_scope", headers = HEAD)
    SchoolAgentAllowScopeResponse getAgentAllowScope(@RequestParam("agentid") Integer agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取家校访问用户身份
     *
     * @param code 授权码
     * @param app  应用名
     * @return 家长或学生身份
     */
    @GetMapping(value = "school/getuserinfo", headers = HEAD)
    SchoolUserInfoResponse getSchoolUserInfo(@RequestParam("code") String code, @RequestParam(HEAD_KEY) String app);
}
