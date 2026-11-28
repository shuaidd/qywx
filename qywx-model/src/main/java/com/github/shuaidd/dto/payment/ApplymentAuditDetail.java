package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请审核驳回详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplymentAuditDetail {

    /**
     * 审核驳回的资料项名称
     **/
    @JsonProperty("param_name")
    private String paramName;

    /**
     * 资料项驳回原因
     **/
    @JsonProperty("reject_reason")
    private String rejectReason;
}
