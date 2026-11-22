package com.github.shuaidd.service;

import com.github.shuaidd.client.SecurityClient;
import com.github.shuaidd.exception.ParamCheckException;
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
import com.github.shuaidd.resquest.security.TrustDeviceImportItem;
import com.github.shuaidd.resquest.security.TrustDeviceListRequest;
import com.github.shuaidd.resquest.vip.VipAccountListRequest;
import com.github.shuaidd.resquest.vip.VipJobResultRequest;
import com.github.shuaidd.resquest.vip.VipUserBatchRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 安全管理服务
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Service
public class SecurityService extends AbstractBaseService {

    /**
     * 高级功能账号单次批量操作人数上限
     */
    private static final int MAX_VIP_BATCH_USER_COUNT = 100;

    /**
     * 高级功能账号列表单页数据上限
     */
    private static final int MAX_VIP_LIST_LIMIT = 200;

    /**
     * 安全管理操作日志单页数据上限
     */
    private static final int MAX_SECURITY_OPER_LOG_LIMIT = 400;

    /**
     * 安全管理操作日志最大查询跨度：7天
     */
    private static final long MAX_SECURITY_OPER_LOG_SPAN_SECONDS = 7L * 24 * 60 * 60;

    /**
     * 文件和截屏操作记录最大查询跨度：14天
     */
    private static final long MAX_SECURITY_RECORD_SPAN_SECONDS = 14L * 24 * 60 * 60;

    /**
     * 文件和截屏操作记录单页数据上限
     */
    private static final int MAX_SECURITY_RECORD_LIMIT = 1000;

    /**
     * 文件和截屏操作记录 userid 单次过滤上限
     */
    private static final int MAX_SECURITY_RECORD_USER_COUNT = 100;

    /**
     * 截屏操作记录部门单次过滤上限
     */
    private static final int MAX_SECURITY_RECORD_DEPARTMENT_COUNT = 100;

    /**
     * 可信设备单次批量处理上限
     */
    private static final int MAX_TRUST_DEVICE_COUNT = 100;

    /**
     * 可信设备列表单页数据上限
     */
    private static final int MAX_TRUST_DEVICE_LIST_LIMIT = 100;

    /**
     * 可信设备 Windows 系统类型
     */
    private static final String TRUST_DEVICE_SYSTEM_WINDOWS = "Windows";

    /**
     * 可信设备 Mac 系统类型
     */
    private static final String TRUST_DEVICE_SYSTEM_MAC = "Mac";

    private final SecurityClient securityClient;

    public SecurityService(SecurityClient securityClient) {
        this.securityClient = securityClient;
    }

    /**
     * 批量分配高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量任务ID
     */
    public VipBatchOperateResponse submitBatchAddVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipUserBatchRequest(request);
        return securityClient.submitBatchAddVipAccounts(request, applicationName);
    }

    /**
     * 查询批量分配高级功能账号结果
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量分配任务结果
     */
    public VipJobResultResponse getBatchAddVipJobResult(VipJobResultRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipJobResultRequest(request);
        return securityClient.getBatchAddVipJobResult(request, applicationName);
    }

    /**
     * 批量撤销高级功能账号
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量任务ID
     */
    public VipBatchOperateResponse submitBatchDeleteVipAccounts(VipUserBatchRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipUserBatchRequest(request);
        return securityClient.submitBatchDeleteVipAccounts(request, applicationName);
    }

    /**
     * 查询批量撤销高级功能账号结果
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 批量撤销任务结果
     */
    public VipJobResultResponse getBatchDeleteVipJobResult(VipJobResultRequest request, String applicationName) {
        checkApplication(applicationName);
        checkVipJobResultRequest(request);
        return securityClient.getBatchDeleteVipJobResult(request, applicationName);
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
        checkVipAccountListRequest(request);
        return securityClient.listVipAccounts(request, applicationName);
    }

    /**
     * 获取成员操作记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 成员操作记录列表
     */
    public MemberOperLogListResponse listMemberOperLog(SecurityOperLogListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkSecurityOperLogListRequest(request);
        return securityClient.listMemberOperLog(request, applicationName);
    }

    /**
     * 获取管理端操作日志
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 管理端操作日志列表
     */
    public AdminOperLogListResponse listAdminOperLog(SecurityOperLogListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkSecurityOperLogListRequest(request);
        return securityClient.listAdminOperLog(request, applicationName);
    }

    /**
     * 获取企业微信域名IP信息
     *
     * @param applicationName 应用名称
     * @return 企业微信域名IP信息
     */
    public ServerDomainIpResponse getServerDomainIp(String applicationName) {
        checkApplication(applicationName);
        return securityClient.getServerDomainIp(applicationName);
    }

    /**
     * 获取文件操作记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 文件操作记录列表
     */
    public FileOperRecordListResponse listFileOperRecords(FileOperRecordListRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        checkSecurityOperRecordBaseRequest(request.getStartTime(), request.getEndTime(),
                request.getUseridList(), request.getCursor(), request.getLimit());
        return securityClient.listFileOperRecords(request, applicationName);
    }

    /**
     * 导入可信企业设备
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 设备导入结果列表
     */
    public TrustDeviceImportResponse importTrustDevices(TrustDeviceImportRequest request, String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceImportRequest(request);
        return securityClient.importTrustDevices(request, applicationName);
    }

    /**
     * 获取可信设备信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 可信设备信息列表
     */
    public TrustDeviceListResponse listTrustDevices(TrustDeviceListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceListRequest(request);
        return securityClient.listTrustDevices(request, applicationName);
    }

    /**
     * 获取成员使用设备
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 成员使用设备列表
     */
    public TrustDeviceGetByUserResponse getTrustDevicesByUser(TrustDeviceGetByUserRequest request,
                                                              String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceGetByUserRequest(request);
        return securityClient.getTrustDevicesByUser(request, applicationName);
    }

    /**
     * 删除可信设备信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 删除结果
     */
    public TrustDeviceDeleteResponse deleteTrustDevices(TrustDeviceDeleteRequest request, String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceDeleteRequest(request);
        return securityClient.deleteTrustDevices(request, applicationName);
    }

    /**
     * 确认可信设备
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 设备确认结果列表
     */
    public TrustDeviceOperateResponse approveTrustDevices(TrustDeviceCodeListRequest request,
                                                          String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceCodeListRequest(request);
        return securityClient.approveTrustDevices(request, applicationName);
    }

    /**
     * 驳回可信设备申请
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 设备驳回结果列表
     */
    public TrustDeviceOperateResponse rejectTrustDevices(TrustDeviceCodeListRequest request,
                                                         String applicationName) {
        checkApplication(applicationName);
        checkTrustDeviceCodeListRequest(request);
        return securityClient.rejectTrustDevices(request, applicationName);
    }

    /**
     * 获取截屏操作记录
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 截屏操作记录列表
     */
    public ScreenOperRecordListResponse listScreenOperRecords(ScreenOperRecordListRequest request,
                                                              String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        checkSecurityOperRecordBaseRequest(request.getStartTime(), request.getEndTime(),
                request.getUseridList(), request.getCursor(), request.getLimit());
        checkDepartmentIdList(request.getDepartmentIdList());
        if (Objects.nonNull(request.getScreenShotType())
                && (request.getScreenShotType() < 1 || request.getScreenShotType() > 6)) {
            throw new ParamCheckException("screen_shot_type 取值范围为 1~6，请检查！！！");
        }
        return securityClient.listScreenOperRecords(request, applicationName);
    }

    private void checkSecurityOperRecordBaseRequest(Long startTime, Long endTime, List<String> useridList,
                                                    String cursor, Integer limit) {
        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            throw new ParamCheckException("start_time 和 end_time 不能为空，请检查！！！");
        }
        if (endTime <= startTime) {
            throw new ParamCheckException("end_time 必须大于 start_time，请检查！！！");
        }
        if (endTime - startTime > MAX_SECURITY_RECORD_SPAN_SECONDS) {
            throw new ParamCheckException("start_time 和 end_time 跨度不能超过14天，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(useridList)) {
            if (useridList.size() > MAX_SECURITY_RECORD_USER_COUNT) {
                throw new ParamCheckException("userid_list 最多100个，请检查！！！");
            }
            for (String userid : useridList) {
                if (StringUtils.isBlank(userid)) {
                    throw new ParamCheckException("userid_list 不能包含空userid，请检查！！！");
                }
            }
        }
        if (Objects.nonNull(cursor) && StringUtils.isBlank(cursor)) {
            throw new ParamCheckException("cursor 不能为空白，请检查！！！");
        }
        if (Objects.nonNull(limit) && (limit < 1 || limit > MAX_SECURITY_RECORD_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~1000，请检查！！！");
        }
    }

    private void checkDepartmentIdList(List<Integer> departmentIdList) {
        if (CollectionUtils.isNotEmpty(departmentIdList)
                && departmentIdList.size() > MAX_SECURITY_RECORD_DEPARTMENT_COUNT) {
            throw new ParamCheckException("department_id_list 最多100个，请检查！！！");
        }
    }

    private void checkTrustDeviceImportRequest(TrustDeviceImportRequest request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getDeviceList())) {
            throw new ParamCheckException("device_list 不能为空，请检查！！！");
        }
        if (request.getDeviceList().size() > MAX_TRUST_DEVICE_COUNT) {
            throw new ParamCheckException("device_list 最多100条，请检查！！！");
        }
        for (TrustDeviceImportItem device : request.getDeviceList()) {
            checkTrustDeviceImportItem(device);
        }
    }

    private void checkTrustDeviceImportItem(TrustDeviceImportItem device) {
        if (Objects.isNull(device)) {
            throw new ParamCheckException("device_list 不能包含空设备，请检查！！！");
        }
        if (TRUST_DEVICE_SYSTEM_WINDOWS.equals(device.getSystem())) {
            if (CollectionUtils.isEmpty(device.getMacAddr())) {
                throw new ParamCheckException("Windows设备的 mac_addr 不能为空，请检查！！！");
            }
            if (device.getMacAddr().size() > MAX_TRUST_DEVICE_COUNT) {
                throw new ParamCheckException("设备 mac_addr 最多100个，请检查！！！");
            }
            if (CollectionUtils.isNotEmpty(device.getHarddiskUuid())
                    && device.getHarddiskUuid().size() > MAX_TRUST_DEVICE_COUNT) {
                throw new ParamCheckException("设备 harddisk_uuid 最多100个，请检查！！！");
            }
        } else if (TRUST_DEVICE_SYSTEM_MAC.equals(device.getSystem())) {
            if (StringUtils.isBlank(device.getSeqNo())) {
                throw new ParamCheckException("Mac设备的 seq_no 不能为空，请检查！！！");
            }
            if (CollectionUtils.isNotEmpty(device.getMacAddr())
                    && device.getMacAddr().size() > MAX_TRUST_DEVICE_COUNT) {
                throw new ParamCheckException("设备 mac_addr 最多100个，请检查！！！");
            }
        } else {
            throw new ParamCheckException("system 仅支持 Windows 或 Mac，请检查！！！");
        }
    }

    private void checkTrustDeviceListRequest(TrustDeviceListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        checkTrustDeviceType(request.getType());
        if (Objects.nonNull(request.getCursor()) && StringUtils.isBlank(request.getCursor())) {
            throw new ParamCheckException("cursor 不能为空白，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_TRUST_DEVICE_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~100，请检查！！！");
        }
    }

    private void checkTrustDeviceGetByUserRequest(TrustDeviceGetByUserRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getLastLoginUserid())) {
            throw new ParamCheckException("last_login_userid 不能为空，请检查！！！");
        }
        checkTrustDeviceType(request.getType());
    }

    private void checkTrustDeviceDeleteRequest(TrustDeviceDeleteRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        checkTrustDeviceType(request.getType());
        checkTrustDeviceCodeList(request.getDeviceCodeList());
    }

    private void checkTrustDeviceCodeListRequest(TrustDeviceCodeListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        checkTrustDeviceCodeList(request.getDeviceCodeList());
    }

    private void checkTrustDeviceType(Integer type) {
        if (Objects.isNull(type) || type < 1 || type > 3) {
            throw new ParamCheckException("type 取值范围为 1~3，请检查！！！");
        }
    }

    private void checkTrustDeviceCodeList(List<String> deviceCodeList) {
        if (CollectionUtils.isEmpty(deviceCodeList)) {
            throw new ParamCheckException("device_code_list 不能为空，请检查！！！");
        }
        if (deviceCodeList.size() > MAX_TRUST_DEVICE_COUNT) {
            throw new ParamCheckException("device_code_list 最多100个，请检查！！！");
        }
        for (String deviceCode : deviceCodeList) {
            if (StringUtils.isBlank(deviceCode)) {
                throw new ParamCheckException("device_code_list 不能包含空设备编码，请检查！！！");
            }
        }
    }

    private void checkSecurityOperLogListRequest(SecurityOperLogListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getStartTime()) || Objects.isNull(request.getEndTime())) {
            throw new ParamCheckException("start_time 和 end_time 不能为空，请检查！！！");
        }
        if (request.getEndTime() <= request.getStartTime()) {
            throw new ParamCheckException("end_time 必须大于 start_time，请检查！！！");
        }
        if (request.getEndTime() - request.getStartTime() > MAX_SECURITY_OPER_LOG_SPAN_SECONDS) {
            throw new ParamCheckException("start_time 和 end_time 跨度不能超过7天，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_SECURITY_OPER_LOG_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~400，请检查！！！");
        }
        if (Objects.nonNull(request.getCursor()) && StringUtils.isBlank(request.getCursor())) {
            throw new ParamCheckException("cursor 不能为空白，请检查！！！");
        }
        if (Objects.nonNull(request.getUserid()) && StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空白，请检查！！！");
        }
    }

    private void checkVipUserBatchRequest(VipUserBatchRequest request) {
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getUseridList())) {
            throw new ParamCheckException("userid_list 不能为空，请检查！！！");
        }
        if (request.getUseridList().size() > MAX_VIP_BATCH_USER_COUNT) {
            throw new ParamCheckException("userid_list 最多100个，请检查！！！");
        }
        checkVipUserIds(request.getUseridList());
    }

    private void checkVipJobResultRequest(VipJobResultRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getJobId())) {
            throw new ParamCheckException("jobid 不能为空，请检查！！！");
        }
    }

    private void checkVipAccountListRequest(VipAccountListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_VIP_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~200，请检查！！！");
        }
    }

    private void checkVipUserIds(List<String> useridList) {
        for (String userid : useridList) {
            if (StringUtils.isBlank(userid)) {
                throw new ParamCheckException("userid_list 不能包含空userid，请检查！！！");
            }
        }
    }
}
