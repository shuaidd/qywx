package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报任务适用范围
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportApplyRange {

    /**
     * type=1时表示企业成员的userid，type=2时表示学生的userid
     */
    @JsonProperty("userids")
    private List<String> userids;

    /**
     * type=1时表示企业通讯录的部门id，type=2时表示学校通讯录的部门id
     */
    @JsonProperty("partyids")
    private List<Integer> partyids;

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }

    public List<Integer> getPartyids() {
        return partyids;
    }

    public void setPartyids(List<Integer> partyids) {
        this.partyids = partyids;
    }
}
