package com.github.shuaidd.dto.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 13:43
 **/
public class UserId {

    @JsonProperty("userid")
    private String userId;

    private Integer status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserId.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("status=" + status)
                .toString();
    }
}
