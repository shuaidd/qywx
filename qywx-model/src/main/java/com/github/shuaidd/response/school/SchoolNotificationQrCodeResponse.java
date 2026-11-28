package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 「学校通知」二维码响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolNotificationQrCodeResponse extends AbstractBaseResponse {

    /**
     * 1200px的大尺寸二维码链接
     */
    @JsonProperty("qrcode_big")
    private String qrcodeBig;

    /**
     * 430px的中尺寸二维码链接
     */
    @JsonProperty("qrcode_middle")
    private String qrcodeMiddle;

    /**
     * 258px的小尺寸二维码链接
     */
    @JsonProperty("qrcode_thumb")
    private String qrcodeThumb;

    public String getQrcodeBig() {
        return qrcodeBig;
    }

    public void setQrcodeBig(String qrcodeBig) {
        this.qrcodeBig = qrcodeBig;
    }

    public String getQrcodeMiddle() {
        return qrcodeMiddle;
    }

    public void setQrcodeMiddle(String qrcodeMiddle) {
        this.qrcodeMiddle = qrcodeMiddle;
    }

    public String getQrcodeThumb() {
        return qrcodeThumb;
    }

    public void setQrcodeThumb(String qrcodeThumb) {
        this.qrcodeThumb = qrcodeThumb;
    }
}
