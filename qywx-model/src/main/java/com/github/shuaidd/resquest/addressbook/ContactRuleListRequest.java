package com.github.shuaidd.resquest.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.ContactRule;

import java.util.List;

/**
 * 创建或修改通讯录隐藏规则请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ContactRuleListRequest {

    @JsonProperty("rules")
    private List<ContactRule> rules;

    public List<ContactRule> getRules() {
        return rules;
    }

    public void setRules(List<ContactRule> rules) {
        this.rules = rules;
    }
}
