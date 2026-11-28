package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取班级收款结果的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class PaymentResultRequest {

    /**
     * 收款项目id，由发起班级收款接口返回
     */
    @JsonProperty("payment_id")
    private String paymentId;

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
