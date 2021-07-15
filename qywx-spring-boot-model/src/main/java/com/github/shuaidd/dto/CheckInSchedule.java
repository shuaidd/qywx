package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 14:25
 **/
public class CheckInSchedule {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("yearmonth")
    private String yearMonth;

    @JsonProperty("groupid")
    private Integer groupId;

    @JsonProperty("groupname")
    private String groupName;

    private CheckInScheduleData schedule;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

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

    public CheckInScheduleData getSchedule() {
        return schedule;
    }

    public void setSchedule(CheckInScheduleData schedule) {
        this.schedule = schedule;
    }
}
