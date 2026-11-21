package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 创建通讯录隐藏规则响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ContactRuleIdsResponse extends AbstractBaseResponse {

    @JsonProperty("rule_ids")
    private List<Integer> ruleIds;

    public List<Integer> getRuleIds() {
        return ruleIds;
    }

    public void setRuleIds(List<Integer> ruleIds) {
        this.ruleIds = ruleIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("ruleIds", ruleIds)
                .toString();
    }
}
