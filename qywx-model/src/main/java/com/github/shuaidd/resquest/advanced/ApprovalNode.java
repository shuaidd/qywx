package com.github.shuaidd.resquest.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 高级功能申请单审批节点
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApprovalNode {
    /**
     * 当前审批节点待处理人列表，最多100个；与已处理人列表不可同时为空
     **/
    @JsonProperty("current_approvers")
    private List<String> currentApprovers;

    /**
     * 当前审批节点已处理人列表，最多100个；与待处理人列表不可同时为空
     **/
    @JsonProperty("completed_approvers")
    private List<String> completedApprovers;

    /**
     * 审批节点状态：1-审批中；2-已驳回；3-已同意；101-已撤销；102-未到流程
     **/
    @JsonProperty("node_apv_status")
    private Integer nodeApvStatus;

    /**
     * 审批节点多人审批方式：1-会签；2-或签；3-依次审批
     **/
    @JsonProperty("node_apv_rel")
    private Integer nodeApvRel;

    /**
     * 审批节点更新时间
     **/
    @JsonProperty("apv_update_time")
    private Long apvUpdateTime;
}