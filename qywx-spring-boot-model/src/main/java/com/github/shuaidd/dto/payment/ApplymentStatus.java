package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 商户申请单状态
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplymentStatus {

    /**
     * 申请状态
     **/
    @JsonProperty("applyment_state")
    private String applymentState;

    /**
     * 申请状态描述
     **/
    @JsonProperty("applyment_state_desc")
    private String applymentStateDesc;

    /**
     * 签约状态
     **/
    @JsonProperty("sign_state")
    private String signState;

    /**
     * 签约链接
     **/
    @JsonProperty("sign_url")
    private String signUrl;

    /**
     * 电商平台二级商户号
     **/
    @JsonProperty("sub_mchid")
    private String subMchId;

    /**
     * 驳回原因详情列表
     **/
    @JsonProperty("audit_detail")
    private List<ApplymentAuditDetail> auditDetail;

    /**
     * 汇款账户验证信息
     **/
    @JsonProperty("account_validation")
    private AccountValidation accountValidation;

    /**
     * 法人验证链接
     **/
    @JsonProperty("legal_validation_url")
    private String legalValidationUrl;
}
