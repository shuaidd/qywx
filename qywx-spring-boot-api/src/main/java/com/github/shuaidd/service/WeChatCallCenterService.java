package com.github.shuaidd.service;

import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.externalcontact.AddContactWayResponse;
import com.github.shuaidd.response.kf.AddKfAccountResponse;
import com.github.shuaidd.response.kf.KfAccountListResponse;
import com.github.shuaidd.response.kf.KfAddContactWayResponse;
import com.github.shuaidd.resquest.kf.KfAddContactWayRequest;
import com.github.shuaidd.resquest.kf.KfAccountRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 微信客服中心
 *
 * @author ddshuai
 * @date 2021-11-24 16:08
 **/
@Service
public class WeChatCallCenterService extends AbstractBaseService {

    /**
     * 添加客服
     *
     * @param name            客服名称
     * @param mediaId         素材编号
     * @param applicationName 应用名称
     * @return 客服编号
     */
    public String addKfAccount(String name, String mediaId, String applicationName) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(mediaId)) {
            throw new WeChatException("客服名称或头像不能为空");
        }

        checkApplication(applicationName);

        KfAccountRequest request = new KfAccountRequest();
        request.setMediaId(mediaId);
        request.setName(name);

        AddKfAccountResponse response = weChatClient.addKfAccount(request, applicationName);
        isSuccess(response);

        return response.getOpenKfId();
    }

    public KfAccountListResponse getAccountList(String applicationName){
        checkApplication(applicationName);
        KfAccountListResponse response = weChatClient.kfAccountLIst(applicationName);
        isSuccess(response);
        return response;
    }

    public String kfContactWay(String kfId,String scene,String applicationName){
        if (StringUtils.isEmpty(kfId)) {
            throw new WeChatException("客服编号不能为空");
        }
        checkApplication(applicationName);
        KfAddContactWayRequest request = new KfAddContactWayRequest();
        request.setOpenKfId(kfId);
        request.setScene(scene);
        KfAddContactWayResponse response = weChatClient.kfContactWay(request,applicationName);
        isSuccess(response);
        return response.getUrl();
    }
}
