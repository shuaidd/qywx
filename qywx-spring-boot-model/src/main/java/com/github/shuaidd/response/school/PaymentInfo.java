package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 班级收款学生付款详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class PaymentInfo {

    /**
     * 学生账号
     */
    @JsonProperty("student_userid")
    private String studentUserid;

    /**
     * 付款状态。0表示未付款，1表示已付款
     */
    @JsonProperty("trade_state")
    private Integer tradeState;

    /**
     * 订单号
     */
    @JsonProperty("trade_no")
    private String tradeNo;

    /**
     * 付款家长账号
     */
    @JsonProperty("payer_parent_userid")
    private String payerParentUserid;

    public String getStudentUserid() {
        return studentUserid;
    }

    public void setStudentUserid(String studentUserid) {
        this.studentUserid = studentUserid;
    }

    public Integer getTradeState() {
        return tradeState;
    }

    public void setTradeState(Integer tradeState) {
        this.tradeState = tradeState;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayerParentUserid() {
        return payerParentUserid;
    }

    public void setPayerParentUserid(String payerParentUserid) {
        this.payerParentUserid = payerParentUserid;
    }
}
