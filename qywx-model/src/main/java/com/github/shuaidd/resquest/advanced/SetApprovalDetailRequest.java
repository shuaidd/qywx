package com.github.shuaidd.resquest.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置审批单审批信息请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class SetApprovalDetailRequest {
    /**
     * 申请id
     **/
    @JsonProperty("apply_id")
    private String applyId;

    /**
     * 审批id；应用生成审批id后，审批id和申请id一一对应，不可改变
     **/
    @JsonProperty("approval_id")
    private String approvalId;

    /**
     * 审批单状态：1-审批中；2-已驳回；3-已同意；101-已撤销
     **/
    @JsonProperty("approval_status")
    private Integer approvalStatus;

    /**
     * 审批单跳转链接，须以http://或https://开头
     **/
    @JsonProperty("approval_url")
    private String approvalUrl;

    /**
     * 审批流程信息；如需变更审批节点信息，需要全量节点都传入
     **/
    @JsonProperty("process_list")
    private ApprovalProcessList processList;
}