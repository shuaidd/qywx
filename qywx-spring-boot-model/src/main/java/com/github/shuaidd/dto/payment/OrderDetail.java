package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;
import lombok.experimental.Accessors;

/**
 * 小程序收款订单优惠明细
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderDetail {

    /**
     * 订单原价，单位分
     **/
    @JsonProperty("cost_price")
    private Long costPrice;

    /**
     * 商家小票 ID
     **/
    @JsonProperty("invoice_id")
    private String invoiceId;

    /**
     * 商品优惠明细列表
     **/
    @JsonProperty("goods_detail")
    private List<OrderGoodsDetail> goodsDetail;
}
