package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提交小程序收款申请图片响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class UploadPaymentImageResponse extends AbstractBaseResponse {

    /**
     * 上传得到的微信支付图片 ID，30 天后过期
     **/
    @JsonProperty("open_wx_pay_media_id")
    private String openWxPayMediaId;
}
