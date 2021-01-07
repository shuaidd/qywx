package com.github.shuaidd.service;

import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 客户联系
 *
 * @author ddshuai
 * @date 2021-01-02 09:47
 **/

@Service
public class CustomContactService extends AbstractBaseService {

    /* ---------企业服务人员管理---------- start */

    /**
     * 获取配置了客户联系功能的成员列表
     */
    public FollowUserResponse getFollowUserList(String applicationName) {
        FollowUserResponse response = weChatClient.getFollowUserList(applicationName);
        return getResponse(response);
    }

    /**
     * 配置客户联系「联系我」方式
     *
     * @param request
     * @param applicationName
     * @return AddContactWayResponse
     */
    public AddContactWayResponse addContactWay(AddContactWayRequest request, String applicationName) {
        AddContactWayResponse response = weChatClient.addContactWay(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取企业已配置的「联系我」方式
     *
     * @param request
     * @param applicationName
     * @return ContactWayResponse
     */
    public ContactWayResponse getContactWay(GetContactWayRequest request, String applicationName) {
        ContactWayResponse contactWayResponse = weChatClient.getContactWay(request, applicationName);
        return getResponse(contactWayResponse);
    }

    /**
     * 更新企业已配置的「联系我」方式
     *
     * @param request
     * @param applicationName
     */
    public void updateContactWay(UpdateContactWayRequest request, String applicationName) {
        BaseResponse response = weChatClient.updateContactWay(request, applicationName);
        getResponse(response);
    }

    /**
     * 删除企业已配置的「联系我」方式
     *
     * @param request
     * @param applicationName
     */
    public void deleteContactWay(GetContactWayRequest request, String applicationName) {
        BaseResponse response = weChatClient.deleteContactWay(request, applicationName);
        getResponse(response);
    }

    /**
     * 结束临时会话
     *
     * @param request
     * @param applicationName
     */
    public void closeTempChat(CloseTempChatRequest request, String applicationName) {
        BaseResponse response = weChatClient.closeTempChat(request, applicationName);
        getResponse(response);
    }
    /* ---------企业服务人员管理---------- end */

    /* ---------客户管理---------- start */

    /**
     * 获取客户列表
     *
     * @param request
     * @param applicationName
     * @return CustomListResponse
     */
    public CustomListResponse getCustomList(CustomListRequest request, String applicationName) {
        CustomListResponse response = weChatClient.getCustomList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户详情
     *
     * @param externalUserId
     * @param applicationName
     * @return ExternalContactResponse
     */
    public ExternalContactResponse getExternalContact(String externalUserId, String applicationName) {
        ExternalContactResponse externalContactResponse = weChatClient.getExternalContact(externalUserId, applicationName);
        return getResponse(externalContactResponse);
    }

    /**
     * 批量获取客户详情
     *
     * @param request
     * @param applicationName
     * @return BatchExternalContactResponse
     */
    public BatchExternalContactResponse getBatchExternalContact(BatchExternalContactRequest request, String applicationName) {
        BatchExternalContactResponse response = weChatClient.getBatchExternalContact(request, applicationName);
        return getResponse(response);
    }

    /**
     * 修改客户备注信息
     *
     * @param remarkRequest
     * @param applicationName
     */
    public void updateCustomRemark(UpdateRemarkRequest remarkRequest, String applicationName) {
        BaseResponse response = weChatClient.updateCustomRemark(remarkRequest, applicationName);
        getResponse(response);
    }

    /**
     * 获取手机号随机串
     *
     * @param request
     * @param applicationName
     * @return MobileHashCodeResponse
     */
    public MobileHashCodeResponse getMobileHashcode(MobileHashCodeRequest request, String applicationName) {
        MobileHashCodeResponse response = weChatClient.getMobileHashcode(request, applicationName);
        return getResponse(response);
    }
    /* ---------客户管理---------- end */

    /* ---------客户标签管理---------- start */

    /**
     * 获取企业标签库
     *
     * @param request
     * @param applicationName
     * @return TagGroupResponse
     */
    public TagGroupResponse getCorpTagList(TagGroupRequest request, String applicationName) {
        TagGroupResponse response = weChatClient.getCorpTagList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 添加企业客户标签
     *
     * @param request
     * @param applicationName
     * @return AddCorpTagResponse
     */
    public AddCorpTagResponse addCorpTag(AddCorpTagRequest request, String applicationName) {
        AddCorpTagResponse response = weChatClient.addCorpTag(request, applicationName);
        return getResponse(response);
    }

    /**
     * 编辑企业客户标签
     *
     * @param request
     * @param applicationName
     */
    public void editCorpTag(EditCorpTagRequest request, String applicationName) {
        BaseResponse response = weChatClient.editCorpTag(request, applicationName);
        getResponse(response);
    }

    /**
     * 删除企业客户标签
     *
     * @param request
     * @param applicationName
     */
    public void delCorpTag(DelCorpTagRequest request, String applicationName) {
        BaseResponse response = weChatClient.delCorpTag(request, applicationName);
        getResponse(response);
    }

    /**
     * 编辑客户企业标签
     *
     * @param request
     * @param applicationName
     */
    public void markTag(MarkTagRequest request, String applicationName) {
        BaseResponse response = weChatClient.markTag(request, applicationName);
        getResponse(response);
    }
    /* ---------客户标签管理---------- end */

    /* ---------客户分配---------- start */

    /**
     * 获取离职成员列表
     *
     * @param request
     * @param applicationName
     * @return UnassignedListResponse
     */
    public UnassignedListResponse unassignedList(PageRequest request, String applicationName) {
        UnassignedListResponse response = weChatClient.unassignedList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 分配在职或离职成员的客户
     *
     * @param request
     * @param applicationName
     */
    public void transfer(TransferRequest request, String applicationName) {
        BaseResponse response = weChatClient.transfer(request, applicationName);
        getResponse(response);
    }

    /**
     * 查询客户接替结果
     *
     * @param resultRequest
     * @param applicationName
     * @return TransferResultResponse
     */
    public TransferResultResponse getTransferResult(TransferResultRequest resultRequest, String applicationName) {
        TransferResultResponse resultResponse = weChatClient.getTransferResult(resultRequest, applicationName);
        return getResponse(resultResponse);
    }

    /**
     * 分配离职成员的客户群
     *
     * @param request
     * @param applicationName
     * @return FailedChatResponse
     */
    public FailedChatResponse groupChatTransfer(GroupChatTransferRequest request, String applicationName) {
        FailedChatResponse response = weChatClient.groupChatTransfer(request, applicationName);
        return getResponse(response);
    }
    /* ---------客户分配---------- end */

    /* ---------客户群管理---------- start */

    /**
     * 获取客户群列表
     *
     * @param request
     * @param applicationName
     * @return GroupChatListResponse
     */
    public GroupChatListResponse groupChatList(GroupChatListRequest request, String applicationName) {
        GroupChatListResponse response = weChatClient.groupChatList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户群详情
     *
     * @param request
     * @param applicationName
     * @return GroupChatDetailResponse
     */
    public GroupChatDetailResponse groupChatDetail(GroupChatDetailRequest request, String applicationName) {
        GroupChatDetailResponse response = weChatClient.groupChatDetail(request, applicationName);
        return getResponse(response);
    }
    /* ---------客户群管理---------- end */

    /**
     * 获取企业全部的发表列表
     *
     * @param request
     * @param applicationName
     * @return MomentListResponse
     */
    public MomentListResponse getMomentList(MomentListRequest request, String applicationName) {
        MomentListResponse response = weChatClient.getMomentList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户朋友圈企业发表的列表
     *
     * @param request
     * @param applicationName
     * @return MomentTaskResponse
     */
    public MomentTaskResponse getMomentTask(MomentTaskRequest request, String applicationName) {
        MomentTaskResponse response = weChatClient.getMomentTask(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户朋友圈发表时选择的可见范围
     *
     * @param request
     * @param applicationName
     * @return MomentCustomerListResponse
     */
    public MomentCustomerListResponse getMomentCustomerList(MomentCustomerListRequest request, String applicationName) {
        MomentCustomerListResponse response = weChatClient.getMomentCustomerList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户朋友圈发表后的可见客户列表
     *
     * @param request
     * @param applicationName
     * @return MomentCustomerListResponse
     */
    public MomentCustomerListResponse getMomentSendResult(MomentCustomerListRequest request, String applicationName) {
        MomentCustomerListResponse response = weChatClient.getMomentSendResult(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取客户朋友圈的互动数据
     *
     * @param request
     * @param applicationName
     * @return CommentsResponse
     */
    public CommentsResponse getMomentComments(CommentsRequest request, String applicationName) {
        CommentsResponse response = weChatClient.getMomentComments(request, applicationName);
        return getResponse(response);
    }

    /**
     * 创建企业群发
     *
     * @param request
     * @param applicationName
     * @return MsgTemplateResponse
     */
    public MsgTemplateResponse addMsgTemplate(MsgTemplateRequest request, String applicationName) {
        MsgTemplateResponse response = weChatClient.addMsgTemplate(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取群发记录列表
     *
     * @param request
     * @param applicationName
     * @return GroupMsgResponse
     */
    public GroupMsgResponse getGroupMsgList(GroupMsgRequest request, String applicationName) {
        GroupMsgResponse response = weChatClient.getGroupMsgList(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取群发成员发送任务列表
     *
     * @param request
     * @param applicationName
     * @return GroupMsgTaskResponse
     */
    public GroupMsgTaskResponse getGroupMsgTask(GroupMsgTaskRequest request, String applicationName) {
        GroupMsgTaskResponse response = weChatClient.getGroupMsgTask(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取企业群发成员执行结果
     *
     * @param request
     * @param applicationName
     * @return GroupMsgSendResultResponse
     */
    GroupMsgSendResultResponse getGroupMsgSendResult(GroupMsgSendRequest request, String applicationName) {
        GroupMsgSendResultResponse response = weChatClient.getGroupMsgSendResult(request, applicationName);
        return getResponse(response);
    }

    /**
     * 发送新客户欢迎语
     *
     * @param request
     * @param applicationName
     * @return BaseResponse
     */
    BaseResponse sendWelcomeMsg(WelcomeMsgRequest request, String applicationName) {
        BaseResponse response = weChatClient.sendWelcomeMsg(request, applicationName);
        return getResponse(response);
    }

    /**
     * 添加入群欢迎语素材
     *
     * @param request
     * @param applicationName
     * @return AddWelcomeTemplateResponse
     */
    AddWelcomeTemplateResponse addGroupWelcomeTemplate(WelcomeTemplateRequest request, String applicationName) {
        AddWelcomeTemplateResponse response = weChatClient.addGroupWelcomeTemplate(request, applicationName);
        return getResponse(response);
    }

    /**
     * 编辑入群欢迎语素材
     *
     * @param request
     * @param applicationName
     * @return BaseResponse
     */
    BaseResponse editGroupWelcomeTemplate(WelcomeTemplateRequest request, String applicationName) {
        BaseResponse response = weChatClient.editGroupWelcomeTemplate(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取入群欢迎语素材
     *
     * @param request
     * @param applicationName
     * @return GetWelcomeTemplateResponse
     */
    GetWelcomeTemplateResponse getGroupWelcomeTemplate(GetWelcomeTemplateRequest request, String applicationName) {
        GetWelcomeTemplateResponse response = weChatClient.getGroupWelcomeTemplate(request, applicationName);
        return getResponse(response);
    }

    /**
     * 删除入群欢迎语素材
     *
     * @param request
     * @param applicationName
     * @return BaseResponse
     */
    BaseResponse deleteGroupWelcomeTemplate(DeleteWelcomeTemplateResponse request, String applicationName) {
        BaseResponse response = weChatClient.deleteGroupWelcomeTemplate(request, applicationName);
        return getResponse(response);
    }

    /**
     * 获取「联系客户统计」数据
     *
     * @param request
     * @param applicationName
     * @return BehaviorDataResponse
     */
    BehaviorDataResponse getUserBehaviorData(UserBehaviorRequest request, String applicationName) {
        BehaviorDataResponse response = weChatClient.getUserBehaviorData(request, applicationName);
        return getResponse(response);
    }

    /**
     * 按群主聚合的方式
     *
     * @param request
     * @param applicationName
     * @return GroupChatStatisticResponse
     */
    GroupChatStatisticResponse getGroupChatStatistic(GroupChatStatisticRequest request, String applicationName) {
        GroupChatStatisticResponse response = weChatClient.getGroupChatStatistic(request, applicationName);
        return getResponse(response);
    }

    /**
     * 按自然日聚合的方式
     *
     * @param request
     * @param applicationName
     * @return GroupChatStatisticResponse
     */
    GroupChatStatisticResponse getGroupChatStatisticByDay(GroupChatStatisticRequest request, String applicationName) {
        GroupChatStatisticResponse response = weChatClient.getGroupChatStatisticByDay(request, applicationName);
        return getResponse(response);
    }
}
