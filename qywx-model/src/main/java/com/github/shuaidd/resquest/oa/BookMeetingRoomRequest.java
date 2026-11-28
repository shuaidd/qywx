package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 13:22
 **/
public class BookMeetingRoomRequest {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    @JsonProperty("start_time")
    private Long startTime;

    @JsonProperty("end_time")
    private Long endTime;

    private String subject;

    private String booker;

    private List<String> attendees;

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookMeetingRoomRequest.class.getSimpleName() + "[", "]")
                .add("meetingRoomId='" + meetingRoomId + "'")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("subject='" + subject + "'")
                .add("booker='" + booker + "'")
                .add("attendees=" + attendees)
                .toString();
    }
}
