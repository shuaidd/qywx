package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 小程序对外收款下单请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class CreateOrderRequest {

    /**
     * 二级商户申请的公众号或移动应用 AppID
     **/
    @JsonProperty("appid")
    private String appId;

    /**
     * 二级商户号
     **/
    @JsonProperty("mchid")
    private String mchId;

    /**
     * 商户系统内部订单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;

    /**
     * 商品描述
     **/
    @JsonProperty("description")
    private String description;

    /**
     * 下单场景 key，用于业绩统计
     **/
    @JsonProperty("scenekey")
    private String sceneKey;

    /**
     * 订单金额
     **/
    @JsonProperty("amount")
    private OrderAmount amount;

    /**
     * 支付者信息
     **/
    @JsonProperty("payer")
    private OrderPayer payer;

    /**
     * 订单失效时间，遵循 RFC3339
     **/
    @JsonProperty("time_expire")
    private String timeExpire;

    /**
     * 附加数据
     **/
    @JsonProperty("attach")
    private String attach;

    /**
     * 订单优惠标记
     **/
    @JsonProperty("goods_tag")
    private String goodsTag;

    /**
     * 下单场景信息
     **/
    @JsonProperty("scene_info")
    private OrderSceneInfo sceneInfo;

    /**
     * 订单优惠和商品明细
     **/
    @JsonProperty("detail")
    private OrderDetail detail;
}
