package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-05 18:51
 **/
public class ConvertUserIdOpenIdResponse extends AbstractBaseResponse {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("openid")
    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ConvertUserIdOpenIdResponse.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("openId='" + openId + "'")
                .toString();
    }
}
