package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:20
 **/
public class ContactMember {

    private String name;

    @JsonProperty("userid")
    private String userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactMember.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("userId='" + userId + "'")
                .toString();
    }
}
