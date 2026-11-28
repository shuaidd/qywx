package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 手机号或邮箱获取的 userid 响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UserIdResponse extends AbstractBaseResponse {

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
        return new ToStringBuilder(this)
                .append("userId", userId)
                .toString();
    }
}
