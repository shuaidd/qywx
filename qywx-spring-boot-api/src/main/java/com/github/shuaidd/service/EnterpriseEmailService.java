package com.github.shuaidd.service;

import com.github.shuaidd.client.EnterpriseEmailClient;
import com.github.shuaidd.response.exmail.*;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.resquest.exmail.*;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import com.github.shuaidd.exception.ParamCheckException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 邮箱及群组管理
 */
@Service
public class EnterpriseEmailService extends AbstractBaseService {

    /**
     * 高级功能账号单次批量操作人数上限
     */
    private static final int MAX_VIP_BATCH_USER_COUNT = 100;

    /**
     * 高级功能账号列表单页数据上限
     */
    private static final int MAX_VIP_LIST_LIMIT = 200;

    /**
     * 单封邮件附件数量上限
     */
    private static final int MAX_EMAIL_ATTACHMENT_COUNT = 200;

    /**
     * 日程管理员数量上限
     */
    private static final int MAX_SCHEDULE_ADMIN_COUNT = 3;

    /**
     * 会议主持人数量上限
     */
    private static final int MAX_MEETING_HOST_COUNT = 10;

    /**
     * 收件箱列表单页数据上限
     */
    private static final int MAX_MAIL_LIST_LIMIT = 1000;


    private final EnterpriseEmailClient emailClient;

    public EnterpriseEmailService(EnterpriseEmailClient emailClient) {
        this.emailClient = emailClient;
    }

    /**
     * 创建邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void createMailGroup(MailGroupRequest request, String applicationName) {
        emailClient.createMailGroup(request, applicationName);
    }

    /**
     * 更新邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updateMailGroup(MailGroupRequest request, String applicationName) {
        emailClient.updateMailGroup(request, applicationName);
    }

    /**
     * 删除邮件群组
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void deleteMailGroup(DeleteMailGroupRequest request, String applicationName) {
        emailClient.deleteMailGroup(request, applicationName);
    }

    /**
     * 获取邮件群组详情
     *
     * @param groupId         群组编号
     * @param applicationName 应用
     * @return 群组详情
     */
    public MailGroupDetailResponse getMailGroupDetail(String groupId, String applicationName) {
        return emailClient.getMailGroupDetail(groupId, applicationName);
    }

    /**
     * @param groupId         群组编号
     * @param fuzzy           1开启模糊搜索，0获取全部邮件群组
     * @param applicationName 应用
     * @return 查询结果
     */
    public SearchMailGroupResponse searchGroup(String groupId, int fuzzy, String applicationName) {
        return emailClient.searchGroup(groupId, fuzzy, applicationName);
    }

    /**
     * 创建业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 业务邮箱ID
     */
    public CreatePublicMailResponse createPublicMail(CreatePublicMailRequest request, String applicationName) {
        return emailClient.createPublicMail(request, applicationName);
    }

    /**
     * 更新业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updatePublicMail(UpdatePublicMailRequest request, String applicationName) {
        emailClient.updatePublicMail(request, applicationName);
    }

    /**
     * 删除业务邮箱
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void deletePublicMail(DeletePublicMailRequest request, String applicationName) {
        emailClient.deletePublicMail(request, applicationName);
    }

    /**
     * 获取业务邮箱详情
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 详情信息
     */
    public MailDetailResponse getMailDetail(GetMailDetailRequest request, String applicationName) {
        return emailClient.mailDetail(request, applicationName);
    }

    /**
     * 发送普通邮件
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void sendNormalEmail(ComposeEmailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkNormalEmail(request);
        emailClient.composeSend(request, applicationName);
    }

    /**
     * 发送日程邮件
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void sendScheduleEmail(ComposeEmailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkScheduleEmail(request);
        emailClient.composeSend(request, applicationName);
    }

    /**
     * 发送会议邮件
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void sendMeetingEmail(ComposeEmailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkMeetingEmail(request);
        emailClient.composeSend(request, applicationName);
    }

    /**
     * 获取收件箱邮件列表
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 邮件列表
     */
    public MailListResponse getMailList(GetMailListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkMailListRequest(request);
        return emailClient.getMailList(request, applicationName);
    }

    /**
     * 获取邮件内容
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 邮件内容
     */
    public ReadMailResponse readMail(ReadMailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkMailReadRequest(request);
        return emailClient.readMail(request, applicationName);
    }

    /**
     * 更新应用邮箱账号
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updateEmailAlias(UpdateEmailAliasRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isBlank(request.getNewEmail())) {
            throw new ParamCheckException("new_email 不能为空，请检查！！！");
        }
        emailClient.updateEmailAlias(request, applicationName);
    }

    /**
     * 查询应用邮箱账号
     *
     * @param applicationName 应用
     * @return 应用邮箱账号及别名列表
     */
    public EmailAliasResponse getEmailAlias(String applicationName) {
        checkApplication(applicationName);
        return emailClient.getEmailAlias(applicationName);
    }

    /**
     * 获取客户端专用密码列表
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 客户端专用密码列表
     */
    public AuthCodeListResponse listAuthCodes(GetAuthCodeListRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || Objects.isNull(request.getId())) {
            throw new ParamCheckException("id 不能为空，请检查！！！");
        }
        return emailClient.getAuthCodeList(request, applicationName);
    }

    /**
     * 删除客户端专用密码
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void deleteAuthCode(DeleteAuthCodeRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || Objects.isNull(request.getId()) || Objects.isNull(request.getAuthCodeId())) {
            throw new ParamCheckException("id 和 auth_code_id 不能为空，请检查！！！");
        }
        emailClient.deleteAuthCode(request, applicationName);
    }

    /**
     * 禁用或启用邮箱账号
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void actEmail(ActEmailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkActEmailRequest(request);
        emailClient.actEmail(request, applicationName);
    }

    /**
     * 获取用户功能属性
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 用户功能属性
     */
    public UserOptionResponse getUserOption(GetUserOptionRequest request, String applicationName) {
        checkApplication(applicationName);
        checkGetUserOptionRequest(request);
        return emailClient.getUserOption(request, applicationName);
    }

    /**
     * 更改用户功能属性
     *
     * @param request         请求
     * @param applicationName 应用
     */
    public void updateUserOption(UpdateUserOptionRequest request, String applicationName) {
        checkApplication(applicationName);
        checkUpdateUserOptionRequest(request);
        emailClient.updateUserOption(request, applicationName);
    }

    /**
     * 获取邮件未读数
     *
     * @param request         请求
     * @param applicationName 应用
     * @return 未读邮件数量
     */
    public MailUnreadCountResponse getMailUnreadCount(GetMailUnreadCountRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        return emailClient.getMailUnreadCount(request, applicationName);
    }

    /**
     * 批量分配高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 分配结果
     */
    public VipBatchOperateResponse batchAddVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipBatchRequest(request);
        return emailClient.batchAddVipAccounts(request, applicationName);
    }

    /**
     * 批量撤销高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 撤销分配结果
     */
    public VipBatchOperateResponse batchDeleteVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipBatchRequest(request);
        return emailClient.batchDeleteVipAccounts(request, applicationName);
    }

    /**
     * 获取高级功能账号列表
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 高级功能账号列表
     */
    public VipAccountListResponse listVipAccounts(VipAccountListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipListRequest(request);
        return emailClient.listVipAccounts(request, applicationName);
    }

    /**
     * 校验普通邮件发送请求
     *
     * @param request 请求
     */
    private void checkNormalEmail(ComposeEmailRequest request) {
        requireRequest(request);
        checkCommonEmail(request);
        checkRequiredRecipients(request.getTo(), "to");
        checkOptionalRecipients(request.getCc(), "cc");
        checkOptionalRecipients(request.getBcc(), "bcc");
    }

    /**
     * 校验日程邮件发送请求
     *
     * @param request 请求
     */
    private void checkScheduleEmail(ComposeEmailRequest request) {
        requireRequest(request);
        checkCommonEmail(request);
        checkRequiredRecipients(request.getTo(), "to");
        checkOptionalRecipients(request.getCc(), "cc");
        checkOptionalRecipients(request.getBcc(), "bcc");
        if (Objects.isNull(request.getSchedule())) {
            throw new ParamCheckException("发送日程邮件时 schedule 不能为空，请检查！！！");
        }
        checkSchedule(request.getSchedule());
        checkUserIdRecipients(request.getSchedule().getScheduleAdmins(), "schedule.schedule_admins",
                false, MAX_SCHEDULE_ADMIN_COUNT);
        checkParticipantUserIds(request.getSchedule().getScheduleAdmins(), request.getTo());
    }

    /**
     * 校验会议邮件发送请求
     *
     * @param request 请求
     */
    private void checkMeetingEmail(ComposeEmailRequest request) {
        requireRequest(request);
        checkCommonEmail(request);
        checkRequiredRecipients(request.getTo(), "to");
        checkOptionalRecipients(request.getCc(), "cc");
        checkOptionalRecipients(request.getBcc(), "bcc");
        if (Objects.isNull(request.getSchedule())) {
            throw new ParamCheckException("发送会议邮件时 schedule 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getMeeting())) {
            throw new ParamCheckException("发送会议邮件时 meeting 不能为空，请检查！！！");
        }
        checkSchedule(request.getSchedule());
        checkUserIdRecipients(request.getMeeting().getHosts(), "meeting.hosts",
                false, MAX_MEETING_HOST_COUNT);
        checkUserIdRecipients(request.getMeeting().getMeetingAdmins(), "meeting.meeting_admins",
                true, 1);
        checkParticipantUserIds(request.getMeeting().getMeetingAdmins(), request.getTo());
        checkMeetingOption(request.getMeeting().getOption());
    }

    /**
     * 校验邮件公共字段
     *
     * @param request 请求
     */
    private void checkCommonEmail(ComposeEmailRequest request) {
        if (StringUtils.isBlank(request.getSubject())) {
            throw new ParamCheckException("subject 不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getContent())) {
            throw new ParamCheckException("content 不能为空，请检查！！！");
        }
        if (StringUtils.isNotBlank(request.getContentType())
                && !"html".equals(request.getContentType()) && !"text".equals(request.getContentType())) {
            throw new ParamCheckException("content_type 仅支持 html 或 text，请检查！！！");
        }
        if (Objects.nonNull(request.getEnableIdTrans())
                && request.getEnableIdTrans() != 0 && request.getEnableIdTrans() != 1) {
            throw new ParamCheckException("enable_id_trans 仅支持 0 或 1，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getAttachmentList())) {
            return;
        }
        if (request.getAttachmentList().size() > MAX_EMAIL_ATTACHMENT_COUNT) {
            throw new ParamCheckException("attachment_list 最多200个，请检查！！！");
        }
        for (ComposeEmailRequest.EmailAttachment attachment : request.getAttachmentList()) {
            if (Objects.isNull(attachment) || StringUtils.isBlank(attachment.getFileName())
                    || StringUtils.isBlank(attachment.getContent())) {
                throw new ParamCheckException("attachment_list 的 fileName 和 content 不能为空，请检查！！！");
            }
        }
    }

    /**
     * 校验日程字段
     *
     * @param schedule 日程信息
     */
    private void checkSchedule(ComposeEmailRequest.EmailSchedule schedule) {
        if (Objects.isNull(schedule.getStartTime()) || Objects.isNull(schedule.getEndTime())) {
            throw new ParamCheckException("schedule 的 start_time 和 end_time 不能为空，请检查！！！");
        }
        if (schedule.getEndTime() < schedule.getStartTime()) {
            throw new ParamCheckException("schedule 的 end_time 不能早于 start_time，请检查！！！");
        }
        if (StringUtils.isNotBlank(schedule.getMethod())
                && !"request".equals(schedule.getMethod()) && !"cancel".equals(schedule.getMethod())) {
            throw new ParamCheckException("schedule.method 仅支持 request 或 cancel，请检查！！！");
        }
        if ("cancel".equals(schedule.getMethod()) && StringUtils.isBlank(schedule.getScheduleId())) {
            throw new ParamCheckException("取消日程时 schedule.schedule_id 不能为空，请检查！！！");
        }
    }

    /**
     * 校验会议基础设置
     *
     * @param option 会议基础设置
     */
    private void checkMeetingOption(ComposeEmailRequest.EmailMeetingOption option) {
        if (Objects.isNull(option)) {
            return;
        }
        if (StringUtils.isNotBlank(option.getPassword()) && !option.getPassword().matches("\\d{4,6}")) {
            throw new ParamCheckException("meeting.option.password 必须为4到6位纯数字，请检查！！！");
        }
        if (Objects.nonNull(option.getAutoRecord())
                && (option.getAutoRecord() < 0 || option.getAutoRecord() > 2)) {
            throw new ParamCheckException("meeting.option.auto_record 取值范围为 0~2，请检查！！！");
        }
        if (Objects.nonNull(option.getEnterRestraint())
                && option.getEnterRestraint() != 0 && option.getEnterRestraint() != 2) {
            throw new ParamCheckException("meeting.option.enter_restraint 仅支持 0 或 2，请检查！！！");
        }
        if (Objects.nonNull(option.getEnableEnterMute())
                && (option.getEnableEnterMute() < 0 || option.getEnableEnterMute() > 2)) {
            throw new ParamCheckException("meeting.option.enable_enter_mute 取值范围为 0~2，请检查！！！");
        }
        if (Objects.nonNull(option.getRemindScope())
                && (option.getRemindScope() < 1 || option.getRemindScope() > 3)) {
            throw new ParamCheckException("meeting.option.remind_scope 取值范围为 1~3，请检查！！！");
        }
        if (Objects.nonNull(option.getWaterMarkType())
                && option.getWaterMarkType() != 0 && option.getWaterMarkType() != 1) {
            throw new ParamCheckException("meeting.option.water_mark_type 仅支持 0 或 1，请检查！！！");
        }
    }

    /**
     * 校验收件箱列表查询请求
     *
     * @param request 请求
     */
    private void checkMailListRequest(GetMailListRequest request) {
        if (Objects.isNull(request) || Objects.isNull(request.getBeginTime()) || Objects.isNull(request.getEndTime())) {
            throw new ParamCheckException("begin_time 和 end_time 不能为空，请检查！！！");
        }
        if (request.getEndTime() < request.getBeginTime()) {
            throw new ParamCheckException("end_time 不能早于 begin_time，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_MAIL_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~1000，请检查！！！");
        }
    }

    /**
     * 校验邮件内容查询请求
     *
     * @param request 请求
     */
    private void checkMailReadRequest(ReadMailRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getMailId())) {
            throw new ParamCheckException("mail_id 不能为空，请检查！！！");
        }
    }

    /**
     * 校验禁用或启用邮箱请求
     *
     * @param request 请求
     */
    private void checkActEmailRequest(ActEmailRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getUserid()) && Objects.isNull(request.getPublicemailId())) {
            throw new ParamCheckException("userid 和 publicemail_id 至少填写一项，请检查！！！");
        }
        if (Objects.isNull(request.getType()) || request.getType() < 1 || request.getType() > 2) {
            throw new ParamCheckException("type 仅支持 1 或 2，请检查！！！");
        }
    }

    /**
     * 校验用户功能属性查询请求
     *
     * @param request 请求
     */
    private void checkGetUserOptionRequest(GetUserOptionRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        if (CollectionUtils.isEmpty(request.getTypeList())) {
            throw new ParamCheckException("type 不能为空，请检查！！！");
        }
        for (Integer type : request.getTypeList()) {
            if (Objects.isNull(type) || type < 1 || type > 4) {
                throw new ParamCheckException("type 取值范围为 1~4，请检查！！！");
            }
        }
    }

    /**
     * 校验用户功能属性更新请求
     *
     * @param request 请求
     */
    private void checkUpdateUserOptionRequest(UpdateUserOptionRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getOption()) || CollectionUtils.isEmpty(request.getOption().getList())) {
            throw new ParamCheckException("option.list 不能为空，请检查！！！");
        }
        for (UpdateUserOptionRequest.OptionItemDTO item : request.getOption().getList()) {
            if (Objects.isNull(item) || Objects.isNull(item.getType())
                    || item.getType() < 1 || item.getType() > 4) {
                throw new ParamCheckException("option.list 的 type 取值范围为 1~4，请检查！！！");
            }
            if (!"0".equals(item.getValue()) && !"1".equals(item.getValue())) {
                throw new ParamCheckException("option.list 的 value 仅支持 0 或 1，请检查！！！");
            }
        }
    }

    /**
     * 校验请求对象
     *
     * @param request 请求
     */
    private void requireRequest(ComposeEmailRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
    }

    /**
     * 校验必填收件人
     *
     * @param recipients 人员信息
     * @param name       字段名称
     */
    private void checkRequiredRecipients(ComposeEmailRequest.EmailRecipients recipients, String name) {
        if (Objects.isNull(recipients)
                || (CollectionUtils.isEmpty(recipients.getEmails()) && CollectionUtils.isEmpty(recipients.getUserids()))) {
            throw new ParamCheckException(name + " 的 emails 和 userids 至少填写一项，请检查！！！");
        }
        checkStringList(recipients.getEmails(), name + ".emails");
        checkStringList(recipients.getUserids(), name + ".userids");
    }

    /**
     * 校验可选收件人
     *
     * @param recipients 人员信息
     * @param name       字段名称
     */
    private void checkOptionalRecipients(ComposeEmailRequest.EmailRecipients recipients, String name) {
        if (Objects.isNull(recipients)) {
            return;
        }
        checkStringList(recipients.getEmails(), name + ".emails");
        checkStringList(recipients.getUserids(), name + ".userids");
    }

    /**
     * 校验只支持 userid 的人员字段
     *
     * @param recipients 人员信息
     * @param name       字段名称
     * @param required   是否必填
     * @param maxSize    数量上限
     */
    private void checkUserIdRecipients(ComposeEmailRequest.EmailRecipients recipients,
                                       String name, boolean required, int maxSize) {
        if (Objects.isNull(recipients)) {
            if (required) {
                throw new ParamCheckException(name + " 不能为空，请检查！！！");
            }
            return;
        }
        if (CollectionUtils.isNotEmpty(recipients.getEmails())) {
            throw new ParamCheckException(name + " 只支持 userids，请检查！！！");
        }
        if (required && CollectionUtils.isEmpty(recipients.getUserids())) {
            throw new ParamCheckException(name + " 不能为空，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(recipients.getUserids()) && recipients.getUserids().size() > maxSize) {
            throw new ParamCheckException(name + " 最多" + maxSize + "个，请检查！！！");
        }
        checkStringList(recipients.getUserids(), name + ".userids");
    }

    /**
     * 校验 userid 人员必须属于邮件参与人
     *
     * @param recipients 人员信息
     * @param to         邮件收件人
     */
    private void checkParticipantUserIds(ComposeEmailRequest.EmailRecipients recipients,
                                         ComposeEmailRequest.EmailRecipients to) {
        if (Objects.isNull(recipients) || CollectionUtils.isEmpty(recipients.getUserids())) {
            return;
        }
        if (Objects.isNull(to) || CollectionUtils.isEmpty(to.getUserids())) {
            throw new ParamCheckException("指定人员必须是 to.userids 中的参与人，请检查！！！");
        }
        for (String userid : recipients.getUserids()) {
            if (!to.getUserids().contains(userid)) {
                throw new ParamCheckException("指定人员必须是 to.userids 中的参与人，请检查！！！");
            }
        }
    }

    /**
     * 校验字符串列表
     *
     * @param values 字符串列表
     * @param name   字段名称
     */
    private void checkStringList(List<String> values, String name) {
        if (CollectionUtils.isEmpty(values)) {
            return;
        }
        for (String value : values) {
            if (StringUtils.isBlank(value)) {
                throw new ParamCheckException(name + " 不能包含空值，请检查！！！");
            }
        }
    }

    /**
     * 校验高级功能账号批量操作请求
     *
     * @param request 请求
     */
    private void checkVipBatchRequest(VipUserBatchRequest request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getUseridList())) {
            throw new ParamCheckException("userid_list 不能为空，请检查！！！");
        }
        if (request.getUseridList().size() > MAX_VIP_BATCH_USER_COUNT) {
            throw new ParamCheckException("userid_list 最多100个，请检查！！！");
        }
        for (String userid : request.getUseridList()) {
            if (StringUtils.isBlank(userid)) {
                throw new ParamCheckException("userid_list 不能包含空userid，请检查！！！");
            }
        }
    }

    /**
     * 校验高级功能账号列表查询请求
     *
     * @param request 请求
     */
    private void checkVipListRequest(VipAccountListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_VIP_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~200，请检查！！！");
        }
    }
}
