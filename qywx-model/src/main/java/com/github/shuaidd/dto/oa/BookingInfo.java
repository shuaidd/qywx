package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 13:14
 **/
public class BookingInfo {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    private List<BookingSchedule> schedule;

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public List<BookingSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<BookingSchedule> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookingInfo.class.getSimpleName() + "[", "]")
                .add("meetingRoomId='" + meetingRoomId + "'")
                .add("schedule=" + schedule)
                .toString();
    }
}
