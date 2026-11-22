package com.github.shuaidd.client;

import com.github.shuaidd.client.config.WeChatConfiguration;
import com.github.shuaidd.response.security.AdminOperLogListResponse;
import com.github.shuaidd.response.security.FileOperRecordListResponse;
import com.github.shuaidd.response.security.MemberOperLogListResponse;
import com.github.shuaidd.response.security.ScreenOperRecordListResponse;
import com.github.shuaidd.response.security.ServerDomainIpResponse;
import com.github.shuaidd.response.security.TrustDeviceDeleteResponse;
import com.github.shuaidd.response.security.TrustDeviceGetByUserResponse;
import com.github.shuaidd.response.security.TrustDeviceImportResponse;
import com.github.shuaidd.response.security.TrustDeviceListResponse;
import com.github.shuaidd.response.security.TrustDeviceOperateResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.response.vip.VipJobResultResponse;
import com.github.shuaidd.resquest.security.FileOperRecordListRequest;
import com.github.shuaidd.resquest.security.ScreenOperRecordListRequest;
import com.github.shuaidd.resquest.security.SecurityOperLogListRequest;
import com.github.shuaidd.resquest.security.TrustDeviceCodeListRequest;
import com.github.shuaidd.resquest.security.TrustDeviceDeleteRequest;
import com.github.shuaidd.resquest.security.TrustDeviceGetByUserRequest;
import com.github.shuaidd.resquest.security.TrustDeviceImportRequest;
import com.github.shuaidd.resquest.security.TrustDeviceListRequest;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipJobResultRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 安全管理
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@FeignClient(name = "SecurityClient", url = "${qywx.url:https://qyapi.weixin.qq.com}", path = "${qywx.public-path:cgi-bin}", configuration = WeChatConfiguration.class)
public interface SecurityClient extends CommonClient {

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @PostMapping(value = "security/vip/submit_batch_add_job", headers = HEAD)
    VipBatchOperateResponse submitBatchAddVipAccounts(VipUserBatchRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询批量分配高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量分配任务结果
     */
    @PostMapping(value = "security/vip/batch_add_job_result", headers = HEAD)
    VipJobResultResponse getBatchAddVipJobResult(VipJobResultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @PostMapping(value = "security/vip/submit_batch_del_job", headers = HEAD)
    VipBatchOperateResponse submitBatchDeleteVipAccounts(VipUserBatchRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 查询批量撤销高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量撤销任务结果
     */
    @PostMapping(value = "security/vip/batch_del_job_result", headers = HEAD)
    VipJobResultResponse getBatchDeleteVipJobResult(VipJobResultRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @PostMapping(value = "security/vip/list", headers = HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 成员操作记录列表
     */
    @PostMapping(value = "security/member_oper_log/list", headers = HEAD)
    MemberOperLogListResponse listMemberOperLog(SecurityOperLogListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取管理端操作日志
     *
     * @param request 请求
     * @param app     应用名
     * @return 管理端操作日志列表
     */
    @PostMapping(value = "security/admin_oper_log/list", headers = HEAD)
    AdminOperLogListResponse listAdminOperLog(SecurityOperLogListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取企业微信域名IP信息
     *
     * @param app 应用名
     * @return 企业微信域名IP信息
     */
    @GetMapping(value = "security/get_server_domain_ip", headers = HEAD)
    ServerDomainIpResponse getServerDomainIp(@RequestParam(HEAD_KEY) String app);

    /**
     * 获取文件操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件操作记录列表
     */
    @PostMapping(value = "security/get_file_oper_record", headers = HEAD)
    FileOperRecordListResponse listFileOperRecords(FileOperRecordListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 导入可信企业设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备导入结果列表
     */
    @PostMapping(value = "security/trustdevice/import", headers = HEAD)
    TrustDeviceImportResponse importTrustDevices(TrustDeviceImportRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取可信设备信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 可信设备信息列表
     */
    @PostMapping(value = "security/trustdevice/list", headers = HEAD)
    TrustDeviceListResponse listTrustDevices(TrustDeviceListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取成员使用设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 成员使用设备列表
     */
    @PostMapping(value = "security/trustdevice/get_by_user", headers = HEAD)
    TrustDeviceGetByUserResponse getTrustDevicesByUser(TrustDeviceGetByUserRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 删除可信设备信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除结果
     */
    @PostMapping(value = "security/trustdevice/delete", headers = HEAD)
    TrustDeviceDeleteResponse deleteTrustDevices(TrustDeviceDeleteRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 确认可信设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备确认结果列表
     */
    @PostMapping(value = "security/trustdevice/approve", headers = HEAD)
    TrustDeviceOperateResponse approveTrustDevices(TrustDeviceCodeListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 驳回可信设备申请
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备驳回结果列表
     */
    @PostMapping(value = "security/trustdevice/reject", headers = HEAD)
    TrustDeviceOperateResponse rejectTrustDevices(TrustDeviceCodeListRequest request, @RequestParam(HEAD_KEY) String app);

    /**
     * 获取截屏操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 截屏操作记录列表
     */
    @PostMapping(value = "security/get_screen_oper_record", headers = HEAD)
    ScreenOperRecordListResponse listScreenOperRecords(ScreenOperRecordListRequest request, @RequestParam(HEAD_KEY) String app);
}
