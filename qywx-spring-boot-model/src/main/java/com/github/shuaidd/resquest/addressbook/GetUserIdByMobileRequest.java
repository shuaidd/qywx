package com.github.shuaidd.resquest.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 手机号获取 userid 请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GetUserIdByMobileRequest {

    @JsonProperty("mobile")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
