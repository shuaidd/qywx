package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.*;
import com.github.shuaidd.resquest.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述 enjoy your life
 *
 * @author ddshuai
 * date 2019-04-03 15:50
 **/
@SuppressWarnings("all")
@FeignClient(name = "wechat", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface WeChatClient {

    String HEAD_KEY = "app";
    String HEAD = HEAD_KEY + "={app}";
    String ACCESS_TOKEN = "access_token";
    String GET_TOKEN = "/gettoken";

    /**
     * 获取应用 access token
     *
     * @param corpId 企业号
     * @param secret 密匙
     * @return AccessTokenResponse
     */
    @GetMapping("gettoken")
    AccessTokenResponse getAccessToken(@RequestParam("corpid") String corpId, @RequestParam("corpsecret") String secret);

    /**
     * 创建成员
     *
     * @param request 请求参数
     * @param app     应用
     * @return BaseResponse
     */
    @PostMapping(value = "user/create", headers = HEAD)
    BaseResponse createUser(CreateUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 读取成员信息
     *
     * @param userId 成员编号
     */
    @GetMapping(value = "user/get", headers = HEAD)
    WeChatUserResponse getUser(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新成员信息【根据userID更新，必传】
     *
     * @param request 更新请求参数
     * @return BaseResponse
     */
    @PostMapping(value = "user/update", headers = HEAD)
    BaseResponse updateUser(UpdateUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除成员
     *
     * @param userId 成员编号
     * @param app    应用
     * @return BaseResponse
     */
    @GetMapping(value = "user/delete", headers = HEAD)
    BaseResponse deleteUser(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量删除成员
     *
     * @param request
     * @param app     应用
     * @return BaseResponse
     */
    @PostMapping(value = "user/batchdelete", headers = HEAD)
    BaseResponse batchDelete(BatchDeleteUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取部门成员[应用须拥有指定部门的查看权限。]
     *
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return
     */
    @GetMapping(value = "user/simplelist", headers = HEAD)
    DepartmentUserResponse getDepartmentUser(@RequestParam("department_id") Integer departmentId, @RequestParam("fetch_child") Integer fetchChild, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取部门成员详情[应用须拥有指定部门的查看权限。]
     *
     * @param departmentId 获取的部门id
     * @param fetchChild   1/0：是否递归获取子部门下面的成员
     * @return
     */
    @GetMapping(value = "user/list", headers = HEAD)
    DepartmentUserResponse getDepartmentUserDetail(@RequestParam("department_id") Integer departmentId, @RequestParam("fetch_child") Integer fetchChild, @RequestParam(HEAD_KEY) String app);

    /**
     * userid转openid
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "user/convert_to_openid", headers = HEAD)
    ConvertUserIdOpenIdResponse convertToOpenId(ConvertUserIdOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * openid转userid
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "user/convert_to_userid", headers = HEAD)
    ConvertUserIdOpenIdResponse convertToUserId(ConvertUserIdOpenIdRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 二次验证
     *
     * @param userId
     * @param app
     * @return
     */
    @GetMapping(value = "user/authsucc", headers = HEAD)
    BaseResponse authSucc(@RequestParam("userid") String userId, @RequestParam(HEAD_KEY) String app);

    /**
     * 邀请成员
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "batch/invite", headers = HEAD)
    BaseResponse invite(InviteUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建部门
     *
     * @param request
     * @return CreateDepartmentResponse
     */
    @PostMapping(value = "department/create", headers = HEAD)
    CreateDepartmentResponse createDepartment(DepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新部门
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "department/update", headers = HEAD)
    BaseResponse updateDepartment(DepartmentRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除部门【注：不能删除根部门；不能删除含有子部门、成员的部门】
     *
     * @param id  企业微信部门id
     * @param app
     * @return
     */
    @GetMapping(value = "department/delete", headers = HEAD)
    BaseResponse deleteDepartment(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 权限说明：
     * 只能拉取token对应的应用的权限范围内的部门列表
     * <p>
     * 获取部门列表【获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构】
     *
     * @param id  部门id
     * @param app
     * @return
     */
    @GetMapping(value = "department/list", headers = HEAD)
    DepartmentListResponse departmentList(@RequestParam(value = "id", required = false) Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建标签【创建的标签属于该应用，只有该应用才可以增删成员】
     * 注意，标签总数不能超过3000个。
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "tag/create", headers = HEAD)
    CreateTagResponse createTag(TagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 权限说明：
     * 调用者必须是指定标签的创建者
     * <p>
     * 更新标签名字【长度限制为32个字（汉字或英文字母），标签不可与其他标签重名】
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "tag/update", headers = HEAD)
    BaseResponse updateTag(TagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除标签
     *
     * @param id  标签ID
     * @param app
     * @return
     */
    @GetMapping(value = "tag/delete", headers = HEAD)
    BaseResponse deleteTag(@RequestParam("tagid") Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取标签成员
     *
     * @param id
     * @param app
     * @return
     */
    @GetMapping(value = "tag/get", headers = HEAD)
    QueryTagUserResponse getTagUser(@RequestParam("tagid") Integer id, @RequestParam(HEAD_KEY) String app);

    /**
     * 增加标签成员
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "tag/addtagusers", headers = HEAD)
    TagUserResponse addTagUsers(TagUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除标签成员
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "tag/deltagusers", headers = HEAD)
    TagUserResponse deleteTagUsers(TagUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取标签列表
     *
     * @param app
     * @return
     */
    @GetMapping(value = "tag/list", headers = HEAD)
    TagListResponse getTagList(@RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，更新成员在文件中指定的字段值
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，保持不变
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * <p>
     * 增量更新成员
     *
     * @param request
     * @param app
     * @return AsyncJobResponse
     */
    @PostMapping(value = "batch/syncuser", headers = HEAD)
    AsyncJobResponse asyncBatchUpdateUser(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 模板中的部门需填写部门ID，多个部门用分号分隔，部门ID必须为数字，根部门的部门id默认为1
     * 文件中存在、通讯录中也存在的成员，完全以文件为准
     * 文件中存在、通讯录中不存在的成员，执行添加操作
     * 通讯录中存在、文件中不存在的成员，执行删除操作。出于安全考虑，下面两种情形系统将中止导入并返回相应的错误码。
     * 需要删除的成员多于50人，且多于现有人数的20%以上
     * 需要删除的成员少于50人，且多于现有人数的80%以上
     * 成员字段更新规则：可自行添加扩展字段。文件中有指定的字段，以指定的字段值为准；文件中没指定的字段，不更新
     * <p>
     * <p>
     * 全量覆盖成员
     *
     * @param request
     * @param app
     * @return AsyncJobResponse
     */
    @PostMapping(value = "batch/replaceuser", headers = HEAD)
    AsyncJobResponse fullCoverUser(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 注意事项：
     * <p>
     * 文件中存在、通讯录中也存在的部门，执行修改操作
     * 文件中存在、通讯录中不存在的部门，执行添加操作
     * 文件中不存在、通讯录中存在的部门，当部门下没有任何成员或子部门时，执行删除操作
     * 文件中不存在、通讯录中存在的部门，当部门下仍有成员或子部门时，暂时不会删除，当下次导入成员把人从部门移出后自动删除
     * CSV文件中，部门名称、部门ID、父部门ID为必填字段，部门ID必须为数字，根部门的部门id默认为1；排序为可选字段，置空或填0不修改排序, order值大的排序靠前。
     * <p>
     * 全量覆盖部门
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "batch/replaceparty", headers = HEAD)
    AsyncJobResponse fullCoverDepartment(AsyncJobRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取异步任务结果
     *
     * @param jobId
     * @param app
     * @return
     */
    @GetMapping(value = "batch/getresult", headers = HEAD)
    AsyncJobResultResponse jobResult(@RequestParam("jobid") String jobId, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取访问用户身份
     *
     * @param code
     * @param app
     * @return
     */
    @GetMapping(value = "user/getuserinfo", headers = HEAD)
    AuthenticationResponse getAuthentication(@RequestParam("code") String code, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取应用
     *
     * @param agentId
     * @param app
     * @return
     */
    @GetMapping(value = "agent/get", headers = HEAD)
    WeChatApplicationResponse getApplication(@RequestParam("agentid") String agentId, @RequestParam(HEAD_KEY) String app);

    /**
     * 设置应用
     *
     * @param reuqest
     * @param app
     * @return
     */
    @PostMapping(value = "agent/set", headers = HEAD)
    BaseResponse applicationSetting(ApplicationSettingReuqest reuqest, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建菜单
     *
     * @param request
     * @param agentid
     * @param app
     * @return
     */
    @PostMapping(value = "menu/create", headers = HEAD)
    BaseResponse createApplicationButton(ApplicationButtonRequest request, @RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取菜单
     *
     * @param agentid
     * @param app
     * @return
     */
    @GetMapping(value = "menu/get", headers = HEAD)
    ApplicationButtonResponse getApplicationButtons(@RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除菜单
     *
     * @param agentid
     * @param app
     * @return
     */
    @GetMapping(value = "menu/delete", headers = HEAD)
    BaseResponse deleteApplicationButtons(@RequestParam("agentid") String agentid, @RequestParam(HEAD_KEY) String app);

    /**
     * 发送消息
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "message/send", headers = HEAD)
    SendMessageResponse sendMessage(SendMessageRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建群聊会话
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "appchat/create", headers = HEAD)
    CreateAppChatResponse createAppChat(CreateAppChatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改群聊会话
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "appchat/update", headers = HEAD)
    BaseResponse updateAppChat(UpdateAppChatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取群聊会话
     *
     * @param chatId
     * @param app
     * @return
     */
    @GetMapping(value = "appchat/get", headers = HEAD)
    SearchAppChatResponse searchAppChat(@RequestParam("chatid") String chatId, @RequestParam(HEAD_KEY) String app);

    /**
     * 发送群消息
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "appchat/send", headers = HEAD)
    BaseResponse sendAppChatMessage(SendAppChatRequest request, @RequestParam(HEAD_KEY) String app);


    /**
     * 获取打卡数据
     * <p>
     * 获取记录时间跨度不超过一个月
     * 用户列表不超过100个。若用户超过100个，请分批获取
     * 有打卡记录即可获取打卡数据，与当前”打卡应用”是否开启无关
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "checkin/getcheckindata", headers = HEAD)
    CheckInDataResponse getCheckInData(CheckInDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取打卡规则
     * <p>
     * 用户列表不超过100个，若用户超过100个，请分批获取。
     * 用户在不同日期的规则不一定相同，请按天获取。
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "checkin/getcheckinoption", headers = HEAD)
    CheckInRuleResponse getCheckInOption(CheckInRuleRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取审批数据
     * <p>
     * 获取审批记录请求参数endtime需要大于startime， 切起始时间跨度不要超过一个月；
     * 一次请求返回的审批记录上限是100条，超过100条记录请使用next_spnum进行分页拉取。
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "corp/getapprovaldata", headers = HEAD)
    ApprovalDataResponse getApprovalData(ApprovalDataRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取公费电话拨打记录
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "dial/get_dial_record", headers = HEAD)
    DialRecordResponse getDialRecord(DialRecordRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取配置了客户联系功能的成员列表
     *
     * @param app 应用别名
     * @return FollowUserResponse
     */
    @GetMapping(value = "externalcontact/get_follow_user_list", headers = HEAD)
    FollowUserResponse getFollowUserList(@RequestParam(HEAD_KEY) String app);

    /**
     * 配置客户联系「联系我」方式
     *
     * @param request
     * @param app
     * @return AddContactWayResponse
     */
    @PostMapping(value = "externalcontact/add_contact_way", headers = HEAD)
    AddContactWayResponse addContactWay(AddContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业已配置的「联系我」方式
     *
     * @param request
     * @param app
     * @return ContactWayResponse
     */
    @PostMapping(value = "externalcontact/get_contact_way", headers = HEAD)
    ContactWayResponse getContactWay(GetContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新企业已配置的「联系我」方式
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/update_contact_way", headers = HEAD)
    BaseResponse updateContactWay(UpdateContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除企业已配置的「联系我」方式
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/del_contact_way", headers = HEAD)
    BaseResponse deleteContactWay(GetContactWayRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 结束临时会话
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/close_temp_chat", headers = HEAD)
    BaseResponse closeTempChat(CloseTempChatRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户列表
     *
     * @param request
     * @param app
     * @return CustomListResponse
     */
    @PostMapping(value = "externalcontact/list", headers = HEAD)
    CustomListResponse getCustomList(CustomListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户详情
     *
     * @param userid
     * @param app
     * @return ExternalContactResponse
     */
    @GetMapping(value = "externalcontact/get", headers = HEAD)
    ExternalContactResponse getExternalContact(@RequestParam("external_userid") String externalUserId, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量获取客户详情
     *
     * @param request
     * @param app
     * @return BatchExternalContactResponse
     */
    @PostMapping(value = "externalcontact/batch/get_by_user", headers = HEAD)
    BatchExternalContactResponse getBatchExternalContact(BatchExternalContactRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 修改客户备注信息
     *
     * @param remarkRequest
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/remark", headers = HEAD)
    BaseResponse updateCustomRemark(UpdateRemarkRequest remarkRequest, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取手机号随机串
     *
     * @param request
     * @param app
     * @return MobileHashCodeResponse
     */
    @PostMapping(value = "user/get_mobile_hashcode", headers = HEAD)
    MobileHashCodeResponse getMobileHashcode(MobileHashCodeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业标签库
     *
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "externalcontact/get_corp_tag_list", headers = HEAD)
    TagGroupResponse getCorpTagList(TagGroupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 添加企业客户标签
     *
     * @param request
     * @param app
     * @return AddCorpTagResponse
     */
    @PostMapping(value = "externalcontact/add_corp_tag", headers = HEAD)
    AddCorpTagResponse addCorpTag(AddCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑企业客户标签
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/edit_corp_tag", headers = HEAD)
    BaseResponse editCorpTag(EditCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除企业客户标签
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/del_corp_tag", headers = HEAD)
    BaseResponse delCorpTag(DelCorpTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑客户企业标签
     *
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/mark_tag", headers = HEAD)
    BaseResponse markTag(MarkTagRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取离职成员列表
     * @param request
     * @param app
     * @return UnassignedListResponse
     */
    @PostMapping(value = "externalcontact/get_unassigned_list",headers = HEAD)
    UnassignedListResponse unassignedList(PageRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 分配在职或离职成员的客户
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/transfer",headers = HEAD)
    BaseResponse transfer(TransferRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 查询客户接替结果
     * @param resultRequest
     * @param app
     * @return TransferResultResponse
     */
    @PostMapping(value = "externalcontact/get_transfer_result",headers = HEAD)
    TransferResultResponse getTransferResult(TransferResultRequest resultRequest,@RequestParam(HEAD_KEY) String app);

    /**
     * 分配离职成员的客户群
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "externalcontact/groupchat/transfer",headers = HEAD)
    FailedChatResponse groupChatTransfer(GroupChatTransferRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户群列表
     * @param request
     * @param app
     * @return GroupChatListResponse
     */
    @PostMapping(value = "externalcontact/groupchat/list",headers = HEAD)
    GroupChatListResponse groupChatList(GroupChatListRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户群详情
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "externalcontact/groupchat/get",headers = HEAD)
    GroupChatDetailResponse groupChatDetail(GroupChatDetailRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业全部的发表列表
     * @param request
     * @param app
     * @return MomentListResponse
     */
    @PostMapping(value = "externalcontact/get_moment_list",headers = HEAD)
    MomentListResponse getMomentList(MomentListRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈企业发表的列表
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "externalcontact/get_moment_task",headers = HEAD)
    MomentTaskResponse getMomentTask(MomentTaskRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈发表时选择的可见范围
     * @param request
     * @param app
     * @return MomentCustomerListResponse
     */
    @PostMapping(value = "externalcontact/get_moment_customer_list",headers = HEAD)
    MomentCustomerListResponse getMomentCustomerList(MomentCustomerListRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈发表后的可见客户列表
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "externalcontact/get_moment_send_result",headers = HEAD)
    MomentCustomerListResponse getMomentSendResult(MomentCustomerListRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户朋友圈的互动数据
     * @param request
     * @param app
     * @return CommentsResponse
     */
    @PostMapping(value = "externalcontact/get_moment_comments",headers = HEAD)
    CommentsResponse getMomentComments(CommentsRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 创建企业群发
     * @param request
     * @param app
     * @return MsgTemplateResponse
     */
    @PostMapping(value = "externalcontact/add_msg_template",headers = HEAD)
    MsgTemplateResponse addMsgTemplate(MsgTemplateRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取群发记录列表
     * @param request
     * @param app
     * @return GroupMsgResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_list",headers = HEAD)
    GroupMsgResponse getGroupMsgList(GroupMsgRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取群发成员发送任务列表
     * @param request
     * @param app
     * @return GroupMsgTaskResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_task",headers = HEAD)
    GroupMsgTaskResponse getGroupMsgTask(GroupMsgTaskRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业群发成员执行结果
     * @param request
     * @param app
     * @return GroupMsgSendResultResponse
     */
    @PostMapping(value = "externalcontact/get_groupmsg_send_result",headers = HEAD)
    GroupMsgSendResultResponse getGroupMsgSendResult(GroupMsgSendRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 发送新客户欢迎语
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/send_welcome_msg",headers = HEAD)
    BaseResponse sendWelcomeMsg(WelcomeMsgRequest request,@RequestParam(HEAD_KEY) String app);

    /**
     * 添加入群欢迎语素材
     * @param request
     * @param app
     * @return AddWelcomeTemplateResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/add",headers = HEAD)
    AddWelcomeTemplateResponse addGroupWelcomeTemplate(WelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 编辑入群欢迎语素材
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/edit",headers = HEAD)
    BaseResponse editGroupWelcomeTemplate(WelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取入群欢迎语素材
     * @param request
     * @param app
     * @return GetWelcomeTemplateResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/get",headers = HEAD)
    GetWelcomeTemplateResponse getGroupWelcomeTemplate(GetWelcomeTemplateRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除入群欢迎语素材
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "externalcontact/group_welcome_template/del",headers = HEAD)
    BaseResponse deleteGroupWelcomeTemplate(DeleteWelcomeTemplateResponse request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取「联系客户统计」数据
     * @param request
     * @param app
     * @return BehaviorDataResponse
     */
    @PostMapping(value = "externalcontact/get_user_behavior_data",headers = HEAD)
    BehaviorDataResponse getUserBehaviorData(UserBehaviorRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 按群主聚合的方式
     * @param request
     * @param app
     * @return GroupChatStatisticResponse
     */
    @PostMapping(value = "externalcontact/groupchat/statistic",headers = HEAD)
    GroupChatStatisticResponse getGroupChatStatistic(GroupChatStatisticRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 按自然日聚合的方式
     * @param request
     * @param app
     * @return GroupChatStatisticResponse
     */
    @PostMapping(value = "externalcontact/groupchat/statistic_group_by_day",headers = HEAD)
    GroupChatStatisticResponse getGroupChatStatisticByDay(GroupChatStatisticRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建日历
     * @param request
     * @param app
     * @return CalendarResponse
     */
    @PostMapping(value = "oa/calendar/add",headers = HEAD)
    CalendarResponse addCalendar(CalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新日历
     * @param request
     * @param app
     * @return BaseResponse
     */
    @PostMapping(value = "oa/calendar/update",headers = HEAD)
    BaseResponse updateCalendar(CalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取日历详情
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "oa/calendar/get",headers = HEAD)
    GetCalendarResponse getCalendar(GetCalendarRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除日历
     * @param request
     * @param app
     * @return
     */
    @PostMapping(value = "oa/calendar/del",headers = HEAD)
    BaseResponse delCalendar(DeleteCalendarRequest request, @RequestParam(HEAD_KEY) String app);


}
