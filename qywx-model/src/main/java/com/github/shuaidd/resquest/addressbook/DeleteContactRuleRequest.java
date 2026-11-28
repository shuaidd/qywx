package com.github.shuaidd.resquest.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 删除通讯录隐藏规则请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class DeleteContactRuleRequest {

    @JsonProperty("rule_ids")
    private List<Integer> ruleIds;

    public List<Integer> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<Integer> ruleIds) {
        this.ruleIds = ruleIds;
    }
}
