package com.github.shuaidd.service;

import com.github.shuaidd.client.WeChatCallCenterClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.exception.WeChatException;
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

    private final WeChatCallCenterClient callCenterClient;

    public WeChatCallCenterService(WeChatCallCenterClient callCenterClient) {
        this.callCenterClient = callCenterClient;
    }

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

        KfAccountRequest request = new KfAccountRequest();
        request.setMediaId(mediaId);
        request.setName(name);

        AddKfAccountResponse response = callCenterClient.addKfAccount(request, applicationName);

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
        callCenterClient.updateKfAccount(request, applicationName);
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

        DelKfAccountRequest request = new DelKfAccountRequest();
        request.setOpenKfId(kfId);

        callCenterClient.delKfAccount(request, applicationName);
    }

    /**
     * 获取客服账号列表
     *
     * @param applicationName 应用名称
     * @return 客户账号列表
     */
    public KfAccountListResponse getAccountList(String applicationName) {
        return callCenterClient.kfAccountLIst(applicationName);
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

        KfAddContactWayRequest request = new KfAddContactWayRequest();
        request.setOpenKfId(kfId);
        request.setScene(scene);
        KfAddContactWayResponse response = callCenterClient.kfContactWay(request, applicationName);
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
        ServicerResponse response = callCenterClient.addServicer(request, applicationName);
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
        ServicerResponse response = callCenterClient.delServicer(request, applicationName);
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
        ServicerListResponse response = callCenterClient.getServicerList(openKfId, applicationName);
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
        return callCenterClient.getServiceState(request, applicationName);
    }

    /**
     * 变更会话状态
     *
     * @param request         请求
     * @param applicationName 应用
     * @return msgCode
     */
    public String changeServiceState(ChangeServiceStateRequest request, String applicationName) {
        ChangeServiceStateResponse response = callCenterClient.changeServiceState(request, applicationName);
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
        return callCenterClient.syncMsg(request, applicationName);
    }

    /**
     * 发送消息
     *
     * @param msg             消息
     * @param applicationName 应用
     * @return msgId
     */
    public String sendMsg(SendMsgRequest msg, String applicationName) {
        SendMsgResponse response = callCenterClient.sendMsg(msg, applicationName);
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
        SendMsgResponse response = callCenterClient.sendMsgOnEvent(msg, applicationName);
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
        return callCenterClient.getCustomer(request, applicationName);
    }

    /**
     * 获取配置的专员与客户群
     *
     * @param applicationName 应用
     * @return 配置信息
     */
    public UpgradeServiceConfigResponse getUpgradeServiceConfig(String applicationName) {
        return callCenterClient.getUpgradeServiceConfig(applicationName);
    }

    /**
     * 为客户升级为专员或客户群服务
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void upgradeService(UpgradeServiceRequest request, String applicationName) {
        callCenterClient.upgradeService(request, applicationName);
    }

    /**
     * 为客户取消推荐
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void cancelUpgradeService(CancelUpgradeServiceRequest request, String applicationName) {
        callCenterClient.cancelUpgradeService(request, applicationName);
    }

    /**
     * 添加分组
     *
     * @param name            分组名称
     * @param applicationName 应用
     * @return 分组编号
     */
    public String addKnowledgeGroup(String name, String applicationName) {

        if (StringUtils.isEmpty(name)) {
            throw new ParamCheckException("分组名称不能为空");
        }

        KnowledgeGroupRequest request = new KnowledgeGroupRequest();
        request.setName(name);
        AddKnowledgeGroupResponse response = callCenterClient.addKnowledgeGroup(request, applicationName);
        return response.getGroupId();
    }

    /**
     * 修改分组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void modKnowledgeGroup(KnowledgeGroupRequest request, String applicationName) {
        callCenterClient.modKnowledgeGroup(request, applicationName);
    }

    /**
     * 删除分组
     *
     * @param groupId         分组编号
     * @param applicationName 应用
     */
    public void delKnowledgeGroup(String groupId, String applicationName) {
        if (StringUtils.isEmpty(groupId)) {
            throw new ParamCheckException("分组编号不能为空");
        }

        DelKnowledgeGroupRequest request = new DelKnowledgeGroupRequest();
        request.setGroupId(groupId);

        callCenterClient.delKnowledgeGroup(request, applicationName);
    }

    /**
     * 查询分组列表
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 分组列表
     */
    public KnowledgeGroupResponse queryKnowledgeGroup(QueryKnowledgeGroupRequest request, String applicationName) {
        return callCenterClient.queryKnowledgeGroup(request, applicationName);
    }

    /**
     * 添加问答
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 问答编号
     */
    public String addKnowledgeIntent(KnowledgeIntentRequest request, String applicationName) {
        return callCenterClient.addKnowledgeIntent(request, applicationName).getIntentId();
    }

    /**
     * 修改问答
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void modKnowledgeIntent(KnowledgeIntentRequest request, String applicationName) {
        callCenterClient.modKnowledgeIntent(request, applicationName);
    }

    /**
     * 删除问答
     *
     * @param intentId        问答编号
     * @param applicationName 应用
     */
    public void delKnowledgeIntent(String intentId, String applicationName) {
        if (StringUtils.isEmpty(intentId)) {
            throw new ParamCheckException("问答编号不能为空");
        }

        DelKnowledgeIntentRequest request = new DelKnowledgeIntentRequest();
        request.setIntentId(intentId);
        callCenterClient.delKnowledgeIntent(request, applicationName);
    }

    /**
     * 获取问答列表
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 问答列表
     */
    public KnowledgeIntentResponse queryKnowledgeIntent(QueryKnowledgeIntentRequest request, String applicationName) {
        return callCenterClient.queryKnowledgeIntent(request, applicationName);
    }
}
