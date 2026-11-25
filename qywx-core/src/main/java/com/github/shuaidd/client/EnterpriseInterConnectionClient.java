package com.github.shuaidd.client;

import com.github.shuaidd.response.corp.AppShareInfoResponse;
import com.github.shuaidd.response.corp.TransferSessionResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.resquest.corp.AppShareInfoRequest;
import com.github.shuaidd.resquest.corp.CorpTokenRequest;
import com.github.shuaidd.resquest.corp.TransferSessionRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 企业互联
 */
public interface EnterpriseInterConnectionClient extends CommonClient {

    /**
     * 获取应用共享信息
     *
     * @param request 请求
     * @param app     应用
     * @return 应用共享信息
     */
    @RequestLine("POST /corpgroup/corp/list_app_share_info?app={app}")
    @Headers(HEAD)
    AppShareInfoResponse appShareInfo(AppShareInfoRequest request, @Param("app") String app);

    /**
     * 获取accessToken
     *
     * @param request 请求
     * @param app     应用
     * @return 获取accessToken
     */
    @RequestLine("POST /corpgroup/corp/gettoken?app={app}")
    @Headers(HEAD)
    AccessTokenResponse getToken(CorpTokenRequest request, @Param("app") String app);

    /**
     * 获取下级/下游企业小程序session
     *
     * @param request 请求
     * @param app     应用
     * @return 应用共享信息
     */
    @RequestLine("POST /miniprogram/transfer_session?app={app}")
    @Headers(HEAD)
    TransferSessionResponse transferSession(TransferSessionRequest request, @Param("app") String app);
}
