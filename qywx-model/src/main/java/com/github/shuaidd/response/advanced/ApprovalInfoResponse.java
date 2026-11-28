package com.github.shuaidd.response.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取申请单详细信息响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class ApprovalInfoResponse extends AbstractBaseResponse {
    /**
     * 申请单详细信息
     **/
    @JsonProperty("approval_info")
    private ApprovalDetail approvalInfo;
}