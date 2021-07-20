package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 13:27
 **/
public class CancelBookRequest {

    @JsonProperty("meeting_id")
    private String meetingId;

    @JsonProperty("keep_schedule")
    private Integer keepSchedule;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public Integer getKeepSchedule() {
        return keepSchedule;
    }

    public void setKeepSchedule(Integer keepSchedule) {
        this.keepSchedule = keepSchedule;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CancelBookRequest.class.getSimpleName() + "[", "]")
                .add("meetingId='" + meetingId + "'")
                .add("keepSchedule=" + keepSchedule)
                .toString();
    }
}
