package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 班级收款订单详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class TradeResponse extends AbstractBaseResponse {

    /**
     * 微信交易单号
     */
    @JsonProperty("transaction_id")
    private String transactionId;

    /**
     * 交易时间戳
     */
    @JsonProperty("pay_time")
    private Long payTime;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }
}
