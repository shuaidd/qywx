package com.github.shuaidd.client;

import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.exmail.AuthCodeListResponse;
import com.github.shuaidd.response.exmail.CreatePublicMailResponse;
import com.github.shuaidd.response.exmail.EmailAliasResponse;
import com.github.shuaidd.response.exmail.MailDetailResponse;
import com.github.shuaidd.response.exmail.MailGroupDetailResponse;
import com.github.shuaidd.response.exmail.MailListResponse;
import com.github.shuaidd.response.exmail.MailUnreadCountResponse;
import com.github.shuaidd.response.exmail.ReadMailResponse;
import com.github.shuaidd.response.exmail.SearchMailGroupResponse;
import com.github.shuaidd.response.exmail.SearchMailResponse;
import com.github.shuaidd.response.exmail.UserOptionResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.resquest.exmail.ActEmailRequest;
import com.github.shuaidd.resquest.exmail.ComposeEmailRequest;
import com.github.shuaidd.resquest.exmail.CreatePublicMailRequest;
import com.github.shuaidd.resquest.exmail.DeleteAuthCodeRequest;
import com.github.shuaidd.resquest.exmail.DeleteMailGroupRequest;
import com.github.shuaidd.resquest.exmail.DeletePublicMailRequest;
import com.github.shuaidd.resquest.exmail.GetAuthCodeListRequest;
import com.github.shuaidd.resquest.exmail.GetMailDetailRequest;
import com.github.shuaidd.resquest.exmail.GetMailListRequest;
import com.github.shuaidd.resquest.exmail.GetMailUnreadCountRequest;
import com.github.shuaidd.resquest.exmail.GetUserOptionRequest;
import com.github.shuaidd.resquest.exmail.MailGroupRequest;
import com.github.shuaidd.resquest.exmail.ReadMailRequest;
import com.github.shuaidd.resquest.exmail.UpdateEmailAliasRequest;
import com.github.shuaidd.resquest.exmail.UpdatePublicMailRequest;
import com.github.shuaidd.resquest.exmail.UpdateUserOptionRequest;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 企业邮箱管理
 */
public interface EnterpriseEmailClient extends CommonClient {

    /**
     * 创建邮件群组
     * 该接口用于创建新邮件群组，可以指定群组成员，定义群组使用权限范围
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/group/create?app={app}")
    @Headers(HEAD)
    BaseResponse createMailGroup(MailGroupRequest request, @Param("app") String app);

    /**
     * 更新邮件群组
     * 该接口用于更新邮件群组，可以修改群组名称、群组成员、群组使用权限等。需要注意的是Json数组类型传空值将会清空其内容，不传则保持不变
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/group/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateMailGroup(MailGroupRequest request, @Param("app") String app);

    /**
     * 删除邮件群组
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/group/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deleteMailGroup(DeleteMailGroupRequest request, @Param("app") String app);

    /**
     * 获取邮件群组详情
     *
     * @param groupId 群组编号
     * @param app     应用
     * @return 详情信息
     */
    @RequestLine("GET /exmail/group/get?groupid={groupid}&app={app}")
    @Headers(HEAD)
    MailGroupDetailResponse getMailGroupDetail(@Param("groupid") String groupId, @Param("app") String app);

    /**
     * @param groupId 群组编号
     * @param fuzzy   1开启模糊搜索，0获取全部邮件群组
     * @param app     邮件群组ID，邮箱格式
     * @return 查询结果
     */
    @RequestLine("GET /exmail/group/search?groupid={groupid}&fuzzy={fuzzy}&app={app}")
    @Headers(HEAD)
    SearchMailGroupResponse searchGroup(@Param("groupid") String groupId, @Param("fuzzy") int fuzzy, @Param("app") String app);

    /**
     * 创建业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 业务邮箱ID
     */
    @RequestLine("POST /exmail/publicmail/create?app={app}")
    @Headers(HEAD)
    CreatePublicMailResponse createPublicMail(CreatePublicMailRequest request, @Param("app") String app);

    /**
     * 更新业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 业务邮箱ID
     */
    @RequestLine("POST /exmail/publicmail/update?app={app}")
    @Headers(HEAD)
    BaseResponse updatePublicMail(UpdatePublicMailRequest request, @Param("app") String app);

    /**
     * 删除业务邮箱
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/publicmail/delete?app={app}")
    @Headers(HEAD)
    BaseResponse deletePublicMail(DeletePublicMailRequest request, @Param("app") String app);

    /**
     * 获取业务邮箱详情
     *
     * @param request 请求
     * @param app     应用
     * @return 详情信息
     */
    @RequestLine("POST /exmail/publicmail/get?app={app}")
    @Headers(HEAD)
    MailDetailResponse mailDetail(GetMailDetailRequest request, @Param("app") String app);

    /**
     * 模糊搜索业务邮箱
     *
     * @param email 邮箱
     * @param fuzzy 1开启模糊搜索，0获取全部业务邮箱
     * @param app   应用
     * @return 搜索结果
     */
    @RequestLine("GET /exmail/publicmail/search?email={email}&fuzzy={fuzzy}&app={app}")
    @Headers(HEAD)
    SearchMailResponse searchEmail(@Param("email") String email, @Param("fuzzy") int fuzzy, @Param("app") String app);

    /**
     * 发送普通邮件
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/app/compose_send?app={app}")
    @Headers(HEAD)
    BaseResponse composeSend(ComposeEmailRequest request, @Param("app") String app);

    /**
     * 获取收件箱邮件列表
     *
     * @param request 请求
     * @param app     应用
     * @return 邮件列表
     */
    @RequestLine("POST /exmail/app/get_mail_list?app={app}")
    @Headers(HEAD)
    MailListResponse getMailList(GetMailListRequest request, @Param("app") String app);

    /**
     * 获取邮件内容
     *
     * @param request 请求
     * @param app     应用
     * @return 邮件内容
     */
    @RequestLine("POST /exmail/app/read_mail?app={app}")
    @Headers(HEAD)
    ReadMailResponse readMail(ReadMailRequest request, @Param("app") String app);

    /**
     * 更新应用邮箱账号
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/app/update_email_alias?app={app}")
    @Headers(HEAD)
    BaseResponse updateEmailAlias(UpdateEmailAliasRequest request, @Param("app") String app);

    /**
     * 查询应用邮箱账号，官方接口不需要请求包体
     *
     * @param app 应用
     * @return 应用邮箱账号及别名列表
     */
    @RequestLine("POST /exmail/app/get_email_alias?app={app}")
    @Headers(HEAD)
    EmailAliasResponse getEmailAlias(@Param("app") String app);

    /**
     * 获取客户端专用密码列表
     *
     * @param request 请求
     * @param app     应用
     * @return 客户端专用密码列表
     */
    @RequestLine("POST /exmail/publicmail/get_auth_code_list?app={app}")
    @Headers(HEAD)
    AuthCodeListResponse getAuthCodeList(GetAuthCodeListRequest request, @Param("app") String app);

    /**
     * 删除客户端专用密码
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/publicmail/delete_auth_code?app={app}")
    @Headers(HEAD)
    BaseResponse deleteAuthCode(DeleteAuthCodeRequest request, @Param("app") String app);

    /**
     * 禁用或启用邮箱账号
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/account/act_email?app={app}")
    @Headers(HEAD)
    BaseResponse actEmail(ActEmailRequest request, @Param("app") String app);

    /**
     * 获取用户功能属性
     *
     * @param request 请求
     * @param app     应用
     * @return 用户功能属性
     */
    @RequestLine("POST /exmail/useroption/get?app={app}")
    @Headers(HEAD)
    UserOptionResponse getUserOption(GetUserOptionRequest request, @Param("app") String app);

    /**
     * 更改用户功能属性
     *
     * @param request 请求
     * @param app     应用
     * @return 操作结果
     */
    @RequestLine("POST /exmail/useroption/update?app={app}")
    @Headers(HEAD)
    BaseResponse updateUserOption(UpdateUserOptionRequest request, @Param("app") String app);

    /**
     * 获取邮件未读数
     *
     * @param request 请求
     * @param app     应用
     * @return 未读邮件数量
     */
    @RequestLine("POST /exmail/mail/get_newcount?app={app}")
    @Headers(HEAD)
    MailUnreadCountResponse getMailUnreadCount(GetMailUnreadCountRequest request, @Param("app") String app);

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 分配结果
     */
    @RequestLine("POST /exmail/vip/batch_add?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 撤销分配结果
     */
    @RequestLine("POST /exmail/vip/batch_del?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @RequestLine("POST /exmail/vip/list?app={app}")
    @Headers(HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @Param("app") String app);
}
