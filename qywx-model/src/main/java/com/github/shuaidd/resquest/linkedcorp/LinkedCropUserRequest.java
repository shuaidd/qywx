package com.github.shuaidd.resquest.linkedcorp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-04 21:30
 **/
public class LinkedCropUserRequest {

    @JsonProperty("userid")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
