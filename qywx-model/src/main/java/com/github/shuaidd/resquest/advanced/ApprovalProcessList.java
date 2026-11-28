package com.github.shuaidd.resquest.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 高级功能申请单审批流程
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApprovalProcessList {
    /**
     * 审批单审批节点列表
     **/
    @JsonProperty("node_list")
    private List<ApprovalNode> nodeList;
}