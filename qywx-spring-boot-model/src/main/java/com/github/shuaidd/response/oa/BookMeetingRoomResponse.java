package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 13:23
 **/
public class BookMeetingRoomResponse extends AbstractBaseResponse {

    @JsonProperty("meeting_id")
    private String meetingId;

    @JsonProperty("schedule_id")
    private String scheduleId;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", BookMeetingRoomResponse.class.getSimpleName() + "[", "]")
                .add("meetingId='" + meetingId + "'")
                .add("scheduleId='" + scheduleId + "'")
                .toString();
    }
}
