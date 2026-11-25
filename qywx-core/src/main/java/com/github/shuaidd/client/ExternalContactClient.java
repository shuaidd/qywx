package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.externalcontact.AddContactWayResponse;
import com.github.shuaidd.response.externalcontact.AddCorpTagResponse;
import com.github.shuaidd.response.externalcontact.AddInterceptRuleResponse;
import com.github.shuaidd.response.externalcontact.AddProductAlbumResponse;
import com.github.shuaidd.response.externalcontact.AddWelcomeTemplateResponse;
import com.github.shuaidd.response.externalcontact.BatchExternalContactResponse;
import com.github.shuaidd.response.externalcontact.BehaviorDataResponse;
import com.github.shuaidd.response.externalcontact.CommentsResponse;
import com.github.shuaidd.response.externalcontact.ContactWayResponse;
import com.github.shuaidd.response.externalcontact.CreateCustomerAcquisitionLinkResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionChatInfoResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionCustomerListResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionLinkListResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionLinkResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionQuotaResponse;
import com.github.shuaidd.response.externalcontact.CustomerAcquisitionStatisticResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyIdResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyListResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyRangeResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyTagCreateResponse;
import com.github.shuaidd.response.externalcontact.CustomerStrategyTagListResponse;
import com.github.shuaidd.response.externalcontact.CustomListResponse;
import com.github.shuaidd.response.externalcontact.ExternalContactResponse;
import com.github.shuaidd.response.externalcontact.FailedChatResponse;
import com.github.shuaidd.response.externalcontact.FollowUserResponse;
import com.github.shuaidd.response.externalcontact.GetInterceptRuleResponse;
import com.github.shuaidd.response.externalcontact.GetProductAlbumResponse;
import com.github.shuaidd.response.externalcontact.GetWelcomeTemplateResponse;
import com.github.shuaidd.response.externalcontact.GroupChatDetailResponse;
import com.github.shuaidd.response.externalcontact.GroupChatJoinWayConfigResponse;
import com.github.shuaidd.response.externalcontact.GroupChatJoinWayCreateResponse;
import com.github.shuaidd.response.externalcontact.GroupChatListResponse;
import com.github.shuaidd.response.externalcontact.GroupChatStatisticResponse;
import com.github.shuaidd.response.externalcontact.GroupMsgResponse;
import com.github.shuaidd.response.externalcontact.GroupMsgSendResultResponse;
import com.github.shuaidd.response.externalcontact.GroupMsgTaskResponse;
import com.github.shuaidd.response.externalcontact.InterceptRuleListResponse;
import com.github.shuaidd.response.externalcontact.MomentCustomerListResponse;
import com.github.shuaidd.response.externalcontact.MomentListResponse;
import com.github.shuaidd.response.externalcontact.MomentPublishCreateResponse;
import com.github.shuaidd.response.externalcontact.MomentPublishTaskResultResponse;
import com.github.shuaidd.response.externalcontact.MomentStrategyIdResponse;
import com.github.shuaidd.response.externalcontact.MomentStrategyListResponse;
import com.github.shuaidd.response.externalcontact.MomentStrategyRangeResponse;
import com.github.shuaidd.response.externalcontact.MomentStrategyResponse;
import com.github.shuaidd.response.externalcontact.MomentTaskResponse;
import com.github.shuaidd.response.externalcontact.MsgTemplateResponse;
import com.github.shuaidd.response.externalcontact.OpenGidToChatIdResponse;
import com.github.shuaidd.response.externalcontact.ProductAlbumListResponse;
import com.github.shuaidd.response.externalcontact.SendSchoolNotificationResponse;
import com.github.shuaidd.response.externalcontact.ServedContactListResponse;
import com.github.shuaidd.response.externalcontact.TagGroupResponse;
import com.github.shuaidd.response.externalcontact.TransferResultResponse;
import com.github.shuaidd.response.externalcontact.UnassignedListResponse;
import com.github.shuaidd.response.externalcontact.V2GroupMsgResponse;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.PageRequest;
import com.github.shuaidd.resquest.addressbook.TagGroupRequest;
import com.github.shuaidd.resquest.externalcontact.AddContactWayRequest;
import com.github.shuaidd.resquest.externalcontact.AddCorpTagRequest;
import com.github.shuaidd.resquest.externalcontact.AddInterceptRuleRequest;
import com.github.shuaidd.resquest.externalcontact.AddProductAlbumRequest;
import com.github.shuaidd.resquest.externalcontact.BatchExternalContactRequest;
import com.github.shuaidd.resquest.externalcontact.CloseTempChatRequest;
import com.github.shuaidd.resquest.externalcontact.CommentsRequest;
import com.github.shuaidd.resquest.externalcontact.CreateCustomerAcquisitionLinkRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerAcquisitionChatKeyRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerAcquisitionCustomerListRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerAcquisitionLinkIdRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerAcquisitionLinkListRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerAcquisitionStatisticRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyCreateRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyEditRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyIdRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyListRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyRangeRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyTagCreateRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyTagDeleteRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyTagEditRequest;
import com.github.shuaidd.resquest.externalcontact.CustomerStrategyTagListRequest;
import com.github.shuaidd.resquest.externalcontact.DelCorpTagRequest;
import com.github.shuaidd.resquest.externalcontact.DeleteWelcomeTemplateRequest;
import com.github.shuaidd.resquest.externalcontact.DelProductAlbumRequest;
import com.github.shuaidd.resquest.externalcontact.EditCorpTagRequest;
import com.github.shuaidd.resquest.externalcontact.GetContactWayRequest;
import com.github.shuaidd.resquest.externalcontact.GetProductAlbumRequest;
import com.github.shuaidd.resquest.externalcontact.GetWelcomeTemplateRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatDetailRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatJoinWayConfigRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatJoinWayRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatListRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatStatisticRequest;
import com.github.shuaidd.resquest.externalcontact.GroupChatTransferRequest;
import com.github.shuaidd.resquest.externalcontact.GroupMsgCancelRequest;
import com.github.shuaidd.resquest.externalcontact.GroupMsgRemindRequest;
import com.github.shuaidd.resquest.externalcontact.GroupMsgRequest;
import com.github.shuaidd.resquest.externalcontact.GroupMsgSendRequest;
import com.github.shuaidd.resquest.externalcontact.GroupMsgTaskRequest;
import com.github.shuaidd.resquest.externalcontact.InterceptRuleIdRequest;
import com.github.shuaidd.resquest.externalcontact.MarkTagRequest;
import com.github.shuaidd.resquest.externalcontact.MomentCustomerListRequest;
import com.github.shuaidd.resquest.externalcontact.MomentListRequest;
import com.github.shuaidd.resquest.externalcontact.MomentPublishCancelRequest;
import com.github.shuaidd.resquest.externalcontact.MomentPublishRequest;
import com.github.shuaidd.resquest.externalcontact.MomentStrategyCreateRequest;
import com.github.shuaidd.resquest.externalcontact.MomentStrategyEditRequest;
import com.github.shuaidd.resquest.externalcontact.MomentStrategyIdRequest;
import com.github.shuaidd.resquest.externalcontact.MomentStrategyListRequest;
import com.github.shuaidd.resquest.externalcontact.MomentStrategyRangeRequest;
import com.github.shuaidd.resquest.externalcontact.MomentTaskRequest;
import com.github.shuaidd.resquest.externalcontact.MsgTemplateRequest;
import com.github.shuaidd.resquest.externalcontact.OpenGidToChatIdRequest;
import com.github.shuaidd.resquest.externalcontact.SendSchoolNotificationRequest;
import com.github.shuaidd.resquest.externalcontact.ServedContactListRequest;
import com.github.shuaidd.resquest.externalcontact.TransferRequest;
import com.github.shuaidd.resquest.externalcontact.TransferResultRequest;
import com.github.shuaidd.resquest.externalcontact.UpdateContactWayRequest;
import com.github.shuaidd.resquest.externalcontact.UpdateCustomerAcquisitionLinkRequest;
import com.github.shuaidd.resquest.externalcontact.UpdateInterceptRuleRequest;
import com.github.shuaidd.resquest.externalcontact.UpdateProductAlbumRequest;
import com.github.shuaidd.resquest.externalcontact.UpdateRemarkRequest;
import com.github.shuaidd.resquest.externalcontact.UserBehaviorRequest;
import com.github.shuaidd.resquest.externalcontact.WelcomeMsgRequest;
import com.github.shuaidd.resquest.externalcontact.WelcomeTemplateRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 客户联系
 *
 * @author ddshuai
 * date 2022-08-31 22:20
 **/
public interface ExternalContactClient extends CommonClient {
    /**
     * 获取配置了客户联系功能的成员列表
     *
     * @param app 应用名
     * @return FollowUserResponse
     */
    @RequestLine("GET /externalcontact/get_follow_user_list?app={app}")
    @Headers(HEAD)
    FollowUserResponse getFollowUserList(@Param("app") String app);

    /**
     * 配置客户联系「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddContactWayResponse
     */
    @RequestLine("POST /externalcontact/add_contact_way?app={app}")
    @Headers(HEAD)
    AddContactWayResponse addContactWay(AddContactWayRequest request, @Param("app") String app);

    /**
     * 获取企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return ContactWayResponse
     */
    @RequestLine("POST /externalcontact/get_contact_way?app={app}")
    @Headers(HEAD)
    ContactWayResponse getContactWay(GetContactWayRequest request, @Param("app") String app);

    /**
     * 更新企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/update_contact_way?app={app}")
    @Headers(HEAD)
    BaseResponse updateContactWay(UpdateContactWayRequest request, @Param("app") String app);

    /**
     * 删除企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/del_contact_way?app={app}")
    @Headers(HEAD)
    BaseResponse deleteContactWay(GetContactWayRequest request, @Param("app") String app);

    /**
     * 配置客户群加入群聊方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatJoinWayCreateResponse
     */
    @RequestLine("POST /externalcontact/groupchat/add_join_way?app={app}")
    @Headers(HEAD)
    GroupChatJoinWayCreateResponse addGroupChatJoinWay(GroupChatJoinWayRequest request, @Param("app") String app);

    /**
     * 获取客户群加入群聊方式配置
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatJoinWayConfigResponse
     */
    @RequestLine("POST /externalcontact/groupchat/get_join_way?app={app}")
    @Headers(HEAD)
    GroupChatJoinWayConfigResponse getGroupChatJoinWay(GroupChatJoinWayConfigRequest request, @Param("app") String app);

    /**
     * 更新客户群加入群聊方式配置
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/groupchat/update_join_way?app={app}")
    @Headers(HEAD)
    BaseResponse updateGroupChatJoinWay(GroupChatJoinWayRequest request, @Param("app") String app);

    /**
     * 删除客户群加入群聊方式配置
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/groupchat/del_join_way?app={app}")
    @Headers(HEAD)
    BaseResponse deleteGroupChatJoinWay(GroupChatJoinWayConfigRequest request, @Param("app") String app);

    /**
     * 结束临时会话
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/close_temp_chat?app={app}")
    @Headers(HEAD)
    BaseResponse closeTempChat(CloseTempChatRequest request, @Param("app") String app);

    /**
     * 获取客户列表
     *
     * @param userId 用户编号
     * @param app    应用名
     * @return CustomListResponse
     */
    @RequestLine("GET /externalcontact/list?userid={userid}&app={app}")
    @Headers(HEAD)
    CustomListResponse getCustomList(@Param("userid") String userId, @Param("app") String app);

    /**
     * 获取客户详情
     *
     * @param externalUserId 客户编号
     * @param app            应用名
     * @return ExternalContactResponse
     */
    @RequestLine("GET /externalcontact/get?external_userid={external_userid}&app={app}")
    @Headers(HEAD)
    ExternalContactResponse getExternalContact(@Param("external_userid") String externalUserId, @Param("app") String app);

    /**
     * 批量获取客户详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return BatchExternalContactResponse
     */
    @RequestLine("POST /externalcontact/batch/get_by_user?app={app}")
    @Headers(HEAD)
    BatchExternalContactResponse getBatchExternalContact(BatchExternalContactRequest request, @Param("app") String app);

    /**
     * 修改客户备注信息
     *
     * @param remarkRequest 请求
     * @param app           应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/remark?app={app}")
    @Headers(HEAD)
    BaseResponse updateCustomRemark(UpdateRemarkRequest remarkRequest, @Param("app") String app);

    /**
     * 获取企业标签库
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagGroupResponse
     */
    @RequestLine("POST /externalcontact/get_corp_tag_list?app={app}")
    @Headers(HEAD)
    TagGroupResponse getCorpTagList(TagGroupRequest request, @Param("app") String app);

    /**
     * 添加企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddCorpTagResponse
     */
    @RequestLine("POST /externalcontact/add_corp_tag?app={app}")
    @Headers(HEAD)
    AddCorpTagResponse addCorpTag(AddCorpTagRequest request, @Param("app") String app);

    /**
     * 编辑企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/edit_corp_tag?app={app}")
    @Headers(HEAD)
    BaseResponse editCorpTag(EditCorpTagRequest request, @Param("app") String app);

    /**
     * 删除企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/del_corp_tag?app={app}")
    @Headers(HEAD)
    BaseResponse delCorpTag(DelCorpTagRequest request, @Param("app") String app);

    /**
     * 编辑客户企业标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/mark_tag?app={app}")
    @Headers(HEAD)
    BaseResponse markTag(MarkTagRequest request, @Param("app") String app);

    /**
     * 获取离职成员列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return UnassignedListResponse
     */
    @RequestLine("POST /externalcontact/get_unassigned_list?app={app}")
    @Headers(HEAD)
    UnassignedListResponse unassignedList(PageRequest request, @Param("app") String app);

    /**
     * 分配在职或离职成员的客户
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/transfer?app={app}")
    @Headers(HEAD)
    BaseResponse transfer(TransferRequest request, @Param("app") String app);

    /**
     * 查询客户接替结果
     *
     * @param resultRequest 请求
     * @param app           应用名
     * @return TransferResultResponse
     */
    @RequestLine("POST /externalcontact/get_transfer_result?app={app}")
    @Headers(HEAD)
    TransferResultResponse getTransferResult(TransferResultRequest resultRequest, @Param("app") String app);

    /**
     * 分配离职成员的客户群
     *
     * @param request 请求体
     * @param app     应用名
     * @return FailedChatResponse
     */
    @RequestLine("POST /externalcontact/groupchat/transfer?app={app}")
    @Headers(HEAD)
    FailedChatResponse groupChatTransfer(GroupChatTransferRequest request, @Param("app") String app);

    /**
     * 客户群 opengid 转换
     *
     * @param request 请求体
     * @param app     应用名
     * @return OpenGidToChatIdResponse
     */
    @RequestLine("POST /externalcontact/opengid_to_chatid?app={app}")
    @Headers(HEAD)
    OpenGidToChatIdResponse openGidToChatId(OpenGidToChatIdRequest request, @Param("app") String app);

    /**
     * 获取客户群列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatListResponse
     */
    @RequestLine("POST /externalcontact/groupchat/list?app={app}")
    @Headers(HEAD)
    GroupChatListResponse groupChatList(GroupChatListRequest request, @Param("app") String app);

    /**
     * 获取客户群详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatDetailResponse
     */
    @RequestLine("POST /externalcontact/groupchat/get?app={app}")
    @Headers(HEAD)
    GroupChatDetailResponse groupChatDetail(GroupChatDetailRequest request, @Param("app") String app);

    /**
     * 创建企业朋友圈发表任务
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentPublishCreateResponse
     */
    @RequestLine("POST /externalcontact/add_moment_task?app={app}")
    @Headers(HEAD)
    MomentPublishCreateResponse addMomentPublishTask(MomentPublishRequest request, @Param("app") String app);

    /**
     * 获取企业朋友圈发表任务创建结果
     *
     * @param jobId 异步任务 ID
     * @param app   应用名
     * @return MomentPublishTaskResultResponse
     */
    @RequestLine("GET /externalcontact/get_moment_task_result?jobid={jobid}&app={app}")
    @Headers(HEAD)
    MomentPublishTaskResultResponse getMomentPublishTaskResult(@Param("jobid") String jobId, @Param("app") String app);
    /**
     * 停止发表企业朋友圈
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/cancel_moment_task?app={app}")
    @Headers(HEAD)
    BaseResponse cancelMomentPublishTask(MomentPublishCancelRequest request, @Param("app") String app);
    /**
     * 获取企业全部的发表列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentListResponse
     */
    @RequestLine("POST /externalcontact/get_moment_list?app={app}")
    @Headers(HEAD)
    MomentListResponse getMomentList(MomentListRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈企业发表的列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentTaskResponse
     */
    @RequestLine("POST /externalcontact/get_moment_task?app={app}")
    @Headers(HEAD)
    MomentTaskResponse getMomentTask(MomentTaskRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈发表时选择的可见范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentCustomerListResponse
     */
    @RequestLine("POST /externalcontact/get_moment_customer_list?app={app}")
    @Headers(HEAD)
    MomentCustomerListResponse getMomentCustomerList(MomentCustomerListRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈发表后的可见客户列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentCustomerListResponse
     */
    @RequestLine("POST /externalcontact/get_moment_send_result?app={app}")
    @Headers(HEAD)
    MomentCustomerListResponse getMomentSendResult(MomentCustomerListRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈的互动数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CommentsResponse
     */
    @RequestLine("POST /externalcontact/get_moment_comments?app={app}")
    @Headers(HEAD)
    CommentsResponse getMomentComments(CommentsRequest request, @Param("app") String app);

    /**
     * 创建企业群发
     *
     * @param request 请求体
     * @param app     应用名
     * @return MsgTemplateResponse
     */
    @RequestLine("POST /externalcontact/add_msg_template?app={app}")
    @Headers(HEAD)
    MsgTemplateResponse addMsgTemplate(MsgTemplateRequest request, @Param("app") String app);

    /**
     * 获取群发记录列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgResponse
     */
    @RequestLine("POST /externalcontact/get_groupmsg_list?app={app}")
    @Headers(HEAD)
    GroupMsgResponse getGroupMsgList(GroupMsgRequest request, @Param("app") String app);

    /**
     * 获取群发记录列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgResponse
     */
    @RequestLine("POST /externalcontact/get_groupmsg_list_v2?app={app}")
    @Headers(HEAD)
    V2GroupMsgResponse getGroupMsgListV2(GroupMsgRequest request, @Param("app") String app);

    /**
     * 获取群发成员发送任务列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgTaskResponse
     */
    @RequestLine("POST /externalcontact/get_groupmsg_task?app={app}")
    @Headers(HEAD)
    GroupMsgTaskResponse getGroupMsgTask(GroupMsgTaskRequest request, @Param("app") String app);

    /**
     * 获取企业群发成员执行结果
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgSendResultResponse
     */
    @RequestLine("POST /externalcontact/get_groupmsg_send_result?app={app}")
    @Headers(HEAD)
    GroupMsgSendResultResponse getGroupMsgSendResult(GroupMsgSendRequest request, @Param("app") String app);

    /**
     * 提醒成员群发
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/remind_groupmsg_send?app={app}")
    @Headers(HEAD)
    BaseResponse remindGroupMsgSend(GroupMsgRemindRequest request, @Param("app") String app);

    /**
     * 停止企业群发
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/cancel_groupmsg_send?app={app}")
    @Headers(HEAD)
    BaseResponse cancelGroupMsgSend(GroupMsgCancelRequest request, @Param("app") String app);

    /**
     * 获取已服务的外部联系人
     *
     * @param request 请求体
     * @param app     应用名
     * @return ServedContactListResponse
     */
    @RequestLine("POST /externalcontact/contact_list?app={app}")
    @Headers(HEAD)
    ServedContactListResponse getServedContactList(ServedContactListRequest request, @Param("app") String app);

    /**
     * 发送新客户欢迎语
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/send_welcome_msg?app={app}")
    @Headers(HEAD)
    BaseResponse sendWelcomeMsg(WelcomeMsgRequest request, @Param("app") String app);

    /**
     * 添加入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddWelcomeTemplateResponse
     */
    @RequestLine("POST /externalcontact/group_welcome_template/add?app={app}")
    @Headers(HEAD)
    AddWelcomeTemplateResponse addGroupWelcomeTemplate(WelcomeTemplateRequest request, @Param("app") String app);

    /**
     * 编辑入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/group_welcome_template/edit?app={app}")
    @Headers(HEAD)
    BaseResponse editGroupWelcomeTemplate(WelcomeTemplateRequest request, @Param("app") String app);

    /**
     * 获取入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetWelcomeTemplateResponse
     */
    @RequestLine("POST /externalcontact/group_welcome_template/get?app={app}")
    @Headers(HEAD)
    GetWelcomeTemplateResponse getGroupWelcomeTemplate(GetWelcomeTemplateRequest request, @Param("app") String app);

    /**
     * 删除入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/group_welcome_template/del?app={app}")
    @Headers(HEAD)
    BaseResponse deleteGroupWelcomeTemplate(DeleteWelcomeTemplateRequest request, @Param("app") String app);

    /**
     * 获取「联系客户统计」数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BehaviorDataResponse
     */
    @RequestLine("POST /externalcontact/get_user_behavior_data?app={app}")
    @Headers(HEAD)
    BehaviorDataResponse getUserBehaviorData(UserBehaviorRequest request, @Param("app") String app);

    /**
     * 按群主聚合的方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatStatisticResponse
     */
    @RequestLine("POST /externalcontact/groupchat/statistic?app={app}")
    @Headers(HEAD)
    GroupChatStatisticResponse getGroupChatStatistic(GroupChatStatisticRequest request, @Param("app") String app);

    /**
     * 按自然日聚合的方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatStatisticResponse
     */
    @RequestLine("POST /externalcontact/groupchat/statistic_group_by_day?app={app}")
    @Headers(HEAD)
    GroupChatStatisticResponse getGroupChatStatisticByDay(GroupChatStatisticRequest request, @Param("app") String app);

    /**
     * 创建商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddProductAlbumResponse
     */
    @RequestLine("POST /externalcontact/add_product_album?app={app}")
    @Headers(HEAD)
    AddProductAlbumResponse addProductAlbum(AddProductAlbumRequest request, @Param("app") String app);

    /**
     * 获取商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetProductAlbumResponse
     */
    @RequestLine("POST /externalcontact/get_product_album?app={app}")
    @Headers(HEAD)
    GetProductAlbumResponse getProductAlbum(GetProductAlbumRequest request, @Param("app") String app);

    /**
     * 获取商品图册列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return ProductAlbumListResponse
     */
    @RequestLine("POST /externalcontact/get_product_album_list?app={app}")
    @Headers(HEAD)
    ProductAlbumListResponse productAlbumList(CursorPageRequest request, @Param("app") String app);

    /**
     * 编辑商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/get_product_album_list?app={app}")
    @Headers(HEAD)
    BaseResponse updateProductAlbum(UpdateProductAlbumRequest request, @Param("app") String app);

    /**
     * 删除商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/delete_product_album?app={app}")
    @Headers(HEAD)
    BaseResponse deleteProductAlbum(DelProductAlbumRequest request, @Param("app") String app);

    /**
     * 新建敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddInterceptRuleResponse
     */
    @RequestLine("POST /externalcontact/add_intercept_rule?app={app}")
    @Headers(HEAD)
    AddInterceptRuleResponse addInterceptRule(AddInterceptRuleRequest request, @Param("app") String app);

    /**
     * 获取敏感词规则列表
     *
     * @param app 应用名
     * @return InterceptRuleListResponse
     */
    @RequestLine("GET /externalcontact/get_intercept_rule_list?app={app}")
    @Headers(HEAD)
    InterceptRuleListResponse interceptRuleList(@Param("app") String app);

    /**
     * 获取敏感词规则详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetInterceptRuleResponse
     */
    @RequestLine("POST /externalcontact/get_intercept_rule?app={app}")
    @Headers(HEAD)
    GetInterceptRuleResponse getInterceptRuleDetail(InterceptRuleIdRequest request, @Param("app") String app);

    /**
     * 修改敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/update_intercept_rule?app={app}")
    @Headers(HEAD)
    BaseResponse updateInterceptRule(UpdateInterceptRuleRequest request, @Param("app") String app);

    /**
     * 删除敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/del_intercept_rule?app={app}")
    @Headers(HEAD)
    BaseResponse delInterceptRule(InterceptRuleIdRequest request, @Param("app") String app);
    /**
     * 发送「学校通知」
     *
     * @param request 请求体
     * @param app     应用名
     * @return SendSchoolNotificationResponse
     */
    @RequestLine("POST /externalcontact/message/send?app={app}")
    @Headers(HEAD)
    SendSchoolNotificationResponse sendSchoolNotification(SendSchoolNotificationRequest request, @Param("app") String app);

    /**
     * 获取客户联系规则组 ID 列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyListResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/list?app={app}")
    @Headers(HEAD)
    CustomerStrategyListResponse getCustomerStrategyList(CustomerStrategyListRequest request, @Param("app") String app);

    /**
     * 获取客户联系规则组详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/get?app={app}")
    @Headers(HEAD)
    CustomerStrategyResponse getCustomerStrategy(CustomerStrategyIdRequest request, @Param("app") String app);

    /**
     * 获取客户联系规则组管理范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyRangeResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/get_range?app={app}")
    @Headers(HEAD)
    CustomerStrategyRangeResponse getCustomerStrategyRange(CustomerStrategyRangeRequest request, @Param("app") String app);

    /**
     * 创建客户联系规则组
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyIdResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/create?app={app}")
    @Headers(HEAD)
    CustomerStrategyIdResponse createCustomerStrategy(CustomerStrategyCreateRequest request, @Param("app") String app);

    /**
     * 编辑客户联系规则组及其管理范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/edit?app={app}")
    @Headers(HEAD)
    BaseResponse editCustomerStrategy(CustomerStrategyEditRequest request, @Param("app") String app);

    /**
     * 删除客户联系规则组
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/customer_strategy/del?app={app}")
    @Headers(HEAD)
    BaseResponse deleteCustomerStrategy(CustomerStrategyIdRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈规则组 ID 列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentStrategyListResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/list?app={app}")
    @Headers(HEAD)
    MomentStrategyListResponse getMomentStrategyList(MomentStrategyListRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈规则组详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentStrategyResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/get?app={app}")
    @Headers(HEAD)
    MomentStrategyResponse getMomentStrategy(MomentStrategyIdRequest request, @Param("app") String app);

    /**
     * 获取客户朋友圈规则组管理范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentStrategyRangeResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/get_range?app={app}")
    @Headers(HEAD)
    MomentStrategyRangeResponse getMomentStrategyRange(MomentStrategyRangeRequest request, @Param("app") String app);

    /**
     * 创建客户朋友圈规则组；官方要求串行调用
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentStrategyIdResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/create?app={app}")
    @Headers(HEAD)
    MomentStrategyIdResponse createMomentStrategy(MomentStrategyCreateRequest request, @Param("app") String app);

    /**
     * 编辑客户朋友圈规则组及其管理范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/edit?app={app}")
    @Headers(HEAD)
    BaseResponse editMomentStrategy(MomentStrategyEditRequest request, @Param("app") String app);

    /**
     * 删除客户朋友圈规则组
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/moment_strategy/del?app={app}")
    @Headers(HEAD)
    BaseResponse deleteMomentStrategy(MomentStrategyIdRequest request, @Param("app") String app);

    /**
     * 获取获客链接 ID 列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerAcquisitionLinkListResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/list_link?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionLinkListResponse getCustomerAcquisitionLinkList(CustomerAcquisitionLinkListRequest request, @Param("app") String app);

    /**
     * 获取获客链接详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerAcquisitionLinkResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/get?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionLinkResponse getCustomerAcquisitionLink(CustomerAcquisitionLinkIdRequest request, @Param("app") String app);

    /**
     * 创建获客链接
     *
     * @param request 请求体
     * @param app     应用名
     * @return CreateCustomerAcquisitionLinkResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/create_link?app={app}")
    @Headers(HEAD)
    CreateCustomerAcquisitionLinkResponse createCustomerAcquisitionLink(CreateCustomerAcquisitionLinkRequest request, @Param("app") String app);

    /**
     * 编辑获客链接
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/update_link?app={app}")
    @Headers(HEAD)
    BaseResponse updateCustomerAcquisitionLink(UpdateCustomerAcquisitionLinkRequest request, @Param("app") String app);

    /**
     * 删除获客链接
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/delete_link?app={app}")
    @Headers(HEAD)
    BaseResponse deleteCustomerAcquisitionLink(CustomerAcquisitionLinkIdRequest request, @Param("app") String app);

    /**
     * 获取由获客链接添加的客户列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerAcquisitionCustomerListResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/customer?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionCustomerListResponse getCustomerAcquisitionCustomerList(CustomerAcquisitionCustomerListRequest request, @Param("app") String app);

    /**
     * 查询获客助手剩余使用量
     *
     * @param app 应用名
     * @return CustomerAcquisitionQuotaResponse
     */
    @RequestLine("GET /externalcontact/customer_acquisition_quota?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionQuotaResponse getCustomerAcquisitionQuota(@Param("app") String app);

    /**
     * 查询获客链接使用详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerAcquisitionStatisticResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/statistic?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionStatisticResponse getCustomerAcquisitionStatistic(CustomerAcquisitionStatisticRequest request, @Param("app") String app);

    /**
     * 获取成员多次收消息详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerAcquisitionChatInfoResponse
     */
    @RequestLine("POST /externalcontact/customer_acquisition/get_chat_info?app={app}")
    @Headers(HEAD)
    CustomerAcquisitionChatInfoResponse getCustomerAcquisitionChatInfo(CustomerAcquisitionChatKeyRequest request, @Param("app") String app);

    /**
     * 获取指定客户联系规则组下的企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyTagListResponse
     */
    @RequestLine("POST /externalcontact/get_strategy_tag_list?app={app}")
    @Headers(HEAD)
    CustomerStrategyTagListResponse getCustomerStrategyTagList(CustomerStrategyTagListRequest request, @Param("app") String app);

    /**
     * 为指定客户联系规则组创建企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return CustomerStrategyTagCreateResponse
     */
    @RequestLine("POST /externalcontact/add_strategy_tag?app={app}")
    @Headers(HEAD)
    CustomerStrategyTagCreateResponse createCustomerStrategyTag(CustomerStrategyTagCreateRequest request, @Param("app") String app);

    /**
     * 编辑指定客户联系规则组下的企业客户标签或标签组
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/edit_strategy_tag?app={app}")
    @Headers(HEAD)
    BaseResponse editCustomerStrategyTag(CustomerStrategyTagEditRequest request, @Param("app") String app);

    /**
     * 删除指定客户联系规则组下的企业客户标签或标签组
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @RequestLine("POST /externalcontact/del_strategy_tag?app={app}")
    @Headers(HEAD)
    BaseResponse deleteCustomerStrategyTag(CustomerStrategyTagDeleteRequest request, @Param("app") String app);
}
