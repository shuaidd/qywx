package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.MeetingAttendee;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 14:23
 **/
public class MeetingRequest {
    @JsonProperty("meetingid")
    private String meetingId;
    @JsonProperty("creator_userid")
    private String creatorUserId;
    private String title;
    @JsonProperty("meeting_start")
    private Date meetingStart;
    @JsonProperty("meeting_duration")
    private Integer meetingDuration;
    private String description;
    private Integer type;
    @JsonProperty("remind_time")
    private Integer remindTime;
    @JsonProperty("agentid")
    private Integer agentId;
    private List<MeetingAttendee> attendees;

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(String creatorUserId) {
        this.creatorUserId = creatorUserId;
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

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public List<MeetingAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<MeetingAttendee> attendees) {
        this.attendees = attendees;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
}
