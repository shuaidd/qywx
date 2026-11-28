package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序收款订单金额
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderAmount {

    /**
     * 订单总金额，单位分
     **/
    @JsonProperty("total")
    private Long total;

    /**
     * 用户支付金额，单位分
     **/
    @JsonProperty("payer_total")
    private Long payerTotal;

    /**
     * 退款金额，单位分
     **/
    @JsonProperty("refund")
    private Long refund;

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
     * 币种，当前支持 CNY
     **/
    @JsonProperty("currency")
    private String currency;

    /**
     * 用户支付币种
     **/
    @JsonProperty("payer_currency")
    private String payerCurrency;
}
