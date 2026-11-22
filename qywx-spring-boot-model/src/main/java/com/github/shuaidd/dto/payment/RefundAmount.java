package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序退款金额
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class RefundAmount {

    /**
     * 退款金额，单位分
     **/
    @JsonProperty("refund")
    private Long refund;

    /**
     * 原订单总金额，单位分
     **/
    @JsonProperty("total")
    private Long total;

    /**
     * 用户退款金额，单位分
     **/
    @JsonProperty("payer_refund")
    private Long payerRefund;

    /**
     * 优惠退款金额，单位分
     **/
    @JsonProperty("discount_refund")
    private Long discountRefund;

    /**
     * 退款币种，当前支持 CNY
     **/
    @JsonProperty("currency")
    private String currency;
}
