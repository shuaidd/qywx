package com.github.shuaidd.client;

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
import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * 安全管理
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public interface SecurityClient extends CommonClient {

    /**
     * 批量分配高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @RequestLine("POST /security/vip/submit_batch_add_job?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse submitBatchAddVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 查询批量分配高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量分配任务结果
     */
    @RequestLine("POST /security/vip/batch_add_job_result?app={app}")
    @Headers(HEAD)
    VipJobResultResponse getBatchAddVipJobResult(VipJobResultRequest request, @Param("app") String app);

    /**
     * 批量撤销高级功能账号
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量任务ID
     */
    @RequestLine("POST /security/vip/submit_batch_del_job?app={app}")
    @Headers(HEAD)
    VipBatchOperateResponse submitBatchDeleteVipAccounts(VipUserBatchRequest request, @Param("app") String app);

    /**
     * 查询批量撤销高级功能账号结果
     *
     * @param request 请求
     * @param app     应用名
     * @return 批量撤销任务结果
     */
    @RequestLine("POST /security/vip/batch_del_job_result?app={app}")
    @Headers(HEAD)
    VipJobResultResponse getBatchDeleteVipJobResult(VipJobResultRequest request, @Param("app") String app);

    /**
     * 获取高级功能账号列表
     *
     * @param request 请求
     * @param app     应用名
     * @return 高级功能账号列表
     */
    @RequestLine("POST /security/vip/list?app={app}")
    @Headers(HEAD)
    VipAccountListResponse listVipAccounts(VipAccountListRequest request, @Param("app") String app);

    /**
     * 获取成员操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 成员操作记录列表
     */
    @RequestLine("POST /security/member_oper_log/list?app={app}")
    @Headers(HEAD)
    MemberOperLogListResponse listMemberOperLog(SecurityOperLogListRequest request, @Param("app") String app);

    /**
     * 获取管理端操作日志
     *
     * @param request 请求
     * @param app     应用名
     * @return 管理端操作日志列表
     */
    @RequestLine("POST /security/admin_oper_log/list?app={app}")
    @Headers(HEAD)
    AdminOperLogListResponse listAdminOperLog(SecurityOperLogListRequest request, @Param("app") String app);

    /**
     * 获取企业微信域名IP信息
     *
     * @param app 应用名
     * @return 企业微信域名IP信息
     */
    @RequestLine("GET /security/get_server_domain_ip?app={app}")
    @Headers(HEAD)
    ServerDomainIpResponse getServerDomainIp(@Param("app") String app);

    /**
     * 获取文件操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 文件操作记录列表
     */
    @RequestLine("POST /security/get_file_oper_record?app={app}")
    @Headers(HEAD)
    FileOperRecordListResponse listFileOperRecords(FileOperRecordListRequest request, @Param("app") String app);

    /**
     * 导入可信企业设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备导入结果列表
     */
    @RequestLine("POST /security/trustdevice/import?app={app}")
    @Headers(HEAD)
    TrustDeviceImportResponse importTrustDevices(TrustDeviceImportRequest request, @Param("app") String app);

    /**
     * 获取可信设备信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 可信设备信息列表
     */
    @RequestLine("POST /security/trustdevice/list?app={app}")
    @Headers(HEAD)
    TrustDeviceListResponse listTrustDevices(TrustDeviceListRequest request, @Param("app") String app);

    /**
     * 获取成员使用设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 成员使用设备列表
     */
    @RequestLine("POST /security/trustdevice/get_by_user?app={app}")
    @Headers(HEAD)
    TrustDeviceGetByUserResponse getTrustDevicesByUser(TrustDeviceGetByUserRequest request, @Param("app") String app);

    /**
     * 删除可信设备信息
     *
     * @param request 请求
     * @param app     应用名
     * @return 删除结果
     */
    @RequestLine("POST /security/trustdevice/delete?app={app}")
    @Headers(HEAD)
    TrustDeviceDeleteResponse deleteTrustDevices(TrustDeviceDeleteRequest request, @Param("app") String app);

    /**
     * 确认可信设备
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备确认结果列表
     */
    @RequestLine("POST /security/trustdevice/approve?app={app}")
    @Headers(HEAD)
    TrustDeviceOperateResponse approveTrustDevices(TrustDeviceCodeListRequest request, @Param("app") String app);

    /**
     * 驳回可信设备申请
     *
     * @param request 请求
     * @param app     应用名
     * @return 设备驳回结果列表
     */
    @RequestLine("POST /security/trustdevice/reject?app={app}")
    @Headers(HEAD)
    TrustDeviceOperateResponse rejectTrustDevices(TrustDeviceCodeListRequest request, @Param("app") String app);

    /**
     * 获取截屏操作记录
     *
     * @param request 请求
     * @param app     应用名
     * @return 截屏操作记录列表
     */
    @RequestLine("POST /security/get_screen_oper_record?app={app}")
    @Headers(HEAD)
    ScreenOperRecordListResponse listScreenOperRecords(ScreenOperRecordListRequest request, @Param("app") String app);
}
