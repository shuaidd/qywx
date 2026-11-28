package com.github.shuaidd.dto.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 通讯录隐藏规则范围
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ContactRuleScope {

    @JsonProperty("userid")
    private List<String> userIds;

    @JsonProperty("partyid")
    private List<Integer> partyIds;

    @JsonProperty("tagid")
    private List<Integer> tagIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getPartyIds() {
        return partyIds;
    }

    public void setPartyIds(List<Integer> partyIds) {
        this.partyIds = partyIds;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ContactRuleScope.class.getSimpleName() + "[", "]")
                .add("userIds=" + userIds)
                .add("partyIds=" + partyIds)
                .add("tagIds=" + tagIds)
                .toString();
    }
}
