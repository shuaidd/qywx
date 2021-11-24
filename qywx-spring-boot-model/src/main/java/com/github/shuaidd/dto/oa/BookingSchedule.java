package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 13:15
 **/
public class BookingSchedule {

    @JsonProperty("meeting_id")
    private String meetingId;

    @JsonProperty("schedule_id")
    private String scheduleId;

    @JsonProperty("start_time")
    private Long startTime;

    @JsonProperty("end_time")
    private Long endTime;

    private String booker;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
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

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookingSchedule.class.getSimpleName() + "[", "]")
                .add("meetingId='" + meetingId + "'")
                .add("scheduleId='" + scheduleId + "'")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("booker='" + booker + "'")
                .toString();
    }
}
