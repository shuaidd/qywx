package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 小程序收款订单响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MiniOrderResponse extends AbstractBaseResponse {

    /**
     * 预支付交易会话 ID
     **/
    @JsonProperty("prepay_id")
    private String prepayId;

    /**
     * 二级商户号
     **/
    @JsonProperty("mchid")
    private String mchId;

    /**
     * 商户订单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 交易状态
     **/
    @JsonProperty("trade_state")
    private String tradeState;

    /**
     * 交易状态描述
     **/
    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;

    /**
     * 交易类型
     **/
    @JsonProperty("trade_type")
    private String tradeType;

    /**
     * 支付者信息
     **/
    @JsonProperty("payer")
    private OrderPayer payer;

    /**
     * 微信支付订单号
     **/
    @JsonProperty("transaction_id")
    private String transactionId;

    /**
     * 付款银行类型
     **/
    @JsonProperty("bank_type")
    private String bankType;

    /**
     * 附加数据
     **/
    @JsonProperty("attach")
    private String attach;

    /**
     * 支付完成时间
     **/
    @JsonProperty("success_time")
    private String successTime;

    /**
     * 订单金额信息
     **/
    @JsonProperty("amount")
    private OrderAmount amount;

    /**
     * 下单或扣款场景信息
     **/
    @JsonProperty("scene_info")
    private OrderSceneInfo sceneInfo;

    /**
     * 优惠详情列表
     **/
    @JsonProperty("promotion_detail")
    private List<PromotionDetail> promotionDetail;
}
