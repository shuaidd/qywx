package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 17:29
 **/
public class UserIdRequest {

    @JsonProperty("userid")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserIdRequest.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .toString();
    }
}
