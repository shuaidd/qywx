package com.github.shuaidd.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

public class AuthUserInfoResponse extends AbstractBaseResponse {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("user_ticket")
    private String userTicket;

    /**
     * 家长的外部联系人id
     */
    @JsonProperty("parent_userid")
    private String parentUserId;

    @JsonProperty("external_userid")
    private String externalUserId;

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

    public String getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(String userTicket) {
        this.userTicket = userTicket;
    }

    public String getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(String parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }
}
