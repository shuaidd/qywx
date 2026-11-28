package com.github.shuaidd.resquest.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-03 06:45
 **/
public class ProInfoRequest {

    @JsonProperty("userid")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
