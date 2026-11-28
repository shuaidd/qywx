package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 查询成员邮箱未读数请求。
 */
public class GetMailUnreadCountRequest {

    /**
     * 成员 userid
     */
    @JsonProperty("userid")
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
