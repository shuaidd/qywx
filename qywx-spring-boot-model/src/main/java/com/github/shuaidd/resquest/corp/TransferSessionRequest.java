package com.github.shuaidd.resquest.corp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferSessionRequest {

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
