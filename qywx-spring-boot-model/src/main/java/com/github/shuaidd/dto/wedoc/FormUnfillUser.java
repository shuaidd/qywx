package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表未提交用户
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormUnfillUser {

    /**
     * 企业内成员的id
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 成员名字
     */
    @JsonProperty("user_name")
    private String userName;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userid", userid)
                .append("userName", userName)
                .toString();
    }
}
