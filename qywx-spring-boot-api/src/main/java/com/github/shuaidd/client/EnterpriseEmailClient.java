package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.exmail.*;
import com.github.shuaidd.response.vip.*;
import com.github.shuaidd.resquest.exmail.*;
import com.github.shuaidd.resquest.vip.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 企业邮箱管理
 */
@FeignClient(name = "EnterpriseEmailClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface EnterpriseEmailClient extends CommonClient {

    /**
     * 创建邮件群组
     * 该接口用于创建新邮件群组，可以指定群组成员，定义群组使用权限范围
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/group/create", headers = HEAD)
    BaseResponse createMailGroup(MailGroupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新邮件群组
     * 该接口用于更新邮件群组，可以修改群组名称、群组成员、群组使用权限等。需要注意的是Json数组类型传空值将会清空其内容，不传则保持不变
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/group/update", headers = HEAD)
    BaseResponse updateMailGroup(MailGroupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除邮件群组
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/group/delete", headers = HEAD)
    BaseResponse deleteMailGroup(DeleteMailGroupRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取邮件群组详情
     *
     * @param groupId 群组编号
     * @param app     应用
     * @return 详情信息
     */
    @GetMapping(value = "exmail/group/get", headers = HEAD)
    MailGroupDetailResponse getMailGroupDetail(@RequestParam("groupid") String groupId, @RequestParam(HEAD_KEY) String app);

    /**
     * @param groupId 群组编号
     * @param fuzzy   1开启模糊搜索，0获取全部邮件群组
     * @param app     邮件群组ID，邮箱格式
     * @return 查询结果
     */
    @GetMapping(value = "exmail/group/search", headers = HEAD)
    SearchMailGroupResponse searchGroup(@RequestParam("groupid") String groupId, @RequestParam("fuzzy") int fuzzy, @RequestParam(HEAD_KEY) String app);

    /**
     * 创建业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 业务邮箱ID
     */
    @PostMapping(value = "exmail/publicmail/create", headers = HEAD)
    CreatePublicMailResponse createPublicMail(CreatePublicMailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 业务邮箱ID
     */
    @PostMapping(value = "exmail/publicmail/update", headers = HEAD)
    BaseResponse updatePublicMail(UpdatePublicMailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/publicmail/delete", headers = HEAD)
    BaseResponse deletePublicMail(DeletePublicMailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取业务邮箱详情
     *
     * @param request 请求
     * @param app     应用
     * @return 详情信息
     */
    @PostMapping(value = "/exmail/publicmail/get", headers = HEAD)
    MailDetailResponse mailDetail(GetMailDetailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 模糊搜索业务邮箱
     *
     * @param email 邮箱
     * @param fuzzy 1开启模糊搜索，0获取全部业务邮箱
     * @param app   应用
     * @return 搜索结果
     */
    @GetMapping(value = "/exmail/publicmail/search", headers = HEAD)
    SearchMailResponse searchEmail(@RequestParam("email") String email, @RequestParam("fuzzy") int fuzzy, @RequestParam(HEAD_KEY) String app);

    /**
     * 发送普通邮件
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/app/compose_send", headers = HEAD)
    BaseResponse composeSend(ComposeEmailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取收件箱邮件列表
     *
     * @param request 请求
     * @param app     应用
     * @return 邮件列表
     */
    @PostMapping(value = "exmail/app/get_mail_list", headers = HEAD)
    MailListResponse getMailList(GetMailListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取邮件内容
     *
     * @param request 请求
     * @param app     应用
     * @return 邮件内容
     */
    @PostMapping(value = "exmail/app/read_mail", headers = HEAD)
    ReadMailResponse readMail(ReadMailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更新应用邮箱账号
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/app/update_email_alias", headers = HEAD)
    BaseResponse updateEmailAlias(UpdateEmailAliasRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询应用邮箱账号，官方接口不需要请求包体
     *
     * @param app 应用
     * @return 应用邮箱账号及别名列表
     */
    @PostMapping(value = "exmail/app/get_email_alias", headers = HEAD)
    EmailAliasResponse getEmailAlias(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取客户端专用密码列表
     *
     * @param request 请求
     * @param app     应用
     * @return 客户端专用密码列表
     */
    @PostMapping(value = "exmail/publicmail/get_auth_code_list", headers = HEAD)
    AuthCodeListResponse getAuthCodeList(GetAuthCodeListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除客户端专用密码
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/publicmail/delete_auth_code", headers = HEAD)
    BaseResponse deleteAuthCode(DeleteAuthCodeRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 禁用或启用邮箱账号
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/account/act_email", headers = HEAD)
    BaseResponse actEmail(ActEmailRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取用户功能属性
     *
     * @param request 请求
     * @param app     应用
     * @return 用户功能属性
     */
    @PostMapping(value = "exmail/useroption/get", headers = HEAD)
    UserOptionResponse getUserOption(GetUserOptionRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 更改用户功能属性
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @PostMapping(value = "exmail/useroption/update", headers = HEAD)
    BaseResponse updateUserOption(UpdateUserOptionRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取邮件未读数
     *
     * @param request 请求
     * @param app     应用
     * @return 未读邮件数量
     */
    @PostMapping(value = "exmail/mail/get_newcount", headers = HEAD)
    MailUnreadCountResponse getMailUnreadCount(GetMailUnreadCountRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 分配结果
     */
    @PostMapping(value = "exmail/vip/batch_add", headers = HEAD)
    VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 撤销分配结果
     */
    @PostMapping(value = "exmail/vip/batch_del", headers = HEAD)
    VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @PostMapping(value = "exmail/vip/list", headers = HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @RequestParam(HEAD_KEY) String app);
}
