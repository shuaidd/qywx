package com.github.shuaidd.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

public class UserSensitiveDetailResponse extends AbstractBaseResponse {

    @JsonProperty("userid")
    private String userid;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("qr_code")
    private String qrCode;

    @JsonProperty("mobile")
    private String mobile;

    @JsonProperty("email")
    private String email;

    @JsonProperty("biz_mail")
    private String bizMail;

    @JsonProperty("address")
    private String address;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBizMail() {
        return bizMail;
    }

    public void setBizMail(String bizMail) {
        this.bizMail = bizMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
