package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表成员答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormMemberReplyItem {

    /**
     * 成员id
     */
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
