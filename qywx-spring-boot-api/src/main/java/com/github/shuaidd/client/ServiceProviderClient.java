package com.github.shuaidd.client;

import com.github.shuaidd.response.auth.ProviderTokenResponse;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author ddshuai
 * date 2022-09-05 21:52
 **/
@FeignClient(name = "ServiceProviderClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}")
public interface ServiceProviderClient {

    /**
     * 获取服务商凭证
     * @param request 请求
     * @return 凭证信息
     */
    @PostMapping("service/get_provider_token")
    ProviderTokenResponse getProviderToken(ProviderTokenRequest request);
}
