package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.WeChatUser;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-05 17:21
 **/
public class WeChatUserResponse extends WeChatUser {

    private String status;

    @JsonProperty(value = "qr_code")
    private String qrCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WeChatUserResponse.class.getSimpleName() + "[", "]")
                .add("status='" + status + "'")
                .add("qrCode='" + qrCode + "'")
                .add(super.toString())
                .toString();
    }
}
