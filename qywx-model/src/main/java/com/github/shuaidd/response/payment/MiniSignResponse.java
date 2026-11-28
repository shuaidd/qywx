package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;

/**
 * 获取小程序支付签名响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MiniSignResponse extends AbstractBaseResponse {

    /**
     * 小程序支付签名值
     **/
    @JsonProperty("pay_sign")
    private String paySign;
}
