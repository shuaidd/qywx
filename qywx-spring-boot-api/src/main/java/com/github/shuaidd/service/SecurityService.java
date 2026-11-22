package com.github.shuaidd.service;

import com.github.shuaidd.client.SecurityClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.vip.VipAccountListResponse;
import com.github.shuaidd.response.vip.VipBatchOperateResponse;
import com.github.shuaidd.response.vip.VipJobResultResponse;
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
