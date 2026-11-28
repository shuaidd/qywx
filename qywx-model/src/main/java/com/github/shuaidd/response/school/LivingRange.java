package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 上课直播范围
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingRange {

    /**
     * 班级的partyid列表，直播范围包含班级群时优先返回班级群对应班级的partyid
     */
    @JsonProperty("partyids")
    private List<Integer> partyids;

    /**
     * 群名称列表
     */
    @JsonProperty("group_names")
    private List<String> groupNames;

    public List<Integer> getPartyids() {
        return partyids;
    }

    public void setPartyids(List<Integer> partyids) {
        this.partyids = partyids;
    }

    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames(List<String> groupNames) {
        this.groupNames = groupNames;
    }
}
