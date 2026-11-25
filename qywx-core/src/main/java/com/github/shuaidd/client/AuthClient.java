package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.auth.AuthUserInfoResponse;
import com.github.shuaidd.response.auth.UserSensitiveDetailResponse;
import com.github.shuaidd.response.auth.UserTfaInfoResponse;
import com.github.shuaidd.resquest.auth.UserSensitiveRequest;
import com.github.shuaidd.resquest.auth.UserTfaInfoRequest;
import com.github.shuaidd.resquest.auth.UserTfaSuccRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 身份认证
 */
public interface AuthClient extends CommonClient {

    /**
     * 获取访问用户身份
     *
     * @param code 授权码
     * @param app  应用
     * @return 用户身份信息
     */
    @RequestLine("GET /auth/getuserinfo?code={code}&app={app}")
    @Headers(HEAD)
    AuthUserInfoResponse getUserInfo(@Param("code") String code, @Param("app") String app);

    /**
     * 获取访问用户敏感信息
     *
     * @param request 请求信息
     * @param app     应用
     * @return 用户详情
     */
    @RequestLine("POST /auth/getuserdetail?app={app}")
    @Headers(HEAD)
    UserSensitiveDetailResponse getUserDetail(UserSensitiveRequest request, @Param("app") String app);

    /**
     * 获取用户二次验证信息
     *
     * @param request 请求信息
     * @param app     应用
     * @return 用户二次验证信息
     */
    @RequestLine("POST /auth/get_tfa_info?app={app}")
    @Headers(HEAD)
    UserTfaInfoResponse getUserTfaInfo(UserTfaInfoRequest request, @Param("app") String app);

    /**
     * 使用二次验证
     *
     * @param request 请求信息
     * @param app     应用
     * @return BaseResponse
     */
    @RequestLine("POST /user/tfa_succ?app={app}")
    @Headers(HEAD)
    BaseResponse useUserTfa(UserTfaSuccRequest request, @Param("app") String app);
}
