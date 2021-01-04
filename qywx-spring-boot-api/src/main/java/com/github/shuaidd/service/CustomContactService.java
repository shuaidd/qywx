package com.github.shuaidd.service;

import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.springframework.stereotype.Service;

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
     * @param request
     * @param applicationName
     * @return FailedChatResponse
     */
    public FailedChatResponse groupChatTransfer(GroupChatTransferRequest request, String applicationName) {
        FailedChatResponse response = weChatClient.groupChatTransfer(request, applicationName);
        return getResponse(response);
    }
    /* ---------客户分配---------- end */
}
