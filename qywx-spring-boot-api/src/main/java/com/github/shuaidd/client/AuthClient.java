package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.auth.AuthUserInfoResponse;
import com.github.shuaidd.response.auth.UserSensitiveDetailResponse;
import com.github.shuaidd.response.auth.UserTfaInfoResponse;
import com.github.shuaidd.resquest.auth.UserSensitiveRequest;
import com.github.shuaidd.resquest.auth.UserTfaInfoRequest;
import com.github.shuaidd.resquest.auth.UserTfaSuccRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 身份认证
 */
@FeignClient(name = "AuthClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface AuthClient extends CommonClient {

    /**
     * 获取访问用户身份
     *
     * @param code 授权码
     * @param app  应用
     * @return 用户身份信息
     */
    @GetMapping(value = "auth/getuserinfo", headers = HEAD)
    AuthUserInfoResponse getUserInfo(@RequestParam("code") String code, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取访问用户敏感信息
     *
     * @param request 请求信息
     * @param app     应用
     * @return 用户详情
     */
    @PostMapping(value = "auth/getuserdetail", headers = HEAD)
    UserSensitiveDetailResponse getUserDetail(UserSensitiveRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取用户二次验证信息
     *
     * @param request 请求信息
     * @param app     应用
     * @return 用户二次验证信息
     */
    @PostMapping(value = "auth/get_tfa_info", headers = HEAD)
    UserTfaInfoResponse getUserTfaInfo(UserTfaInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 使用二次验证
     *
     * @param request 请求信息
     * @param app     应用
     * @return BaseResponse
     */
    @PostMapping(value = "user/tfa_succ", headers = HEAD)
    BaseResponse useUserTfa(UserTfaSuccRequest request, @RequestParam(HEAD_KEY) String app);
}
