package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 申请小程序收款退款请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class RefundRequest {

    /**
     * 企业微信分配的商户号
     **/
    @JsonProperty("mchid")
    private String mchId;

    /**
     * 小程序 AppID
     **/
    @JsonProperty("appid")
    private String appId;

    /**
     * 原支付订单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;

    /**
     * 退款原因
     **/
    @JsonProperty("reason")
    private String reason;

    /**
     * 退款使用的资金账户
     **/
    @JsonProperty("funds_account")
    private String fundsAccount;

    /**
     * 退款金额信息
     **/
    @JsonProperty("amount")
    private RefundAmount amount;
}
