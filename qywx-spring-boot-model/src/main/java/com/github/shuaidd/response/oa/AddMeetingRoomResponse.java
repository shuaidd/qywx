package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 12:59
 **/
public class AddMeetingRoomResponse extends AbstractBaseResponse {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AddMeetingRoomResponse.class.getSimpleName() + "[", "]")
                .add("meetingRoomId='" + meetingRoomId + "'")
                .toString();
    }
}
