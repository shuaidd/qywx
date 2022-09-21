package com.github.shuaidd.resquest.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSensitiveRequest {

    @JsonProperty("user_ticket")
    private String userTicket;

    public String getUserTicket() {
        return userTicket;
    }

    public void setUserTicket(String userTicket) {
        this.userTicket = userTicket;
    }
}
