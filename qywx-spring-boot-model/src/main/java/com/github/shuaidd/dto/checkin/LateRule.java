package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 16:47
 **/
public class LateRule {

    @JsonProperty("allow_offwork_after_time")
    private Boolean allowOffWorkAfterTime;

    @JsonProperty("timerules")
    private List<TimeRule> timeRules;

    public Boolean getAllowOffWorkAfterTime() {
        return allowOffWorkAfterTime;
    }

    public void setAllowOffWorkAfterTime(Boolean allowOffWorkAfterTime) {
        this.allowOffWorkAfterTime = allowOffWorkAfterTime;
    }

    public List<TimeRule> getTimeRules() {
        return timeRules;
    }

    public void setTimeRules(List<TimeRule> timeRules) {
        this.timeRules = timeRules;
    }
}
