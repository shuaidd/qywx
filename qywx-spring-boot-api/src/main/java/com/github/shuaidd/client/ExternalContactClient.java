package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.externalcontact.*;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.PageRequest;
import com.github.shuaidd.resquest.addressbook.TagGroupRequest;
import com.github.shuaidd.resquest.externalcontact.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 客户联系
 *
 * @author ddshuai
 * date 2022-08-31 22:20
 **/
@FeignClient(name = "ExternalContact", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface ExternalContactClient extends CommonClient {
    /**
     * 获取配置了客户联系功能的成员列表
     *
     * @param app 应用名
     * @return FollowUserResponse
     */
    @GetMapping(value = "externalcontact/get_follow_user_list", headers = HEAD)
    FollowUserResponse getFollowUserList(@RequestParam(HEAD_KEY) String app);

    /**
     * 配置客户联系「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddContactWayResponse
     */
    @PostMapping(value = "externalcontact/add_contact_way", headers = HEAD)
    AddContactWayResponse addContactWay(AddContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return ContactWayResponse
     */
    @PostMapping(value = "externalcontact/get_contact_way", headers = HEAD)
    ContactWayResponse getContactWay(GetContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/update_contact_way", headers = HEAD)
    BaseResponse updateContactWay(UpdateContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除企业已配置的「联系我」方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/del_contact_way", headers = HEAD)
    BaseResponse deleteContactWay(GetContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 结束临时会话
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/close_temp_chat", headers = HEAD)
    BaseResponse closeTempChat(CloseTempChatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户列表
     *
     * @param userId 用户编号
     * @param app    应用名
     * @return CustomListResponse
     */
    @GetMapping(value = "externalcontact/list", headers = HEAD)
    CustomListResponse getCustomList(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户详情
     *
     * @param externalUserId 客户编号
     * @param app            应用名
     * @return ExternalContactResponse
     */
    @GetMapping(value = "externalcontact/get", headers = HEAD)
    ExternalContactResponse getExternalContact(@RequestParam("external_userid") String externalUserId, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量获取客户详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return BatchExternalContactResponse
     */
    @PostMapping(value = "externalcontact/batch/get_by_user", headers = HEAD)
    BatchExternalContactResponse getBatchExternalContact(BatchExternalContactRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改客户备注信息
     *
     * @param remarkRequest 请求
     * @param app           应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/remark", headers = HEAD)
    BaseResponse updateCustomRemark(UpdateRemarkRequest remarkRequest, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业标签库
     *
     * @param request 请求体
     * @param app     应用名
     * @return TagGroupResponse
     */
    @PostMapping(value = "externalcontact/get_corp_tag_list", headers = HEAD)
    TagGroupResponse getCorpTagList(TagGroupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddCorpTagResponse
     */
    @PostMapping(value = "externalcontact/add_corp_tag", headers = HEAD)
    AddCorpTagResponse addCorpTag(AddCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/edit_corp_tag", headers = HEAD)
    BaseResponse editCorpTag(EditCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除企业客户标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/del_corp_tag", headers = HEAD)
    BaseResponse delCorpTag(DelCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑客户企业标签
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/mark_tag", headers = HEAD)
    BaseResponse markTag(MarkTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取离职成员列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return UnassignedListResponse
     */
    @PostMapping(value = "externalcontact/get_unassigned_list", headers = HEAD)
    UnassignedListResponse unassignedList(PageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 分配在职或离职成员的客户
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/transfer", headers = HEAD)
    BaseResponse transfer(TransferRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询客户接替结果
     *
     * @param resultRequest 请求
     * @param app           应用名
     * @return TransferResultResponse
     */
    @PostMapping(value = "externalcontact/get_transfer_result", headers = HEAD)
    TransferResultResponse getTransferResult(TransferResultRequest resultRequest, @RequestParam(HEAD_KEY) String app);

    /**
     * 分配离职成员的客户群
     *
     * @param request 请求体
     * @param app     应用名
     * @return FailedChatResponse
     */
    @PostMapping(value = "externalcontact/groupchat/transfer", headers = HEAD)
    FailedChatResponse groupChatTransfer(GroupChatTransferRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户群列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatListResponse
     */
    @PostMapping(value = "externalcontact/groupchat/list", headers = HEAD)
    GroupChatListResponse groupChatList(GroupChatListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户群详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatDetailResponse
     */
    @PostMapping(value = "externalcontact/groupchat/get", headers = HEAD)
    GroupChatDetailResponse groupChatDetail(GroupChatDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业全部的发表列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentListResponse
     */
    @PostMapping(value = "externalcontact/get_moment_list", headers = HEAD)
    MomentListResponse getMomentList(MomentListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈企业发表的列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentTaskResponse
     */
    @PostMapping(value = "externalcontact/get_moment_task", headers = HEAD)
    MomentTaskResponse getMomentTask(MomentTaskRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈发表时选择的可见范围
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentCustomerListResponse
     */
    @PostMapping(value = "externalcontact/get_moment_customer_list", headers = HEAD)
    MomentCustomerListResponse getMomentCustomerList(MomentCustomerListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈发表后的可见客户列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return MomentCustomerListResponse
     */
    @PostMapping(value = "externalcontact/get_moment_send_result", headers = HEAD)
    MomentCustomerListResponse getMomentSendResult(MomentCustomerListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈的互动数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return CommentsResponse
     */
    @PostMapping(value = "externalcontact/get_moment_comments", headers = HEAD)
    CommentsResponse getMomentComments(CommentsRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建企业群发
     *
     * @param request 请求体
     * @param app     应用名
     * @return MsgTemplateResponse
     */
    @PostMapping(value = "externalcontact/add_msg_template", headers = HEAD)
    MsgTemplateResponse addMsgTemplate(MsgTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取群发记录列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_list", headers = HEAD)
    GroupMsgResponse getGroupMsgList(GroupMsgRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取群发记录列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_list_v2", headers = HEAD)
    V2GroupMsgResponse getGroupMsgListV2(GroupMsgRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取群发成员发送任务列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgTaskResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_task", headers = HEAD)
    GroupMsgTaskResponse getGroupMsgTask(GroupMsgTaskRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业群发成员执行结果
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupMsgSendResultResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_send_result", headers = HEAD)
    GroupMsgSendResultResponse getGroupMsgSendResult(GroupMsgSendRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 发送新客户欢迎语
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/send_welcome_msg", headers = HEAD)
    BaseResponse sendWelcomeMsg(WelcomeMsgRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddWelcomeTemplateResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/add", headers = HEAD)
    AddWelcomeTemplateResponse addGroupWelcomeTemplate(WelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/edit", headers = HEAD)
    BaseResponse editGroupWelcomeTemplate(WelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetWelcomeTemplateResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/get", headers = HEAD)
    GetWelcomeTemplateResponse getGroupWelcomeTemplate(GetWelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除入群欢迎语素材
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/del", headers = HEAD)
    BaseResponse deleteGroupWelcomeTemplate(DeleteWelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取「联系客户统计」数据
     *
     * @param request 请求体
     * @param app     应用名
     * @return BehaviorDataResponse
     */
    @PostMapping(value = "externalcontact/get_user_behavior_data", headers = HEAD)
    BehaviorDataResponse getUserBehaviorData(UserBehaviorRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 按群主聚合的方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatStatisticResponse
     */
    @PostMapping(value = "externalcontact/groupchat/statistic", headers = HEAD)
    GroupChatStatisticResponse getGroupChatStatistic(GroupChatStatisticRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 按自然日聚合的方式
     *
     * @param request 请求体
     * @param app     应用名
     * @return GroupChatStatisticResponse
     */
    @PostMapping(value = "externalcontact/groupchat/statistic_group_by_day", headers = HEAD)
    GroupChatStatisticResponse getGroupChatStatisticByDay(GroupChatStatisticRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddProductAlbumResponse
     */
    @PostMapping(value = "externalcontact/add_product_album", headers = HEAD)
    AddProductAlbumResponse addProductAlbum(AddProductAlbumRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetProductAlbumResponse
     */
    @PostMapping(value = "externalcontact/get_product_album", headers = HEAD)
    GetProductAlbumResponse getProductAlbum(GetProductAlbumRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取商品图册列表
     *
     * @param request 请求体
     * @param app     应用名
     * @return ProductAlbumListResponse
     */
    @PostMapping(value = "externalcontact/get_product_album_list", headers = HEAD)
    ProductAlbumListResponse productAlbumList(CursorPageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/get_product_album_list", headers = HEAD)
    BaseResponse updateProductAlbum(UpdateProductAlbumRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除商品图册
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/delete_product_album", headers = HEAD)
    BaseResponse deleteProductAlbum(DelProductAlbumRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 新建敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return AddInterceptRuleResponse
     */
    @PostMapping(value = "externalcontact/add_intercept_rule", headers = HEAD)
    AddInterceptRuleResponse addInterceptRule(AddInterceptRuleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取敏感词规则列表
     *
     * @param app 应用名
     * @return InterceptRuleListResponse
     */
    @GetMapping(value = "externalcontact/get_intercept_rule_list", headers = HEAD)
    InterceptRuleListResponse interceptRuleList(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取敏感词规则详情
     *
     * @param request 请求体
     * @param app     应用名
     * @return GetInterceptRuleResponse
     */
    @PostMapping(value = "externalcontact/get_intercept_rule", headers = HEAD)
    GetInterceptRuleResponse getInterceptRuleDetail(InterceptRuleIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/update_intercept_rule", headers = HEAD)
    BaseResponse updateInterceptRule(UpdateInterceptRuleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除敏感词规则
     *
     * @param request 请求体
     * @param app     应用名
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/del_intercept_rule", headers = HEAD)
    BaseResponse delInterceptRule(InterceptRuleIdRequest request, @RequestParam(HEAD_KEY) String app);
}
