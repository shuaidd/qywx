package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 12:27
 **/
public class CheckInDate {

    @JsonProperty("flex_time")
    private Long flexTime;

    @JsonProperty("limit_aheadtime")
    private Long limitAheadTime;

    @JsonProperty("noneed_offwork")
    private Boolean noNeedOffWork;

    private List<Integer> workdays;

    @JsonProperty("checkintime")
    private List<CheckInTime> checkInTimes;

    @JsonProperty("flex_on_duty_time")
    private Long flexOnDutyTime;

    @JsonProperty("flex_off_duty_time")
    private Long flexOffDutyTime;

    public Long getFlexTime() {
        return flexTime;
    }

    public void setFlexTime(Long flexTime) {
        this.flexTime = flexTime;
    }

    public Long getLimitAheadTime() {
        return limitAheadTime;
    }

    public void setLimitAheadTime(Long limitAheadTime) {
        this.limitAheadTime = limitAheadTime;
    }

    public Boolean getNoNeedOffWork() {
        return noNeedOffWork;
    }

    public void setNoNeedOffWork(Boolean noNeedOffWork) {
        this.noNeedOffWork = noNeedOffWork;
    }

    public List<Integer> getWorkdays() {
        return workdays;
    }

    public void setWorkdays(List<Integer> workdays) {
        this.workdays = workdays;
    }

    public List<CheckInTime> getCheckInTimes() {
        return checkInTimes;
    }

    public void setCheckInTimes(List<CheckInTime> checkInTimes) {
        this.checkInTimes = checkInTimes;
    }

    public Long getFlexOnDutyTime() {
        return flexOnDutyTime;
    }

    public void setFlexOnDutyTime(Long flexOnDutyTime) {
        this.flexOnDutyTime = flexOnDutyTime;
    }

    public Long getFlexOffDutyTime() {
        return flexOffDutyTime;
    }

    public void setFlexOffDutyTime(Long flexOffDutyTime) {
        this.flexOffDutyTime = flexOffDutyTime;
    }
}
