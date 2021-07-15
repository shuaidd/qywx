package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 11:28
 **/
public class DayReportRuleInfo {

    @JsonProperty("groupid")
    private Integer groupId;

    @JsonProperty("groupname")
    private String groupName;

    @JsonProperty("scheduleid")
    private Integer scheduleId;

    @JsonProperty("schedulename")
    private String scheduleName;

    @JsonProperty("checkintime")
    private List<CheckInTime> checkInTimes;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public void setScheduleName(String scheduleName) {
        this.scheduleName = scheduleName;
    }

    public List<CheckInTime> getCheckInTimes() {
        return checkInTimes;
    }

    public void setCheckInTimes(List<CheckInTime> checkInTimes) {
        this.checkInTimes = checkInTimes;
    }
}
