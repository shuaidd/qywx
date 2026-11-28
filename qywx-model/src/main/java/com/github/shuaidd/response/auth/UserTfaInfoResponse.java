package com.github.shuaidd.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 用户二次验证信息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UserTfaInfoResponse extends AbstractBaseResponse {

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
