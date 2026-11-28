package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.CheckInRuleGroup;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 17:14
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groups", groups)
                .append(super.toString())
                .toString();
    }
}
