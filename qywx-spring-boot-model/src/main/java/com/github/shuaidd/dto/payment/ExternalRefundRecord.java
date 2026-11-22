package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对外收款退款单详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalRefundRecord {

    /**
     * 退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;

    /**
     * 退款发起人 ID
     **/
    @JsonProperty("refund_userid")
    private String refundUserId;

    /**
     * 退款备注
     **/
    @JsonProperty("refund_comment")
    private String refundComment;

    /**
     * 退款发起时间戳，单位秒
     **/
    @JsonProperty("refund_reqtime")
    private Long refundReqTime;

    /**
     * 退款状态：0 已申请、1 处理中、2 成功、3 关闭、4 异常、5 审批中、6 审批失败、7 审批取消
     **/
    @JsonProperty("refund_status")
    private Integer refundStatus;

    /**
     * 退款金额，单位分
     **/
    @JsonProperty("refund_fee")
    private Long refundFee;
}
