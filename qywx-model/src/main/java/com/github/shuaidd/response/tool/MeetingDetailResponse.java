package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.MeetingDetailAttendee;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 17:39
 **/
public class MeetingDetailResponse extends AbstractBaseResponse {

    @JsonProperty("creator_userid")
    private String creatorUserId;

    @JsonProperty("meetingid")
    private String meetingId;

    private String title;

    @JsonProperty("reserve_meeting_start")
    private Date reserveMeetingStart;

    @JsonProperty("reserve_meeting_duration")
    private Integer reserveMeetingDuration;

    @JsonProperty("main_department")
    private Integer mainDepartment;

    @JsonProperty("meeting_start")
    private Date meetingStart;

    @JsonProperty("meeting_duration")
    private Integer meetingDuration;

    private String description;
    private Integer type;

    @JsonProperty("remind_time")
    private Integer remindTime;

    @JsonProperty("schedule_id")
    private String scheduleId;

    private List<MeetingDetailAttendee> attendees;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getMeetingStart() {
        return meetingStart;
    }

    public void setMeetingStart(Date meetingStart) {
        this.meetingStart = meetingStart;
    }

    public Integer getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(Integer meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Date getReserveMeetingStart() {
        return reserveMeetingStart;
    }

    public void setReserveMeetingStart(Date reserveMeetingStart) {
        this.reserveMeetingStart = reserveMeetingStart;
    }

    public Integer getReserveMeetingDuration() {
        return reserveMeetingDuration;
    }

    public void setReserveMeetingDuration(Integer reserveMeetingDuration) {
        this.reserveMeetingDuration = reserveMeetingDuration;
    }

    public Integer getMainDepartment() {
        return mainDepartment;
    }

    public void setMainDepartment(Integer mainDepartment) {
        this.mainDepartment = mainDepartment;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<MeetingDetailAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<MeetingDetailAttendee> attendees) {
        this.attendees = attendees;
    }
}
