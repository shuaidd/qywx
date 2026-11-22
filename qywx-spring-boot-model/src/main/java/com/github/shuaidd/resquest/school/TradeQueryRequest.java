package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询班级收款订单详情的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class TradeQueryRequest {

    /**
     * 收款项目id，由发起班级收款接口返回
     */
    @JsonProperty("payment_id")
    private String paymentId;

    /**
     * 订单号，由获取学生付款结果接口返回
     */
    @JsonProperty("trade_no")
    private String tradeNo;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
