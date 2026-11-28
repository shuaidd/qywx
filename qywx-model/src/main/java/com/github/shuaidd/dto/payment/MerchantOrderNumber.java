package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 收款项目商户单号
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MerchantOrderNumber {

    /**
     * 商户单号
     **/
    @JsonProperty("out_trade_no")
    private String outTradeNo;
}
