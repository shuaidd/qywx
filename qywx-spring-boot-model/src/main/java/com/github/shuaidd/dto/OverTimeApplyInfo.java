package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 16:28
 **/
public class OverTimeApplyInfo {

    @JsonProperty("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    @JsonProperty("allow_ot_nonworkingday")
    private Boolean allowOtNonWorkingDay;

    @JsonProperty("ot_nonworkingday_spanday_time")
    private Integer otNonWorkingDaySpanDayTime;

    private Long uptime;

    @JsonProperty("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingDayRestInfo;

    @JsonProperty("ot_nonworkingday_restinfo")
    private OtWorkingDayRestInfo otNonWorkingDayRestInfo;

    public Boolean getAllowOtWorkingDay() {
        return allowOtWorkingDay;
    }

    public void setAllowOtWorkingDay(Boolean allowOtWorkingDay) {
        this.allowOtWorkingDay = allowOtWorkingDay;
    }

    public Boolean getAllowOtNonWorkingDay() {
        return allowOtNonWorkingDay;
    }

    public void setAllowOtNonWorkingDay(Boolean allowOtNonWorkingDay) {
        this.allowOtNonWorkingDay = allowOtNonWorkingDay;
    }

    public Integer getOtNonWorkingDaySpanDayTime() {
        return otNonWorkingDaySpanDayTime;
    }

    public void setOtNonWorkingDaySpanDayTime(Integer otNonWorkingDaySpanDayTime) {
        this.otNonWorkingDaySpanDayTime = otNonWorkingDaySpanDayTime;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public OtWorkingDayRestInfo getOtWorkingDayRestInfo() {
        return otWorkingDayRestInfo;
    }

    public void setOtWorkingDayRestInfo(OtWorkingDayRestInfo otWorkingDayRestInfo) {
        this.otWorkingDayRestInfo = otWorkingDayRestInfo;
    }

    public OtWorkingDayRestInfo getOtNonWorkingDayRestInfo() {
        return otNonWorkingDayRestInfo;
    }

    public void setOtNonWorkingDayRestInfo(OtWorkingDayRestInfo otNonWorkingDayRestInfo) {
        this.otNonWorkingDayRestInfo = otNonWorkingDayRestInfo;
    }
}
