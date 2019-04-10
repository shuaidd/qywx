package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.ConvertUserIdOpenIdResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-05 18:53
 **/
public class ConvertUserIdOpenIdRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("openid")
    private String openId;

    public ConvertUserIdOpenIdRequest(String userId, String openId) {
        this.userId = userId;
        this.openId = openId;
    }

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
