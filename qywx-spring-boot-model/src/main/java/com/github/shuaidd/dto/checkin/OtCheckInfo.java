package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 16:11
 **/
public class OtCheckInfo {

    @JsonProperty("ot_workingday_time_start")
    private Integer otWorkingDayTimeStart;

    @JsonProperty("ot_workingday_time_min")
    private Integer otWorkingDayTimeMin;

    @JsonProperty("ot_workingday_time_max")
    private Integer otWorkingDayTimeMax;

    @JsonProperty("ot_nonworkingday_time_min")
    private Integer otNonWorkingDayTimeMin;

    @JsonProperty("ot_nonworkingday_time_max")
    private Integer otNonWorkingDayTimeMax;

    @JsonProperty("ot_nonworkingday_spanday_time")
    private Integer otNonWorkingDaySpanDayTime;

    @JsonProperty("ot_workingday_restinfo")
    private OtWorkingDayRestInfo otWorkingDayRestInfo;

    @JsonProperty("ot_nonworkingday_restinfo")
    private OtWorkingDayRestInfo otNonWorkingDayRestInfo;

    public Integer getOtWorkingDayTimeStart() {
        return otWorkingDayTimeStart;
    }

    public void setOtWorkingDayTimeStart(Integer otWorkingDayTimeStart) {
        this.otWorkingDayTimeStart = otWorkingDayTimeStart;
    }

    public Integer getOtWorkingDayTimeMin() {
        return otWorkingDayTimeMin;
    }

    public void setOtWorkingDayTimeMin(Integer otWorkingDayTimeMin) {
        this.otWorkingDayTimeMin = otWorkingDayTimeMin;
    }

    public Integer getOtWorkingDayTimeMax() {
        return otWorkingDayTimeMax;
    }

    public void setOtWorkingDayTimeMax(Integer otWorkingDayTimeMax) {
        this.otWorkingDayTimeMax = otWorkingDayTimeMax;
    }

    public Integer getOtNonWorkingDayTimeMin() {
        return otNonWorkingDayTimeMin;
    }

    public void setOtNonWorkingDayTimeMin(Integer otNonWorkingDayTimeMin) {
        this.otNonWorkingDayTimeMin = otNonWorkingDayTimeMin;
    }

    public Integer getOtNonWorkingDayTimeMax() {
        return otNonWorkingDayTimeMax;
    }

    public void setOtNonWorkingDayTimeMax(Integer otNonWorkingDayTimeMax) {
        this.otNonWorkingDayTimeMax = otNonWorkingDayTimeMax;
    }

    public Integer getOtNonWorkingDaySpanDayTime() {
        return otNonWorkingDaySpanDayTime;
    }

    public void setOtNonWorkingDaySpanDayTime(Integer otNonWorkingDaySpanDayTime) {
        this.otNonWorkingDaySpanDayTime = otNonWorkingDaySpanDayTime;
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
