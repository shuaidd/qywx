package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请汇款账户验证信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class AccountValidation {

    /**
     * 汇款付款户名
     **/
    @JsonProperty("account_name")
    private String accountName;

    /**
     * 汇款付款卡号
     **/
    @JsonProperty("account_no")
    private String accountNo;

    /**
     * 汇款金额，单位分
     **/
    @JsonProperty("pay_amount")
    private Long payAmount;

    /**
     * 收款账户卡号
     **/
    @JsonProperty("destination_account_number")
    private String destinationAccountNumber;

    /**
     * 收款账户户名
     **/
    @JsonProperty("destination_account_name")
    private String destinationAccountName;

    /**
     * 收款账户开户银行
     **/
    @JsonProperty("destination_account_bank")
    private String destinationAccountBank;

    /**
     * 收款账户省市
     **/
    @JsonProperty("city")
    private String city;

    /**
     * 汇款备注
     **/
    @JsonProperty("remark")
    private String remark;

    /**
     * 汇款截止时间
     **/
    @JsonProperty("deadline")
    private String deadline;
}
