package com.github.shuaidd.resquest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取用户二次验证信息请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UserTfaInfoRequest {

    @JsonProperty("code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
