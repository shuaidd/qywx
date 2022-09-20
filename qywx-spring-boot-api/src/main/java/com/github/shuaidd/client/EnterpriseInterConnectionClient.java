package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.corp.AppShareInfoResponse;
import com.github.shuaidd.response.corp.TransferSessionResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.resquest.corp.AppShareInfoRequest;
import com.github.shuaidd.resquest.corp.CorpTokenRequest;
import com.github.shuaidd.resquest.corp.TransferSessionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 企业互联
 */
@FeignClient(name = "EnterpriseInterConnectionClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface EnterpriseInterConnectionClient extends CommonClient {

    /**
     * 获取应用共享信息
     *
     * @param request 请求
     * @param app     应用
     * @return 应用共享信息
     */
    @PostMapping(value = "/corpgroup/corp/list_app_share_info", headers = HEAD)
    AppShareInfoResponse appShareInfo(AppShareInfoRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取应用共享信息
     *
     * @param request 请求
     * @param app     应用
     * @return 应用共享信息
     */
    @PostMapping(value = "/corpgroup/corp/gettoken", headers = HEAD)
    AccessTokenResponse getToken(CorpTokenRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取下级/下游企业小程序session
     *
     * @param request 请求
     * @param app     应用
     * @return 应用共享信息
     */
    @PostMapping(value = "/miniprogram/transfer_session", headers = HEAD)
    TransferSessionResponse transferSession(TransferSessionRequest request, @RequestParam(HEAD_KEY) String app);
}
