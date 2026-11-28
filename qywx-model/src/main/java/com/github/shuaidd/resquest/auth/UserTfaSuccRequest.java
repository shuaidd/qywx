package com.github.shuaidd.resquest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 使用二次验证请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UserTfaSuccRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("tfa_code")
    private String tfaCode;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTfaCode() {
        return tfaCode;
    }

    public void setTfaCode(String tfaCode) {
        this.tfaCode = tfaCode;
    }
}
