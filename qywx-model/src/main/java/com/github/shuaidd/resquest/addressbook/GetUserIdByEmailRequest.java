package com.github.shuaidd.resquest.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 邮箱获取 userid 请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GetUserIdByEmailRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("email_type")
    private Integer emailType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmailType() {
        return emailType;
    }

    public void setEmailType(Integer emailType) {
        this.emailType = emailType;
    }
}
