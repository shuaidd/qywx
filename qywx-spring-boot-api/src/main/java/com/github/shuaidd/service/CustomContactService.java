package com.github.shuaidd.service;

import com.github.shuaidd.client.ExternalContactClient;
import com.github.shuaidd.dto.externalcontact.CustomerStrategyRange;
import com.github.shuaidd.dto.externalcontact.CustomerStrategyTag;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.addressbook.MobileHashCodeResponse;
import com.github.shuaidd.response.externalcontact.*;
import com.github.shuaidd.resquest.CursorPageRequest;
import com.github.shuaidd.resquest.PageRequest;
import com.github.shuaidd.resquest.addressbook.MobileHashCodeRequest;
import com.github.shuaidd.resquest.addressbook.TagGroupRequest;
import com.github.shuaidd.resquest.externalcontact.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * 描述 客户联系
 *
 * @author ddshuai
 * date 2021-01-02 09:47
 **/

@Service
public class CustomContactService extends AbstractBaseService {

    private final ExternalContactClient externalContactClient;

    public CustomContactService(ExternalContactClient externalContactClient) {
        this.externalContactClient = externalContactClient;
    }

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

    /**
     * 创建商品图册
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddProductAlbumResponse
     */
    public AddProductAlbumResponse addProductAlbum(AddProductAlbumRequest request, String applicationName) {
        return externalContactClient.addProductAlbum(request, applicationName);
    }

    /**
     * 获取商品图册
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GetProductAlbumResponse
     */
    public GetProductAlbumResponse getProductAlbum(GetProductAlbumRequest request, String applicationName) {
        return externalContactClient.getProductAlbum(request, applicationName);
    }

    /**
     * 获取商品图册列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return ProductAlbumListResponse
     */
    public ProductAlbumListResponse productAlbumList(CursorPageRequest request, String applicationName) {
        return externalContactClient.productAlbumList(request, applicationName);
    }

    /**
     * 更新商品图册
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateProductAlbum(UpdateProductAlbumRequest request, String applicationName) {
        externalContactClient.updateProductAlbum(request, applicationName);
    }

    /**
     * 删除商品图册
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void deleteProductAlbum(DelProductAlbumRequest request, String applicationName) {
        externalContactClient.deleteProductAlbum(request, applicationName);
    }

    /**
     * 新建敏感词规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return AddInterceptRuleResponse
     */
    public AddInterceptRuleResponse addInterceptRule(AddInterceptRuleRequest request, String applicationName) {
        return externalContactClient.addInterceptRule(request, applicationName);
    }

    /**
     * 获取敏感词规则列表
     *
     * @param applicationName 应用名称
     * @return InterceptRuleListResponse
     */
    public InterceptRuleListResponse interceptRuleList(String applicationName) {
        return externalContactClient.interceptRuleList(applicationName);
    }

    /**
     * 新建敏感词规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return GetInterceptRuleResponse
     */
    public GetInterceptRuleResponse getInterceptRuleDetail(InterceptRuleIdRequest request, String applicationName) {
        return externalContactClient.getInterceptRuleDetail(request, applicationName);
    }

    /**
     * 修改敏感词规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void updateInterceptRule(UpdateInterceptRuleRequest request, String applicationName) {
        externalContactClient.updateInterceptRule(request, applicationName);
    }

    /**
     * 删除敏感词规则
     *
     * @param request         请求
     * @param applicationName 应用名称
     */
    public void delInterceptRule(InterceptRuleIdRequest request, String applicationName) {
        externalContactClient.delInterceptRule(request, applicationName);
    }

    /**
     * 发送「学校通知」
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return SendSchoolNotificationResponse
     */
    public SendSchoolNotificationResponse sendSchoolNotification(SendSchoolNotificationRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getAgentid()) || Objects.isNull(request.getMsgType())) {
            throw new ParamCheckException("agentid、msgtype 不能为空，请检查！！！");
        }
        return externalContactClient.sendSchoolNotification(request, applicationName);
    }

    /* ---------客户联系规则组管理---------- start */

    /**
     * 获取客户联系规则组 ID 列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyListResponse
     */
    public CustomerStrategyListResponse getCustomerStrategyList(CustomerStrategyListRequest request, String applicationName) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("request 不能为空，请检查！！！");
        }
        checkCustomerStrategyLimit(request.getLimit(), request);
        return externalContactClient.getCustomerStrategyList(request, applicationName);
    }

    /**
     * 获取客户联系规则组详情
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyResponse
     */
    public CustomerStrategyResponse getCustomerStrategy(CustomerStrategyIdRequest request, String applicationName) {
        checkCustomerStrategyId(request);
        return externalContactClient.getCustomerStrategy(request, applicationName);
    }

    /**
     * 获取客户联系规则组管理范围
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyRangeResponse
     */
    public CustomerStrategyRangeResponse getCustomerStrategyRange(CustomerStrategyRangeRequest request, String applicationName) {
        checkCustomerStrategyId(request);
        checkCustomerStrategyLimit(request.getLimit(), request);
        return externalContactClient.getCustomerStrategyRange(request, applicationName);
    }

    /**
     * 创建客户联系规则组；官方要求串行调用，请勿并发创建
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyIdResponse
     */
    public CustomerStrategyIdResponse createCustomerStrategy(CustomerStrategyCreateRequest request, String applicationName) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getStrategyName())
                || CollectionUtils.isEmpty(request.getAdminList()) || CollectionUtils.isEmpty(request.getRange())) {
            throw new ParamCheckException("strategyName、adminList、range 不能为空，请检查！！！");
        }
        checkCustomerStrategyAdminList(request.getAdminList());
        checkCustomerStrategyRange(request.getRange());
        return externalContactClient.createCustomerStrategy(request, applicationName);
    }

    /**
     * 编辑客户联系规则组及其管理范围；官方要求串行调用，请勿并发编辑
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse editCustomerStrategy(CustomerStrategyEditRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getStrategyId())) {
            throw new ParamCheckException("strategyId 不能为空，请检查！！！");
        }
        checkCustomerStrategyAdminList(request.getAdminList());
        checkCustomerStrategyRange(request.getRangeAdd());
        checkCustomerStrategyRange(request.getRangeDel());
        return externalContactClient.editCustomerStrategy(request, applicationName);
    }

    /**
     * 删除客户联系规则组
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse deleteCustomerStrategy(CustomerStrategyIdRequest request, String applicationName) {
        checkCustomerStrategyId(request);
        return externalContactClient.deleteCustomerStrategy(request, applicationName);
    }

    /* ---------客户联系规则组管理---------- end */

    /* ---------客户联系规则组企业客户标签管理---------- start */

    /**
     * 获取指定客户联系规则组下的企业客户标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyTagListResponse
     */
    public CustomerStrategyTagListResponse getCustomerStrategyTagList(CustomerStrategyTagListRequest request, String applicationName) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("request 不能为空，请检查！！！");
        }
        checkCustomerStrategyIdList(request.getTagId(), "tagId");
        checkCustomerStrategyIdList(request.getGroupId(), "groupId");
        return externalContactClient.getCustomerStrategyTagList(request, applicationName);
    }

    /**
     * 为指定客户联系规则组创建企业客户标签
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return CustomerStrategyTagCreateResponse
     */
    public CustomerStrategyTagCreateResponse createCustomerStrategyTag(CustomerStrategyTagCreateRequest request, String applicationName) {
        if (Objects.isNull(request) || Objects.isNull(request.getStrategyId())) {
            throw new ParamCheckException("strategyId 不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getGroupId()) && StringUtils.isBlank(request.getGroupName())) {
            throw new ParamCheckException("groupId 和 groupName 至少配置一个，请检查！！！");
        }
        if (StringUtils.isNotBlank(request.getGroupName()) && request.getGroupName().length() > 30) {
            throw new ParamCheckException("groupName 最长允许 30 个字符，请检查！！！");
        }
        checkCustomerStrategyTagOrder(request.getOrder(), "order");
        if (CollectionUtils.isEmpty(request.getTag())) {
            throw new ParamCheckException("tag 不能为空，不支持创建空标签组，请检查！！！");
        }
        checkCustomerStrategyTagList(request.getTag());
        return externalContactClient.createCustomerStrategyTag(request, applicationName);
    }

    /**
     * 编辑指定客户联系规则组下的企业客户标签或标签组
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse editCustomerStrategyTag(CustomerStrategyTagEditRequest request, String applicationName) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getId())) {
            throw new ParamCheckException("id 不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getName()) && Objects.isNull(request.getOrder())) {
            throw new ParamCheckException("name 和 order 至少配置一个，请检查！！！");
        }
        if (StringUtils.isNotBlank(request.getName()) && request.getName().length() > 30) {
            throw new ParamCheckException("name 最长允许 30 个字符，请检查！！！");
        }
        checkCustomerStrategyTagOrder(request.getOrder(), "order");
        return externalContactClient.editCustomerStrategyTag(request, applicationName);
    }

    /**
     * 删除指定客户联系规则组下的企业客户标签或标签组
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return BaseResponse
     */
    public BaseResponse deleteCustomerStrategyTag(CustomerStrategyTagDeleteRequest request, String applicationName) {
        if (Objects.isNull(request) || (CollectionUtils.isEmpty(request.getTagId()) && CollectionUtils.isEmpty(request.getGroupId()))) {
            throw new ParamCheckException("tagId 和 groupId 不可同时为空，请检查！！！");
        }
        checkCustomerStrategyIdList(request.getTagId(), "tagId");
        checkCustomerStrategyIdList(request.getGroupId(), "groupId");
        return externalContactClient.deleteCustomerStrategyTag(request, applicationName);
    }

    /* ---------客户联系规则组企业客户标签管理---------- end */

    /**
     * 校验规则组详情请求及规则组 ID
     *
     * @param request 请求
     */
    private void checkCustomerStrategyId(CustomerStrategyIdRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getStrategyId())) {
            throw new ParamCheckException("strategyId 不能为空，请检查！！！");
        }
    }

    /**
     * 校验规则组管理范围请求及规则组 ID
     *
     * @param request 请求
     */
    private void checkCustomerStrategyId(CustomerStrategyRangeRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getStrategyId())) {
            throw new ParamCheckException("strategyId 不能为空，请检查！！！");
        }
    }

    /**
     * 校验规则组分页大小，官方最大值为 1000
     *
     * @param limit   分页大小
     * @param request 请求
     */
    private void checkCustomerStrategyLimit(Integer limit, Object request) {
        if (Objects.isNull(limit)) {
            if (request instanceof CustomerStrategyListRequest) {
                ((CustomerStrategyListRequest) request).setLimit(1000);
            } else if (request instanceof CustomerStrategyRangeRequest) {
                ((CustomerStrategyRangeRequest) request).setLimit(1000);
            } else {
                throw new ParamCheckException("limit 不能为空，请检查！！！");
            }
            return;
        }
        if (limit <= 0 || limit > 1000) {
            throw new ParamCheckException("limit 必须在 1 到 1000 之间，请检查！！！");
        }
    }

    /**
     * 校验规则组管理员列表，官方单次最多允许 20 个管理员
     *
     * @param adminList 管理员列表
     */
    private void checkCustomerStrategyAdminList(List<String> adminList) {
        if (CollectionUtils.isEmpty(adminList)) {
            return;
        }
        if (adminList.size() > 20) {
            throw new ParamCheckException("adminList 最多允许 20 个管理员，请检查！！！");
        }
        for (String admin : adminList) {
            if (StringUtils.isBlank(admin)) {
                throw new ParamCheckException("adminList 中存在空的管理员 userid，请检查！！！");
            }
        }
    }

    /**
     * 校验规则组管理范围节点，官方单次最多允许 100 个节点
     *
     * @param range 管理范围节点列表
     */
    private void checkCustomerStrategyRange(List<CustomerStrategyRange> range) {
        if (CollectionUtils.isEmpty(range)) {
            return;
        }
        if (range.size() > 100) {
            throw new ParamCheckException("range 最多允许 100 个管理范围节点，请检查！！！");
        }
        for (CustomerStrategyRange node : range) {
            if (Objects.isNull(node) || Objects.isNull(node.getType())) {
                throw new ParamCheckException("管理范围节点和节点类型不能为空，请检查！！！");
            }
            if (Objects.equals(node.getType(), 1) && StringUtils.isBlank(node.getUserid())) {
                throw new ParamCheckException("成员类型管理范围节点必须配置 userid，请检查！！！");
            } else if (Objects.equals(node.getType(), 2) && Objects.isNull(node.getPartyid())) {
                throw new ParamCheckException("部门类型管理范围节点必须配置 partyid，请检查！！！");
            } else if (!Objects.equals(node.getType(), 1) && !Objects.equals(node.getType(), 2)) {
                throw new ParamCheckException("管理范围节点类型只允许 1-成员 或 2-部门，请检查！！！");
            }
        }
    }

    /**
     * 校验客户联系规则组标签 ID 列表
     *
     * @param idList    标签或标签组 ID 列表
     * @param fieldName 参数名称
     */
    private void checkCustomerStrategyIdList(List<String> idList, String fieldName) {
        if (CollectionUtils.isEmpty(idList)) {
            return;
        }
        for (String id : idList) {
            if (StringUtils.isBlank(id)) {
                throw new ParamCheckException(fieldName + " 中存在空 ID，请检查！！！");
            }
        }
    }

    /**
     * 校验客户联系规则组标签列表
     *
     * @param tagList 标签列表
     */
    private void checkCustomerStrategyTagList(List<CustomerStrategyTag> tagList) {
        for (CustomerStrategyTag tag : tagList) {
            if (Objects.isNull(tag) || StringUtils.isBlank(tag.getName())) {
                throw new ParamCheckException("tag 中存在空标签或空标签名称，请检查！！！");
            }
            if (tag.getName().length() > 30) {
                throw new ParamCheckException("tag 中存在超过 30 个字符的标签名称，请检查！！！");
            }
            checkCustomerStrategyTagOrder(tag.getOrder(), "tag.order");
        }
    }

    /**
     * 校验客户联系规则组标签排序值，官方有效范围是 [0, 2^32)
     *
     * @param order     排序值
     * @param fieldName 参数名称
     */
    private void checkCustomerStrategyTagOrder(Long order, String fieldName) {
        if (Objects.isNull(order)) {
            return;
        }
        if (order < 0 || order >= 4294967296L) {
            throw new ParamCheckException(fieldName + " 必须在 0 到 4294967295 之间，请检查！！！");
        }
    }
}
