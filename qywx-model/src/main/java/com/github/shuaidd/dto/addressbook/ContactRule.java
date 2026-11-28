package com.github.shuaidd.dto.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 通讯录隐藏规则
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ContactRule {

    @JsonProperty("rule_id")
    private Integer ruleId;

    @JsonProperty("rule_type")
    private Integer ruleType;

    @JsonProperty("range")
    private ContactRuleScope range;

    @JsonProperty("whitelist")
    private ContactRuleScope whitelist;

    @JsonProperty("exclude")
    private ContactRuleScope exclude;

    @JsonProperty("is_allowed_search")
    private Boolean allowedSearch;

    @JsonProperty("is_allowed_conversation")
    private Boolean allowedConversation;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public ContactRuleScope getRange() {
        return range;
    }

    public void setRange(ContactRuleScope range) {
        this.range = range;
    }

    public ContactRuleScope getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(ContactRuleScope whitelist) {
        this.whitelist = whitelist;
    }

    public ContactRuleScope getExclude() {
        return exclude;
    }

    public void setExclude(ContactRuleScope exclude) {
        this.exclude = exclude;
    }

    public Boolean getAllowedSearch() {
        return allowedSearch;
    }

    public void setAllowedSearch(Boolean allowedSearch) {
        this.allowedSearch = allowedSearch;
    }

    public Boolean getAllowedConversation() {
        return allowedConversation;
    }

    public void setAllowedConversation(Boolean allowedConversation) {
        this.allowedConversation = allowedConversation;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactRule.class.getSimpleName() + "[", "]")
                .add("ruleId=" + ruleId)
                .add("ruleType=" + ruleType)
                .add("range=" + range)
                .add("whitelist=" + whitelist)
                .add("exclude=" + exclude)
                .add("allowedSearch=" + allowedSearch)
                .add("allowedConversation=" + allowedConversation)
                .toString();
    }
}
