package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;

/**
 * 查询对外收款商户号响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class GetMerchantResponse extends AbstractBaseResponse {

    /**
     * 微信支付商户号
     **/
    @JsonProperty("mch_id")
    private String mchId;

    /**
     * 商户号全称
     **/
    @JsonProperty("merchant_name")
    private String merchantName;

    /**
     * 绑定状态：1 申请中、2 已绑定、3 已撤销
     **/
    @JsonProperty("bind_status")
    private Integer bindStatus;

    /**
     * 商户号使用范围，已绑定时返回
     **/
    @JsonProperty("allow_use_scope")
    private PaymentScope allowUseScope;
}
