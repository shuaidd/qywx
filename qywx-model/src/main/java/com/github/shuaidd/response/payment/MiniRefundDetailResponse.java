package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 查询小程序收款退款详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MiniRefundDetailResponse extends AbstractBaseResponse {

    /**
     * 商户退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;

    /**
     * 商户订单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 退款渠道
     **/
    @JsonProperty("channel")
    private String channel;

    /**
     * 退款入账账户
     **/
    @JsonProperty("user_received_account")
    private String userReceivedAccount;

    /**
     * 退款成功时间
     **/
    @JsonProperty("success_time")
    private String successTime;

    /**
     * 退款创建时间
     **/
    @JsonProperty("create_time")
    private String createTime;

    /**
     * 退款状态
     **/
    @JsonProperty("status")
    private String status;

    /**
     * 退款金额信息
     **/
    @JsonProperty("amount")
    private RefundAmount amount;

    /**
     * 优惠退款详情列表
     **/
    @JsonProperty("promotion_detail")
    private List<PromotionDetail> promotionDetail;
}
