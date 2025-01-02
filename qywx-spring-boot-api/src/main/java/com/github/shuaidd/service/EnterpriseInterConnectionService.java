package com.github.shuaidd.service;

import com.github.shuaidd.client.EnterpriseInterConnectionClient;
import com.github.shuaidd.response.corp.AppShareInfoResponse;
import com.github.shuaidd.response.corp.TransferSessionResponse;
import com.github.shuaidd.response.tool.AccessTokenResponse;
import com.github.shuaidd.resquest.corp.AppShareInfoRequest;
import com.github.shuaidd.resquest.corp.CorpTokenRequest;
import com.github.shuaidd.resquest.corp.TransferSessionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 企业互联
 * @author ddshuai
 */
@Service
public class EnterpriseInterConnectionService extends AbstractBaseService {

    private final EnterpriseInterConnectionClient enterpriseInterConnectionClient;

    public EnterpriseInterConnectionService(EnterpriseInterConnectionClient enterpriseInterConnectionClient) {
        this.enterpriseInterConnectionClient = enterpriseInterConnectionClient;
    }

    /**
     * 获取应用共享信息
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 应用共享信息
     */
    public AppShareInfoResponse appShareInfo(AppShareInfoRequest request, String applicationName) {
        return enterpriseInterConnectionClient.appShareInfo(request, applicationName);
    }

    /**
     * 获取accessToken
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 获取accessToken
     */
    public AccessTokenResponse getToken(CorpTokenRequest request, String applicationName) {
        return enterpriseInterConnectionClient.getToken(request, applicationName);
    }

    /**
     * 获取下级/下游企业小程序session
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 下级/下游企业小程序session
     */
    public TransferSessionResponse transferSession(TransferSessionRequest request, String applicationName) {
        return enterpriseInterConnectionClient.transferSession(request, applicationName);
    }
}
