package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.kf.AddIntentResponse;
import com.github.shuaidd.response.kf.AddKfAccountResponse;
import com.github.shuaidd.response.kf.AddKnowledgeGroupResponse;
import com.github.shuaidd.response.kf.ChangeServiceStateResponse;
import com.github.shuaidd.response.kf.GetCorpStatisticResponse;
import com.github.shuaidd.response.kf.GetCustomerResponse;
import com.github.shuaidd.response.kf.GetServicerStatisticResponse;
import com.github.shuaidd.response.kf.KfAccountListResponse;
import com.github.shuaidd.response.kf.KfAddContactWayResponse;
import com.github.shuaidd.response.kf.KnowledgeGroupResponse;
import com.github.shuaidd.response.kf.KnowledgeIntentResponse;
import com.github.shuaidd.response.kf.SendMsgResponse;
import com.github.shuaidd.response.kf.ServicerListResponse;
import com.github.shuaidd.response.kf.ServicerResponse;
import com.github.shuaidd.response.kf.ServiceStateResponse;
import com.github.shuaidd.response.kf.SyncMsgResponse;
import com.github.shuaidd.response.kf.UpgradeServiceConfigResponse;
import com.github.shuaidd.resquest.kf.CancelUpgradeServiceRequest;
import com.github.shuaidd.resquest.kf.ChangeServiceStateRequest;
import com.github.shuaidd.resquest.kf.CorpStatisticRequest;
import com.github.shuaidd.resquest.kf.DelKfAccountRequest;
import com.github.shuaidd.resquest.kf.DelKnowledgeGroupRequest;
import com.github.shuaidd.resquest.kf.DelKnowledgeIntentRequest;
import com.github.shuaidd.resquest.kf.GetCustomerRequest;
import com.github.shuaidd.resquest.kf.KfAccountRequest;
import com.github.shuaidd.resquest.kf.KfAddContactWayRequest;
import com.github.shuaidd.resquest.kf.KnowledgeGroupRequest;
import com.github.shuaidd.resquest.kf.KnowledgeIntentRequest;
import com.github.shuaidd.resquest.kf.QueryKnowledgeGroupRequest;
import com.github.shuaidd.resquest.kf.QueryKnowledgeIntentRequest;
import com.github.shuaidd.resquest.kf.SendMsgOnEventRequest;
import com.github.shuaidd.resquest.kf.SendMsgRequest;
import com.github.shuaidd.resquest.kf.ServicerRequest;
import com.github.shuaidd.resquest.kf.ServicerStatisticRequest;
import com.github.shuaidd.resquest.kf.ServiceStateRequest;
import com.github.shuaidd.resquest.kf.SyncMsgRequest;
import com.github.shuaidd.resquest.kf.UpgradeServiceRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 微信客服中心
 *
 * @author ddshuai
 * date 2022-08-31 22:34
 **/
public interface WeChatCallCenterClient extends CommonClient {
    /**
     * 添加客服帐号
     *
     * @param request 请求
     * @param app     应用
     * @return 新创建的客服帐号ID
     */
    @RequestLine("POST /kf/account/add?app={app}")
    @Headers(HEAD)
    AddKfAccountResponse addKfAccount(KfAccountRequest request, @Param("app") String app);

    /**
     * 删除客服帐号
     *
     * @param request 请求
     * @param app     应用
     * @return 无
     */
    @RequestLine("POST /kf/account/del?app={app}")
    @Headers(HEAD)
    BaseResponse delKfAccount(DelKfAccountRequest request, @Param("app") String app);

    /**
     * 修改客服帐号
     *
     * @param request 请求
     * @param app     应用
     * @return 无
     */
    @RequestLine("POST /kf/account/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateKfAccount(KfAccountRequest request, @Param("app") String app);

    /**
     * 获取客服帐号列表
     *
     * @param app 应用名称
     * @return 账户列表
     */
    @RequestLine("GET /kf/account/list?app={app}")
    @Headers(HEAD)
    KfAccountListResponse kfAccountLIst(@Param("app") String app);

    /**
     * 获取客服帐号链接
     *
     * @param request 请求
     * @param app     应用名称
     * @return 客服帐号链接
     */
    @RequestLine("POST /kf/add_contact_way?app={app}")
    @Headers(HEAD)
    KfAddContactWayResponse kfContactWay(KfAddContactWayRequest request, @Param("app") String app);

    /**
     * 添加接待人员
     *
     * @param request 请求
     * @param app     应用
     * @return 添加结果
     */
    @RequestLine("POST /kf/servicer/add?app={app}")
    @Headers(HEAD)
    ServicerResponse addServicer(ServicerRequest request, @Param("app") String app);

    /**
     * 删除接待人员
     *
     * @param request 请求
     * @param app     应用
     * @return 删除结果
     */
    @RequestLine("POST /kf/servicer/del?app={app}")
    @Headers(HEAD)
    ServicerResponse delServicer(ServicerRequest request, @Param("app") String app);

    /**
     * 获取接待人员列表
     *
     * @param openKfId 客服编号
     * @param app      应用
     * @return 接待人员列表
     */
    @RequestLine("GET /kf/servicer/list?open_kfid={open_kfid}&app={app}")
    @Headers(HEAD)
    ServicerListResponse getServicerList(@Param("open_kfid") String openKfId, @Param("app") String app);

    /**
     * 获取会话状态
     *
     * @param request 请求
     * @param app     应用
     * @return 会话状态
     */
    @RequestLine("POST /kf/service_state/get?app={app}")
    @Headers(HEAD)
    ServiceStateResponse getServiceState(ServiceStateRequest request, @Param("app") String app);

    /**
     * 变更会话状态
     *
     * @param request 请求
     * @param app     应用
     * @return msgCode
     */
    @RequestLine("POST /kf/service_state/trans?app={app}")
    @Headers(HEAD)
    ChangeServiceStateResponse changeServiceState(ChangeServiceStateRequest request, @Param("app") String app);

    /**
     * 读取消息
     *
     * @param request 请求
     * @param app     应用
     * @return 消息
     */
    @RequestLine("POST /kf/sync_msg?app={app}")
    @Headers(HEAD)
    SyncMsgResponse syncMsg(SyncMsgRequest request, @Param("app") String app);

    /**
     * 发送消息
     *
     * @param msg 消息
     * @param app 应用
     * @return msgID
     */
    @RequestLine("POST /kf/send_msg?app={app}")
    @Headers(HEAD)
    SendMsgResponse sendMsg(SendMsgRequest msg, @Param("app") String app);

    /**
     * 发送事件响应消息
     *
     * @param request 消息
     * @param app     应用
     * @return msgID
     */
    @RequestLine("POST /kf/send_msg_on_event?app={app}")
    @Headers(HEAD)
    SendMsgResponse sendMsgOnEvent(SendMsgOnEventRequest request, @Param("app") String app);

    /**
     * 获取客户基础信息
     *
     * @param request 请求
     * @param app     应用
     * @return 客户信息
     */
    @RequestLine("POST /kf/customer/batchget?app={app}")
    @Headers(HEAD)
    GetCustomerResponse getCustomer(GetCustomerRequest request, @Param("app") String app);

    /**
     * 获取配置的专员与客户群
     *
     * @param app 应用
     * @return 配置信息
     */
    @RequestLine("GET /kf/customer/get_upgrade_service_config?app={app}")
    @Headers(HEAD)
    UpgradeServiceConfigResponse getUpgradeServiceConfig(@Param("app") String app);

    /**
     * 为客户升级为专员或客户群服务
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /kf/customer/upgrade_service?app={app}")
    @Headers(HEAD)
    BaseResponse upgradeService(UpgradeServiceRequest request, @Param("app") String app);

    /**
     * 为客户取消推荐
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /kf/customer/cancel_upgrade_service?app={app}")
    @Headers(HEAD)
    BaseResponse cancelUpgradeService(CancelUpgradeServiceRequest request, @Param("app") String app);

    /**
     * 可通过此接口创建新的知识库分组
     *
     * @param request 请求
     * @param app     应用
     * @return 分组编号
     */
    @RequestLine("POST /kf/knowledge/add_group?app={app}")
    @Headers(HEAD)
    AddKnowledgeGroupResponse addKnowledgeGroup(KnowledgeGroupRequest request, @Param("app") String app);

    /**
     * 修改分组
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /kf/knowledge/mod_group?app={app}")
    @Headers(HEAD)
    BaseResponse modKnowledgeGroup(KnowledgeGroupRequest request, @Param("app") String app);

    /**
     * 可通过此接口删除已有的知识库分组，但不能删除系统创建的默认分组
     *
     * @param request 请求
     * @param app     应用
     * @return 分组编号
     */
    @RequestLine("POST /kf/knowledge/del_group?app={app}")
    @Headers(HEAD)
    BaseResponse delKnowledgeGroup(DelKnowledgeGroupRequest request, @Param("app") String app);

    /**
     * 获取分组列表
     *
     * @param request 请求
     * @param app     应用
     * @return 结果列表
     */
    @RequestLine("POST /kf/knowledge/list_group?app={app}")
    @Headers(HEAD)
    KnowledgeGroupResponse queryKnowledgeGroup(QueryKnowledgeGroupRequest request, @Param("app") String app);

    /**
     * 添加问答
     *
     * @param request 请求
     * @param app     应用
     * @return 问答编号
     */
    @RequestLine("POST /kf/knowledge/add_intent?app={app}")
    @Headers(HEAD)
    AddIntentResponse addKnowledgeIntent(KnowledgeIntentRequest request, @Param("app") String app);

    /**
     * 修改问答
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /kf/knowledge/mod_intent?app={app}")
    @Headers(HEAD)
    BaseResponse modKnowledgeIntent(KnowledgeIntentRequest request, @Param("app") String app);

    /**
     * 删除问答
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /kf/knowledge/del_intent?app={app}")
    @Headers(HEAD)
    BaseResponse delKnowledgeIntent(DelKnowledgeIntentRequest request, @Param("app") String app);

    /**
     * 获取问答列表
     *
     * @param request 请求
     * @param app     应用
     * @return 问答列表
     */
    @RequestLine("POST /kf/knowledge/list_intent?app={app}")
    @Headers(HEAD)
    KnowledgeIntentResponse queryKnowledgeIntent(QueryKnowledgeIntentRequest request, @Param("app") String app);
    /**
     * 获取客服帐号企业汇总数据
     *
     * @param request 请求
     * @param app     应用
     * @return 企业汇总统计数据
     */
    @RequestLine("POST /kf/get_corp_statistic?app={app}")
    @Headers(HEAD)
    GetCorpStatisticResponse getCorpStatistic(CorpStatisticRequest request, @Param("app") String app);

    /**
     * 获取客服帐号接待人员明细数据
     *
     * @param request 请求
     * @param app     应用
     * @return 接待人员明细统计数据
     */
    @RequestLine("POST /kf/get_servicer_statistic?app={app}")
    @Headers(HEAD)
    GetServicerStatisticResponse getServicerStatistic(ServicerStatisticRequest request, @Param("app") String app);
}
