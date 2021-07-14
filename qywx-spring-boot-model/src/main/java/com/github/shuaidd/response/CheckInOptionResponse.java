package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.CheckInRuleGroup;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 17:14
 **/
public class CheckInOptionResponse extends AbstractBaseResponse {

    @JsonProperty("group")
    private List<CheckInRuleGroup> groups;

    public List<CheckInRuleGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<CheckInRuleGroup> groups) {
        this.groups = groups;
    }
}
