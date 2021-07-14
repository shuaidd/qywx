package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 15:53
 **/
public class CheckInRuleGroupRange {

    @JsonProperty("partyid")
    private List<Integer> partyId;

    @JsonProperty("userid")
    private List<String> userId;

    @JsonProperty("tagid")
    private List<Integer> tagId;

    public List<Integer> getPartyId() {
        return partyId;
    }

    public void setPartyId(List<Integer> partyId) {
        this.partyId = partyId;
    }

    public List<String> getUserId() {
        return userId;
    }

    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    public List<Integer> getTagId() {
        return tagId;
    }

    public void setTagId(List<Integer> tagId) {
        this.tagId = tagId;
    }
}
