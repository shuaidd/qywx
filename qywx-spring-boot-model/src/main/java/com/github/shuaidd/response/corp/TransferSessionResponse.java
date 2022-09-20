package com.github.shuaidd.response.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

public class TransferSessionResponse  extends AbstractBaseResponse {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("session_key")
    private String sessionKey;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
