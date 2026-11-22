package com.github.shuaidd.service;

import com.github.shuaidd.client.SecurityClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.security.AdminOperLogListResponse;
import com.github.shuaidd.response.security.MemberOperLogListResponse;
import com.github.shuaidd.response.security.ServerDomainIpResponse;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.response.vip.VipJobResultResponse;
import com.github.shuaidd.resquest.security.SecurityOperLogListRequest;
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
