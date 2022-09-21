package com.github.shuaidd.service;

import com.github.shuaidd.response.auth.ProviderTokenResponse;
import com.github.shuaidd.resquest.auth.ProviderTokenRequest;
import org.springframework.stereotype.Service;

/**
 * @author ddshuai
 * date 2022-09-05 21:55
 **/
@Service
public class ServiceProviderService extends AbstractBaseService {

    /**
     * 获取服务商凭证
     * @param request 请求
     * @return 凭证信息
     */
    public ProviderTokenResponse getProviderToken(ProviderTokenRequest request) {
        return serviceProviderClient.getProviderToken(request);
    }
}
