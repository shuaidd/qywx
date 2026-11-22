package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 设置对外收款商户号使用范围请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class SetMerchantUseScopeRequest {

    /**
     * 微信支付商户号
     **/
    @JsonProperty("mch_id")
    private String mchId;

    /**
     * 商户号使用范围
     **/
    @JsonProperty("allow_use_scope")
    private PaymentScope allowUseScope;
}
