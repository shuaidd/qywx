package com.github.shuaidd.response.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级功能申请单详细信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApprovalDetail {
    /**
     * 申请人userid
     **/
    @JsonProperty("applicant")
    private String applicant;

    /**
     * 创建时间戳
     **/
    @JsonProperty("create_time")
    private Long createTime;

    /**
     * 申请的高级账号类型：1-邮件；2-文档；3-微盘；4-会议
     **/
    @JsonProperty("business_type")
    private Integer businessType;

    /**
     * 请求id
     **/
    @JsonProperty("apply_id")
    private String applyId;

    /**
     * 自建应用中的审批id
     **/
    @JsonProperty("approval_id")
    private String approvalId;

    /**
     * 自建应用中的审批单跳转链接
     **/
    @JsonProperty("approval_url")
    private String approvalUrl;

    /**
     * 审批单状态：1-审批人审批中；2-审批人驳回；3-审批人已同意；4-管理员驳回；5-管理员已分配；0-历史申请记录，无流程信息
     **/
    @JsonProperty("approval_status")
    private Integer approvalStatus;

    /**
     * 审批类型：0-默认申请；1-自定义审批；2-企业openapi
     **/
    @JsonProperty("approval_type")
    private Integer approvalType;

    /**
     * 申请原因
     **/
    @JsonProperty("request_reason")
    private String requestReason;
}