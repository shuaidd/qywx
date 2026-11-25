package com.github.shuaidd.client;

import com.github.shuaidd.response.auth.ProviderTokenResponse;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import feign.RequestLine;

/**
 * @author ddshuai
 * date 2022-09-05 21:52
 **/
public interface ServiceProviderClient {

    /**
     * 获取服务商凭证
     * @param request 请求
     * @return 凭证信息
     */
    @RequestLine("POST /service/get_provider_token")
    ProviderTokenResponse getProviderToken(ProviderTokenRequest request);
}
