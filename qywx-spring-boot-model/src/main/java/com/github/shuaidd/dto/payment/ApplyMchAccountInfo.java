package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商户申请结算账户信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ApplyMchAccountInfo {

    /**
     * 银行账户类型：74 对公，75 对私
     **/
    @JsonProperty("bank_account_type")
    private Integer bankAccountType;

    /**
     * 开户银行名称
     **/
    @JsonProperty("account_bank")
    private String accountBank;

    /**
     * 开户名称
     **/
    @JsonProperty("account_name")
    private String accountName;

    /**
     * 开户银行省市编码
     **/
    @JsonProperty("bank_address_code")
    private String bankAddressCode;

    /**
     * 开户银行全称，含支行
     **/
    @JsonProperty("bank_name")
    private String bankName;

    /**
     * 银行账号
     **/
    @JsonProperty("account_number")
    private String accountNumber;

    /**
     * 银行卡补充资料
     **/
    @JsonProperty("bank_card_supplement")
    private ApplyMchBankCardSupplement bankCardSupplement;
}
