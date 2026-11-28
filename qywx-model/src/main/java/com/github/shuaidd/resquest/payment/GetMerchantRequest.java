package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 查询对外收款商户号请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetMerchantRequest {

    /**
     * 微信支付商户号，不超过 32 字节
     **/
    @JsonProperty("mch_id")
    private String mchId;
}
