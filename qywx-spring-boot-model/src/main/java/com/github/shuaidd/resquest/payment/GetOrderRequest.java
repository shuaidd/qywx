package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 查询小程序收款订单请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetOrderRequest {

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
}
