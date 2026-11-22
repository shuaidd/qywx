package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 企业支付资金流水记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class FundFlowRecord {

    /**
     * 动账时间戳，单位秒
     **/
    @JsonProperty("timestamp")
    private Long timestamp;

    /**
     * 微信支付资金流水单号
     **/
    @JsonProperty("request_no")
    private String requestNo;

    /**
     * 动账类型：1 退款、2 手续费、3 收款、4 提现、5 其他
     **/
    @JsonProperty("transaction_type")
    private Integer transactionType;

    /**
     * 收支类型：1 收入，2 支出
     **/
    @JsonProperty("fund_flow_type")
    private Integer fundFlowType;

    /**
     * 动账金额，单位分
     **/
    @JsonProperty("transaction_amount")
    private Long transactionAmount;

    /**
     * 账户余额，单位分
     **/
    @JsonProperty("account_balance")
    private Long accountBalance;

    /**
     * 商户单号或业务凭证号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 商户号
     **/
    @JsonProperty("mch_id")
    private String mchId;

    /**
     * 操作人 userid
     **/
    @JsonProperty("operator_userid")
    private String operatorUserId;

    /**
     * 所属规则组列表
     **/
    @JsonProperty("group_list")
    private List<FundFlowGroup> groupList;

    /**
     * 资金流水备注
     **/
    @JsonProperty("remark")
    private String remark;
}
