package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 17:08
 **/
public class TimeRule {

    @JsonProperty("offwork_after_time")
    private Integer offWorkAfterTime;

    @JsonProperty("onwork_flex_time")
    private Integer onWorkFlexTime;

    public Integer getOffWorkAfterTime() {
        return offWorkAfterTime;
    }

    public void setOffWorkAfterTime(Integer offWorkAfterTime) {
        this.offWorkAfterTime = offWorkAfterTime;
    }

    public Integer getOnWorkFlexTime() {
        return onWorkFlexTime;
    }

    public void setOnWorkFlexTime(Integer onWorkFlexTime) {
        this.onWorkFlexTime = onWorkFlexTime;
    }
}
