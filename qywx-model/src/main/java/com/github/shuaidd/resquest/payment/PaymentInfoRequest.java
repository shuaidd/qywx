package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询收款项目商户单号请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class PaymentInfoRequest {

    /**
     * 收款项目单号
     **/
    @JsonProperty("payment_id")
    private String paymentId;
}
