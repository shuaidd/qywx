package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.ContactRule;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 通讯录隐藏规则列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ContactRuleListResponse extends AbstractBaseResponse {

    @JsonProperty("rules")
    private List<ContactRule> rules;

    public List<ContactRule> getRules() {
        return rules;
    }

    public void setRules(List<ContactRule> rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("rules", rules)
                .toString();
    }
}
