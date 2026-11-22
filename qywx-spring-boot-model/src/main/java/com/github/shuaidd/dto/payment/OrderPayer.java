package com.github.shuaidd.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 小程序收款支付者信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class OrderPayer {

    /**
     * 用户在子商户 AppID 下的唯一标识
     **/
    @JsonProperty("openid")
    private String openId;
}
