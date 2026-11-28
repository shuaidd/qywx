package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序收款商品明细
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderGoodsDetail {

    /**
     * 商户侧商品编码
     **/
    @JsonProperty("merchant_goods_id")
    private String merchantGoodsId;

    /**
     * 微信支付商品编码
     **/
    @JsonProperty("wechatpay_goods_id")
    private String wechatpayGoodsId;

    /**
     * 商品名称
     **/
    @JsonProperty("goods_name")
    private String goodsName;

    /**
     * 商品数量
     **/
    @JsonProperty("quantity")
    private Integer quantity;

    /**
     * 商品单价，单位分
     **/
    @JsonProperty("unit_price")
    private Long unitPrice;
}
