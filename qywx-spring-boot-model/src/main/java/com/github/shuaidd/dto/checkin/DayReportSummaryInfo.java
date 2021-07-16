package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 13:03
 **/
public class DayReportSummaryInfo {

    @JsonProperty("checkin_count")
    private Integer checkinCount;

    @JsonProperty("regular_work_sec")
    private Integer regularWorkSec;

    @JsonProperty("standard_work_sec")
    private Integer standardWorkSec;

    @JsonProperty("earliest_time")
    private Integer earliestTime;

    @JsonProperty("lastest_time")
    private Integer lastTime;

    @JsonProperty("except_days")
    private Integer exceptDays;

    @JsonProperty("work_days")
    private Integer workDays;

    public Integer getCheckinCount() {
        return checkinCount;
    }

    public void setCheckinCount(Integer checkinCount) {
        this.checkinCount = checkinCount;
    }

    public Integer getRegularWorkSec() {
        return regularWorkSec;
    }

    public void setRegularWorkSec(Integer regularWorkSec) {
        this.regularWorkSec = regularWorkSec;
    }

    public Integer getStandardWorkSec() {
        return standardWorkSec;
    }

    public void setStandardWorkSec(Integer standardWorkSec) {
        this.standardWorkSec = standardWorkSec;
    }

    public Integer getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(Integer earliestTime) {
        this.earliestTime = earliestTime;
    }

    public Integer getLastTime() {
        return lastTime;
    }

    public void setLastTime(Integer lastTime) {
        this.lastTime = lastTime;
    }

    public Integer getExceptDays() {
        return exceptDays;
    }

    public void setExceptDays(Integer exceptDays) {
        this.exceptDays = exceptDays;
    }

    public Integer getWorkDays() {
        return workDays;
    }

    public void setWorkDays(Integer workDays) {
        this.workDays = workDays;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DayReportSummaryInfo.class.getSimpleName() + "[", "]")
                .add("checkinCount=" + checkinCount)
                .add("regularWorkSec=" + regularWorkSec)
                .add("standardWorkSec=" + standardWorkSec)
                .add("earliestTime=" + earliestTime)
                .add("lastTime=" + lastTime)
                .add("exceptDays=" + exceptDays)
                .add("workDays=" + workDays)
                .toString();
    }
}
