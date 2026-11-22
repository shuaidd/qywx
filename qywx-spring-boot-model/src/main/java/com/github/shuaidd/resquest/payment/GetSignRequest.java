package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import com.github.shuaidd.dto.payment.*;

/**
 * 获取小程序支付签名请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetSignRequest {

    /**
     * 二级商户应用 AppID
     **/
    @JsonProperty("appid")
    private String appId;

    /**
     * 预支付交易会话 ID
     **/
    @JsonProperty("prepay_id")
    private String prepayId;

    /**
     * 签名方式，默认 RSA
     **/
    @JsonProperty("sign_type")
    private String signType;

    /**
     * 随机字符串
     **/
    @JsonProperty("nonce")
    private String nonce;

    /**
     * 秒级时间戳
     **/
    @JsonProperty("timestamp")
    private Long timestamp;
}
