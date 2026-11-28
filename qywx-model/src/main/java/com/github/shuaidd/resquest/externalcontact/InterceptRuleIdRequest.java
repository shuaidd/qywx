package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InterceptRuleIdRequest {

    /**
     * ruleId
     */
    @JsonProperty("rule_id")
    private String ruleId;

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }
}