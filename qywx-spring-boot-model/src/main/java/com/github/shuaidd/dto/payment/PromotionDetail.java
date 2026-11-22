package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 小程序收款优惠详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class PromotionDetail {

    /**
     * 优惠券或立减优惠 ID
     **/
    @JsonProperty("promotion_id")
    private String promotionId;

    /**
     * 商家小票 ID 或优惠券 ID
     **/
    @JsonProperty("coupon_id")
    private String couponId;

    /**
     * 优惠名称
     **/
    @JsonProperty("name")
    private String name;

    /**
     * 优惠范围：GLOBAL 全场，SINGLE 单品
     **/
    @JsonProperty("scope")
    private String scope;

    /**
     * 优惠类型
     **/
    @JsonProperty("type")
    private String type;

    /**
     * 优惠金额，单位分
     **/
    @JsonProperty("amount")
    private Integer amount;

    /**
     * 优惠退款金额，单位分
     **/
    @JsonProperty("refund_amount")
    private Integer refundAmount;

    /**
     * 活动 ID
     **/
    @JsonProperty("stock_id")
    private String stockId;

    /**
     * 微信出资金额，单位分
     **/
    @JsonProperty("wechatpay_contribute")
    private Integer wechatpayContribute;

    /**
     * 商户出资金额，单位分
     **/
    @JsonProperty("merchant_contribute")
    private Integer merchantContribute;

    /**
     * 其他出资方金额，单位分
     **/
    @JsonProperty("other_contribute")
    private Integer otherContribute;

    /**
     * 优惠币种
     **/
    @JsonProperty("currency")
    private String currency;

    /**
     * 优惠关联商品明细
     **/
    @JsonProperty("goods_detail")
    private List<OrderGoodsDetail> goodsDetail;
}
