package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 13:36
 **/
public class ScheduleData {
    @JsonProperty("schedule_id")
    private String scheduleId;
    private String organizer;
    @JsonProperty("start_time")
    private Date startTime;
    @JsonProperty("end_time")
    private Date endTime;
    private List<UserId> attendees;
    private String summary;
    private String description;
    private String location;
    @JsonProperty("cal_id")
    private String calId;
    private ReminderData reminders;
    private Integer status;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<UserId> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UserId> attendees) {
        this.attendees = attendees;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }

    public ReminderData getReminders() {
        return reminders;
    }

    public void setReminders(ReminderData reminders) {
        this.reminders = reminders;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
