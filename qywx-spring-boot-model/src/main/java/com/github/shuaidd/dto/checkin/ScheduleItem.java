package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.LateRule;
import com.github.shuaidd.dto.TimeSection;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 16:39
 **/
public class ScheduleItem {

    @JsonProperty("schedule_id")
    private Long scheduleId;

    @JsonProperty("schedule_name")
    private String scheduleName;

    @JsonProperty("limit_aheadtime")
    private Long limitAheadTime;

    @JsonProperty("noneed_offwork")
    private Boolean noNeedOffWork;

    @JsonProperty("limit_offtime")
    private Integer limitOffTime;

    @JsonProperty("flex_on_duty_time")
    private Integer flexOnDutyTime;

    @JsonProperty("flex_off_duty_time")
    private Integer flexOffDutyTime;

    @JsonProperty("allow_flex")
    private Boolean allowFlex;

    @JsonProperty("max_allow_arrive_early")
    private Integer maxAllowArriveEarly;

    @JsonProperty("max_allow_arrive_late")
    private Integer maxAllowArriveLate;

    @JsonProperty("time_section")
    private List<TimeSection> timeSections;

    @JsonProperty("late_rule")
    private LateRule lateRule;

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
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

    public Integer getLimitOffTime() {
        return limitOffTime;
    }

    public void setLimitOffTime(Integer limitOffTime) {
        this.limitOffTime = limitOffTime;
    }

    public Integer getFlexOnDutyTime() {
        return flexOnDutyTime;
    }

    public void setFlexOnDutyTime(Integer flexOnDutyTime) {
        this.flexOnDutyTime = flexOnDutyTime;
    }

    public Integer getFlexOffDutyTime() {
        return flexOffDutyTime;
    }

    public void setFlexOffDutyTime(Integer flexOffDutyTime) {
        this.flexOffDutyTime = flexOffDutyTime;
    }

    public Boolean getAllowFlex() {
        return allowFlex;
    }

    public void setAllowFlex(Boolean allowFlex) {
        this.allowFlex = allowFlex;
    }

    public Integer getMaxAllowArriveEarly() {
        return maxAllowArriveEarly;
    }

    public void setMaxAllowArriveEarly(Integer maxAllowArriveEarly) {
        this.maxAllowArriveEarly = maxAllowArriveEarly;
    }

    public Integer getMaxAllowArriveLate() {
        return maxAllowArriveLate;
    }

    public void setMaxAllowArriveLate(Integer maxAllowArriveLate) {
        this.maxAllowArriveLate = maxAllowArriveLate;
    }

    public List<TimeSection> getTimeSections() {
        return timeSections;
    }

    public void setTimeSections(List<TimeSection> timeSections) {
        this.timeSections = timeSections;
    }

    public LateRule getLateRule() {
        return lateRule;
    }

    public void setLateRule(LateRule lateRule) {
        this.lateRule = lateRule;
    }
}
