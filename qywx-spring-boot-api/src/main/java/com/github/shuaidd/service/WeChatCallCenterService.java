package com.github.shuaidd.service;

import com.github.shuaidd.exception.WeChatException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.kf.*;
import com.github.shuaidd.resquest.kf.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信客服中心
 *
 * @author ddshuai
 * date 2021-11-24 16:08
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

        AddKfAccountResponse response = callCenterClient.addKfAccount(request, applicationName);
        isSuccess(response);

        return response.getOpenKfId();
    }

    /**
     * 更新客服账号信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateKfAccount(KfAccountRequest request, String applicationName) {
        if (request == null || StringUtils.isEmpty(request.getOpenKfId())) {
            throw new WeChatException("请求信息不合法");
        }
        if (StringUtils.isEmpty(request.getMediaId()) && StringUtils.isEmpty(request.getName())) {
            throw new WeChatException("客服名称或头像不能同时为空");
        }
        checkApplication(applicationName);
        BaseResponse response = callCenterClient.updateKfAccount(request, applicationName);
        isSuccess(response);
    }

    /**
     * 删除客服账号
     *
     * @param kfId            客服编号
     * @param applicationName 应用名称
     */
    public void delKfAccount(String kfId, String applicationName) {
        if (StringUtils.isEmpty(kfId)) {
            throw new WeChatException("客服编号不能为空");
        }
        checkApplication(applicationName);
        DelKfAccountRequest request = new DelKfAccountRequest();
        request.setOpenKfId(kfId);

        BaseResponse response = callCenterClient.delKfAccount(request, applicationName);
        isSuccess(response);
    }

    /**
     * 获取客服账号列表
     *
     * @param applicationName 应用名称
     * @return 客户账号列表
     */
    public KfAccountListResponse getAccountList(String applicationName) {
        checkApplication(applicationName);
        KfAccountListResponse response = callCenterClient.kfAccountLIst(applicationName);
        isSuccess(response);
        return response;
    }

    /**
     * 获取客服帐号链接
     *
     * @param kfId            客服编号
     * @param scene           场景
     * @param applicationName 应用
     * @return 链接地址
     */
    public String kfContactWay(String kfId, String scene, String applicationName) {
        if (StringUtils.isEmpty(kfId)) {
            throw new WeChatException("客服编号不能为空");
        }
        checkApplication(applicationName);
        KfAddContactWayRequest request = new KfAddContactWayRequest();
        request.setOpenKfId(kfId);
        request.setScene(scene);
        KfAddContactWayResponse response = callCenterClient.kfContactWay(request, applicationName);
        isSuccess(response);
        return response.getUrl();
    }

    /**
     * 添加接待人员
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 结果
     */
    public List<ServicerResponse.ResultDetail> addServicer(ServicerRequest request, String applicationName) {
        checkApplication(applicationName);
        ServicerResponse response = callCenterClient.addServicer(request, applicationName);
        isSuccess(response);
        return response.getResultList();
    }

    /**
     * 删除接待人员
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 结果
     */
    public List<ServicerResponse.ResultDetail> delServicer(ServicerRequest request, String applicationName) {
        checkApplication(applicationName);
        ServicerResponse response = callCenterClient.delServicer(request, applicationName);
        isSuccess(response);
        return response.getResultList();
    }

    /**
     * 获取接待人员列表
     *
     * @param openKfId        客服编号
     * @param applicationName 应用
     * @return 接待人员列表
     */
    public List<ServicerListResponse.Servicer> getServicerList(String openKfId, String applicationName) {
        checkApplication(applicationName);
        ServicerListResponse response = callCenterClient.getServicerList(openKfId, applicationName);
        isSuccess(response);
        return response.getServicerList();
    }

    /**
     * 获取会话状态
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 会话状态
     */
    public ServiceStateResponse getServiceState(ServiceStateRequest request, String applicationName) {
        checkApplication(applicationName);
        ServiceStateResponse response = callCenterClient.getServiceState(request, applicationName);
        isSuccess(response);
        return response;
    }

    /**
     * 变更会话状态
     *
     * @param request         请求
     * @param applicationName 应用
     * @return msgCode
     */
    public String changeServiceState(ChangeServiceStateRequest request, String applicationName) {
        checkApplication(applicationName);
        ChangeServiceStateResponse response = callCenterClient.changeServiceState(request, applicationName);
        isSuccess(response);
        return response.getMsgCode();
    }

    /**
     * 读取消息
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 消息
     */
    public SyncMsgResponse syncMsg(SyncMsgRequest request, String applicationName) {
        checkApplication(applicationName);
        SyncMsgResponse response = callCenterClient.syncMsg(request, applicationName);
        isSuccess(response);
        return response;
    }

    /**
     * 发送消息
     *
     * @param msg             消息
     * @param applicationName 应用
     * @return msgId
     */
    public String sendMsg(SendMsgRequest msg, String applicationName) {
        checkApplication(applicationName);
        SendMsgResponse response = callCenterClient.sendMsg(msg, applicationName);
        isSuccess(response);
        return response.getMsgId();
    }

    /**
     * 发送事件响应消息
     *
     * @param msg             消息
     * @param applicationName 应用
     * @return msgId
     */
    public String sendMsgOnEvent(SendMsgOnEventRequest msg, String applicationName) {
        checkApplication(applicationName);
        SendMsgResponse response = callCenterClient.sendMsgOnEvent(msg, applicationName);
        isSuccess(response);
        return response.getMsgId();
    }

    /**
     * 获取客户基础信息
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 客户基础信息
     */
    public GetCustomerResponse getCustomer(GetCustomerRequest request, String applicationName) {
        checkApplication(applicationName);
        GetCustomerResponse response = callCenterClient.getCustomer(request, applicationName);
        isSuccess(response);
        return response;
    }

    /**
     * 获取配置的专员与客户群
     *
     * @param applicationName 应用
     * @return 配置信息
     */
    public UpgradeServiceConfigResponse getUpgradeServiceConfig(String applicationName) {
        checkApplication(applicationName);
        UpgradeServiceConfigResponse response = callCenterClient.getUpgradeServiceConfig(applicationName);
        isSuccess(response);
        return response;
    }

    /**
     * 为客户升级为专员或客户群服务
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void upgradeService(UpgradeServiceRequest request, String applicationName) {
        checkApplication(applicationName);
        BaseResponse baseResponse = callCenterClient.upgradeService(request, applicationName);
        isSuccess(baseResponse);
    }

    /**
     * 为客户取消推荐
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void cancelUpgradeService(CancelUpgradeServiceRequest request, String applicationName) {
        checkApplication(applicationName);
        BaseResponse baseResponse = callCenterClient.cancelUpgradeService(request, applicationName);
        isSuccess(baseResponse);
    }
}
