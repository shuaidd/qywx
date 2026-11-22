package com.github.shuaidd.service;

import com.github.shuaidd.client.AdvancedFeatureClient;
import com.github.shuaidd.exception.ParamCheckException;
import com.github.shuaidd.response.BaseResponse;
import com.github.shuaidd.response.advanced.ApprovalInfoResponse;
import com.github.shuaidd.response.advanced.ApplyIdListResponse;
import com.github.shuaidd.resquest.advanced.ApprovalNode;
import com.github.shuaidd.resquest.advanced.ApprovalProcessList;
import com.github.shuaidd.resquest.advanced.GetApplyIdListRequest;
import com.github.shuaidd.resquest.advanced.GetApprovalInfoRequest;
import com.github.shuaidd.resquest.advanced.SetApprovalDetailRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 高级功能成员申请管理服务
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Service
public class AdvancedFeatureService extends AbstractBaseService {

    /**
     * 当前或已处理审批人列表上限
     */
    private static final int MAX_APPROVER_COUNT = 100;

    /**
     * 申请单ID分页上限
     */
    private static final int MAX_APPLY_ID_LIST_LIMIT = 200;

    /**
     * 申请的高级账号类型：1-邮件；2-文档；3-微盘；4-会议
     */
    private static final Set<Integer> VALID_BUSINESS_TYPES = new HashSet<>(Arrays.asList(1, 2, 3, 4));

    /**
     * 申请单类型：0-所有；1-仅api单；2-非api申请单
     */
    private static final Set<Integer> VALID_REQ_TYPES = new HashSet<>(Arrays.asList(0, 1, 2));

    private final AdvancedFeatureClient advancedFeatureClient;

    public AdvancedFeatureService(AdvancedFeatureClient advancedFeatureClient) {
        this.advancedFeatureClient = advancedFeatureClient;
    }

    /**
     * 设置审批单审批信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 基础响应
     */
    public BaseResponse setApprovalDetail(SetApprovalDetailRequest request, String applicationName) {
        checkApplication(applicationName);
        checkSetApprovalDetailRequest(request);
        return advancedFeatureClient.setApprovalDetail(request, applicationName);
    }

    /**
     * 批量获取申请单ID
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 申请单ID列表
     */
    public ApplyIdListResponse getApplyIdList(GetApplyIdListRequest request, String applicationName) {
        checkApplication(applicationName);
        checkGetApplyIdListRequest(request);
        return advancedFeatureClient.getApplyIdList(request, applicationName);
    }

    /**
     * 获取申请单详细信息
     *
     * @param request         请求
     * @param applicationName 应用名称
     * @return 申请单详细信息
     */
    public ApprovalInfoResponse getApprovalInfo(GetApprovalInfoRequest request, String applicationName) {
        checkApplication(applicationName);
        if (Objects.isNull(request) || StringUtils.isBlank(request.getApplyId())) {
            throw new ParamCheckException("apply_id 不能为空，请检查！！！");
        }
        return advancedFeatureClient.getApprovalInfo(request, applicationName);
    }

    private void checkSetApprovalDetailRequest(SetApprovalDetailRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getApplyId())) {
            throw new ParamCheckException("apply_id 不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getApprovalId())) {
            throw new ParamCheckException("approval_id 不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getApprovalStatus())) {
            throw new ParamCheckException("approval_status 不能为空，请检查！！！");
        }
        if (StringUtils.isBlank(request.getApprovalUrl())
                || !(request.getApprovalUrl().startsWith("http://") || request.getApprovalUrl().startsWith("https://"))) {
            throw new ParamCheckException("approval_url 必须以 http:// 或 https:// 开头，请检查！！！");
        }
        ApprovalProcessList processList = request.getProcessList();
        if (Objects.isNull(processList) || CollectionUtils.isEmpty(processList.getNodeList())) {
            throw new ParamCheckException("process_list.node_list 不能为空，请检查！！！");
        }
        for (ApprovalNode node : processList.getNodeList()) {
            checkApprovalNode(node);
        }
    }

    private void checkApprovalNode(ApprovalNode node) {
        if (Objects.isNull(node)) {
            throw new ParamCheckException("process_list.node_list 节点不能为空，请检查！！！");
        }
        if (Objects.isNull(node.getNodeApvStatus())) {
            throw new ParamCheckException("process_list.node_list.node_apv_status 不能为空，请检查！！！");
        }
        if (Objects.isNull(node.getNodeApvRel())) {
            throw new ParamCheckException("process_list.node_list.node_apv_rel 不能为空，请检查！！！");
        }
        List<String> currentApprovers = node.getCurrentApprovers();
        List<String> completedApprovers = node.getCompletedApprovers();
        if (CollectionUtils.isNotEmpty(currentApprovers) && currentApprovers.size() > MAX_APPROVER_COUNT) {
            throw new ParamCheckException("current_approvers 最多100个，请检查！！！");
        }
        if (CollectionUtils.isNotEmpty(completedApprovers) && completedApprovers.size() > MAX_APPROVER_COUNT) {
            throw new ParamCheckException("completed_approvers 最多100个，请检查！！！");
        }
        if (CollectionUtils.isEmpty(currentApprovers) && CollectionUtils.isEmpty(completedApprovers)) {
            throw new ParamCheckException("current_approvers 和 completed_approvers 不能同时为空，请检查！！！");
        }
    }

    private void checkGetApplyIdListRequest(GetApplyIdListRequest request) {
        if (Objects.isNull(request)) {
            throw new ParamCheckException("请求参数不能为空，请检查！！！");
        }
        if (Objects.isNull(request.getBusinessType()) || !VALID_BUSINESS_TYPES.contains(request.getBusinessType())) {
            throw new ParamCheckException("business_type 只支持 1-邮件、2-文档、3-微盘、4-会议，请检查！！！");
        }
        if (StringUtils.isBlank(request.getUserid())) {
            throw new ParamCheckException("userid 不能为空，请检查！！！");
        }
        if (Objects.nonNull(request.getLimit())
                && (request.getLimit() < 1 || request.getLimit() > MAX_APPLY_ID_LIST_LIMIT)) {
            throw new ParamCheckException("limit 取值范围为 1~200，请检查！！！");
        }
        if (Objects.nonNull(request.getReqType()) && !VALID_REQ_TYPES.contains(request.getReqType())) {
            throw new ParamCheckException("req_type 只支持 0-所有、1-仅api单、2-非api申请单，请检查！！！");
        }
    }
}