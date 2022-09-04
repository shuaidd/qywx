package com.github.shuaidd.service;

import com.github.shuaidd.response.addressbook.MobileHashCodeResponse;
import com.github.shuaidd.response.externalcontact.*;
import com.github.shuaidd.resquest.PageRequest;
import com.github.shuaidd.resquest.addressbook.MobileHashCodeRequest;
import com.github.shuaidd.resquest.addressbook.TagGroupRequest;
import com.github.shuaidd.resquest.externalcontact.*;
import org.springframework.stereotype.Service;

/**
 * 描述 客户联系
 *
 * @author ddshuai
 * date 2021-01-02 09:47
 **/

@Service
public class CustomContactService extends AbstractBaseService {

    /* ---------企业服务人员管理---------- start */

    /**
     * 获取配置了客户联系功能的成员列表
     *
     * @param applicationName 应用名称
     * @return FollowUserResponse
     */
    public FollowUserResponse getFollowUserList(String applicationName) {
        return externalContactClient.getFollowUserList(applicationName);
    }

    /**
     * 配置客户联系「联系我」方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddContactWayResponse
     */
    public AddContactWayResponse addContactWay(AddContactWayRequest request, String applicationName) {
        return externalContactClient.addContactWay(request, applicationName);
    }

    /**
     * 获取企业已配置的「联系我」方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ContactWayResponse
     */
    public ContactWayResponse getContactWay(GetContactWayRequest request, String applicationName) {
        return externalContactClient.getContactWay(request, applicationName);
    }

    /**
     * 更新企业已配置的「联系我」方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateContactWay(UpdateContactWayRequest request, String applicationName) {
        externalContactClient.updateContactWay(request, applicationName);
    }

    /**
     * 删除企业已配置的「联系我」方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void deleteContactWay(GetContactWayRequest request, String applicationName) {
        externalContactClient.deleteContactWay(request, applicationName);
    }

    /**
     * 结束临时会话
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void closeTempChat(CloseTempChatRequest request, String applicationName) {
        externalContactClient.closeTempChat(request, applicationName);
    }

    /* ---------企业服务人员管理---------- end */

    /* ---------客户管理---------- start */

    /**
     * 获取客户列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomListResponse
     */
    public CustomListResponse getCustomList(CustomListRequest request, String applicationName) {
        return externalContactClient.getCustomList(request.getUserId(), applicationName);
    }

    /**
     * 获取客户详情
     *
     * @param externalUserId  外部联系人
     * @param applicationName 应用名称
     * @return ExternalContactResponse
     */
    public ExternalContactResponse getExternalContact(String externalUserId, String applicationName) {
        return externalContactClient.getExternalContact(externalUserId, applicationName);
    }

    /**
     * 批量获取客户详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BatchExternalContactResponse
     */
    public BatchExternalContactResponse getBatchExternalContact(BatchExternalContactRequest request, String applicationName) {
        return externalContactClient.getBatchExternalContact(request, applicationName);
    }

    /**
     * 修改客户备注信息
     *
     * @param remarkRequest   请求
     * @param applicationName 应用名称
     */
    public void updateCustomRemark(UpdateRemarkRequest remarkRequest, String applicationName) {
        externalContactClient.updateCustomRemark(remarkRequest, applicationName);
    }

    /**
     * 获取手机号随机串
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MobileHashCodeResponse
     */
    public MobileHashCodeResponse getMobileHashcode(MobileHashCodeRequest request, String applicationName) {
        return weChatClient.getMobileHashcode(request, applicationName);
    }
    /* ---------客户管理---------- end */

    /* ---------客户标签管理---------- start */

    /**
     * 获取企业标签库
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return TagGroupResponse
     */
    public TagGroupResponse getCorpTagList(TagGroupRequest request, String applicationName) {
        return externalContactClient.getCorpTagList(request, applicationName);
    }

    /**
     * 添加企业客户标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddCorpTagResponse
     */
    public AddCorpTagResponse addCorpTag(AddCorpTagRequest request, String applicationName) {
        return externalContactClient.addCorpTag(request, applicationName);
    }

    /**
     * 编辑企业客户标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void editCorpTag(EditCorpTagRequest request, String applicationName) {
        externalContactClient.editCorpTag(request, applicationName);
    }

    /**
     * 删除企业客户标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void delCorpTag(DelCorpTagRequest request, String applicationName) {
        externalContactClient.delCorpTag(request, applicationName);
    }

    /**
     * 编辑客户企业标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void markTag(MarkTagRequest request, String applicationName) {
        externalContactClient.markTag(request, applicationName);
    }
    /* ---------客户标签管理---------- end */

    /* ---------客户分配---------- start */

    /**
     * 获取离职成员列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return UnassignedListResponse
     */
    public UnassignedListResponse unassignedList(PageRequest request, String applicationName) {
        return externalContactClient.unassignedList(request, applicationName);
    }

    /**
     * 分配在职或离职成员的客户
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void transfer(TransferRequest request, String applicationName) {
        externalContactClient.transfer(request, applicationName);
    }

    /**
     * 查询客户接替结果
     *
     * @param resultRequest   请求
     * @param applicationName 应用名称
     * @return TransferResultResponse
     */
    public TransferResultResponse getTransferResult(TransferResultRequest resultRequest, String applicationName) {
        return externalContactClient.getTransferResult(resultRequest, applicationName);
    }

    /**
     * 分配离职成员的客户群
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return FailedChatResponse
     */
    public FailedChatResponse groupChatTransfer(GroupChatTransferRequest request, String applicationName) {
        return externalContactClient.groupChatTransfer(request, applicationName);
    }
    /* ---------客户分配---------- end */

    /* ---------客户群管理---------- start */

    /**
     * 获取客户群列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupChatListResponse
     */
    public GroupChatListResponse groupChatList(GroupChatListRequest request, String applicationName) {
        return externalContactClient.groupChatList(request, applicationName);
    }

    /**
     * 获取客户群详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupChatDetailResponse
     */
    public GroupChatDetailResponse groupChatDetail(GroupChatDetailRequest request, String applicationName) {
        return externalContactClient.groupChatDetail(request, applicationName);
    }
    /* ---------客户群管理---------- end */

    /**
     * 获取企业全部的发表列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MomentListResponse
     */
    public MomentListResponse getMomentList(MomentListRequest request, String applicationName) {
        return externalContactClient.getMomentList(request, applicationName);
    }

    /**
     * 获取客户朋友圈企业发表的列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MomentTaskResponse
     */
    public MomentTaskResponse getMomentTask(MomentTaskRequest request, String applicationName) {
        return externalContactClient.getMomentTask(request, applicationName);
    }

    /**
     * 获取客户朋友圈发表时选择的可见范围
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MomentCustomerListResponse
     */
    public MomentCustomerListResponse getMomentCustomerList(MomentCustomerListRequest request, String applicationName) {
        return externalContactClient.getMomentCustomerList(request, applicationName);
    }

    /**
     * 获取客户朋友圈发表后的可见客户列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MomentCustomerListResponse
     */
    public MomentCustomerListResponse getMomentSendResult(MomentCustomerListRequest request, String applicationName) {
        return externalContactClient.getMomentSendResult(request, applicationName);
    }

    /**
     * 获取客户朋友圈的互动数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CommentsResponse
     */
    public CommentsResponse getMomentComments(CommentsRequest request, String applicationName) {
        return externalContactClient.getMomentComments(request, applicationName);
    }

    /**
     * 创建企业群发
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return MsgTemplateResponse
     */
    public MsgTemplateResponse addMsgTemplate(MsgTemplateRequest request, String applicationName) {
        return externalContactClient.addMsgTemplate(request, applicationName);
    }

    /**
     * 获取群发记录列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupMsgResponse
     */
    public GroupMsgResponse getGroupMsgList(GroupMsgRequest request, String applicationName) {
        return externalContactClient.getGroupMsgList(request, applicationName);
    }

    /**
     * 获取群发记录列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupMsgResponse
     */
    public V2GroupMsgResponse getGroupMsgListV2(GroupMsgRequest request, String applicationName) {
        return externalContactClient.getGroupMsgListV2(request, applicationName);
    }

    /**
     * 获取群发成员发送任务列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupMsgTaskResponse
     */
    public GroupMsgTaskResponse getGroupMsgTask(GroupMsgTaskRequest request, String applicationName) {
        return externalContactClient.getGroupMsgTask(request, applicationName);
    }

    /**
     * 获取企业群发成员执行结果
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupMsgSendResultResponse
     */
    public GroupMsgSendResultResponse getGroupMsgSendResult(GroupMsgSendRequest request, String applicationName) {
        return externalContactClient.getGroupMsgSendResult(request, applicationName);
    }

    /**
     * 发送新客户欢迎语
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void sendWelcomeMsg(WelcomeMsgRequest request, String applicationName) {
        externalContactClient.sendWelcomeMsg(request, applicationName);
    }

    /**
     * 添加入群欢迎语素材
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddWelcomeTemplateResponse
     */
    public AddWelcomeTemplateResponse addGroupWelcomeTemplate(WelcomeTemplateRequest request, String applicationName) {
        return externalContactClient.addGroupWelcomeTemplate(request, applicationName);
    }

    /**
     * 编辑入群欢迎语素材
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void editGroupWelcomeTemplate(WelcomeTemplateRequest request, String applicationName) {
        externalContactClient.editGroupWelcomeTemplate(request, applicationName);
    }

    /**
     * 获取入群欢迎语素材
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GetWelcomeTemplateResponse
     */
    public GetWelcomeTemplateResponse getGroupWelcomeTemplate(GetWelcomeTemplateRequest request, String applicationName) {
        return externalContactClient.getGroupWelcomeTemplate(request, applicationName);
    }

    /**
     * 删除入群欢迎语素材
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void deleteGroupWelcomeTemplate(DeleteWelcomeTemplateRequest request, String applicationName) {
        externalContactClient.deleteGroupWelcomeTemplate(request, applicationName);
    }

    /**
     * 获取「联系客户统计」数据
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BehaviorDataResponse
     */
    public BehaviorDataResponse getUserBehaviorData(UserBehaviorRequest request, String applicationName) {
        return externalContactClient.getUserBehaviorData(request, applicationName);
    }

    /**
     * 按群主聚合的方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupChatStatisticResponse
     */
    public GroupChatStatisticResponse getGroupChatStatistic(GroupChatStatisticRequest request, String applicationName) {
        return externalContactClient.getGroupChatStatistic(request, applicationName);
    }

    /**
     * 按自然日聚合的方式
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GroupChatStatisticResponse
     */
    public GroupChatStatisticResponse getGroupChatStatisticByDay(GroupChatStatisticRequest request, String applicationName) {
        return externalContactClient.getGroupChatStatisticByDay(request, applicationName);
    }
}
