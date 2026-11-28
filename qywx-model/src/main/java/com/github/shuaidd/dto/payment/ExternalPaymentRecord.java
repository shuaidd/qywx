package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 对外收款记录
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalPaymentRecord {

    /**
     * 交易单号
     **/
    @JsonProperty("transaction_id")
    private String transactionId;

    /**
     * 交易类型：0 收款记录，1 退款记录
     **/
    @JsonProperty("bill_type")
    private Integer billType;

    /**
     * 交易状态：1 已完成，3 已完成有退款
     **/
    @JsonProperty("trade_state")
    private Integer tradeState;

    /**
     * 交易时间戳，单位秒
     **/
    @JsonProperty("pay_time")
    private Long payTime;

    /**
     * 商户单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;

    /**
     * 付款人 userid
     **/
    @JsonProperty("external_userid")
    private String externalUserId;

    /**
     * 收款总金额，单位分
     **/
    @JsonProperty("total_fee")
    private Long totalFee;

    /**
     * 收款或退款成员 userid
     **/
    @JsonProperty("payee_userid")
    private String payeeUserId;

    /**
     * 收款方式：0 聊天收款、1 收款码、2 直播间、3 产品图册、14 转账、15 小程序
     **/
    @JsonProperty("payment_type")
    private Integer paymentType;

    /**
     * 收款商户号
     **/
    @JsonProperty("mch_id")
    private String mchId;

    /**
     * 交易或退款备注
     **/
    @JsonProperty("remark")
    private String remark;

    /**
     * 商品信息列表，退款记录不返回
     **/
    @JsonProperty("commodity_list")
    private List<ExternalCommodity> commodityList;

    /**
     * 退款总金额，单位分
     **/
    @JsonProperty("total_refund_fee")
    private Long totalRefundFee;

    /**
     * 退款单据列表，退款记录不返回
     **/
    @JsonProperty("refund_list")
    private List<ExternalRefundRecord> refundList;

    /**
     * 收款项目联系人信息
     **/
    @JsonProperty("contact_info")
    private ExternalContactInfo contactInfo;

    /**
     * 小程序收款信息
     **/
    @JsonProperty("miniprogram_info")
    private ExternalMiniprogramInfo miniprogramInfo;
}
