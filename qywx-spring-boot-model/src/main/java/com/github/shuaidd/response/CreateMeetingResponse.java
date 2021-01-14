package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:29
 **/
public class CreateMeetingResponse extends AbstractBaseResponse {
    @JsonProperty("meetingid")
    private String meetingId;

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }
}
