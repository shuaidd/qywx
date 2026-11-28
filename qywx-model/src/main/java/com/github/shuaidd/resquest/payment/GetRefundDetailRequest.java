package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询小程序收款退款详情请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetRefundDetailRequest {

    /**
     * 企业微信分配的商户号
     **/
    @JsonProperty("mchid")
    private String mchId;

    /**
     * 商户退款单号
     **/
    @JsonProperty("out_refund_no")
    private String outRefundNo;
}
