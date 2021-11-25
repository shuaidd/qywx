package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 获取加入企业二维码
 *
 * @author ddshuai
 * date 2021-11-24 12:59
 **/

public class JoinQrCodeResponse extends AbstractBaseResponse {

    @JsonProperty("join_qrcode")
    private String joinQrCode;

    public String getJoinQrCode() {
        return joinQrCode;
    }

    public void setJoinQrCode(String joinQrCode) {
        this.joinQrCode = joinQrCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JoinQrCodeResponse.class.getSimpleName() + "[", "]")
                .add("joinQrCode='" + joinQrCode + "'")
                .toString();
    }
}
