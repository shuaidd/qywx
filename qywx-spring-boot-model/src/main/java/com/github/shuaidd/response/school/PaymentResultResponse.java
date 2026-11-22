package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 班级收款结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class PaymentResultResponse extends AbstractBaseResponse {

    /**
     * 收款项目名称
     */
    @JsonProperty("project_name")
    private String projectName;

    /**
     * 收款项目金额，单位为分
     */
    @JsonProperty("amount")
    private Integer amount;

    /**
     * 学生付款信息列表
     */
    @JsonProperty("payment_result")
    private List<PaymentInfo> paymentResult;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<PaymentInfo> getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(List<PaymentInfo> paymentResult) {
        this.paymentResult = paymentResult;
    }
}
