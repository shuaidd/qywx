package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:46
 **/
public class GetMeetingForUserResponse extends AbstractBaseResponse {

    @JsonProperty("meetingid_list")
    private List<String> meetingIdList;

    public List<String> getMeetingIdList() {
        return meetingIdList;
    }

    public void setMeetingIdList(List<String> meetingIdList) {
        this.meetingIdList = meetingIdList;
    }
}
