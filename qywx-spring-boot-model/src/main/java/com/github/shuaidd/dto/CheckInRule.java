package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 12:21
 **/
public class CheckInRule {

    @JsonProperty("userid")
    private String userId;

    private CheckInRuleGroup group;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CheckInRuleGroup getGroup() {
        return group;
    }

    public void setGroup(CheckInRuleGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("group", group)
                .toString();
    }
}
