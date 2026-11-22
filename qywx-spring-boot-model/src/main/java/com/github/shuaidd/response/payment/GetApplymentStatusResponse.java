package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;

/**
 * 查询对外收款账户申请单状态响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GetApplymentStatusResponse extends AbstractBaseResponse {

    /**
     * 申请单具体状态
     **/
    @JsonProperty("status")
    private ApplymentStatus status;

    /**
     * 申请单当前阶段
     **/
    @JsonProperty("apply_state")
    private Integer applyState;

    /**
     * 当前签约阶段
     **/
    @JsonProperty("real_sign_state")
    private Integer realSignState;

    /**
     * 驳回理由
     **/
    @JsonProperty("reject_reason")
    private String rejectReason;
}
