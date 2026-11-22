package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingGuest;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

/**
 * 会议嘉宾列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingGuestListResponse extends AbstractBaseResponse {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 会议入会码
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;

    /**
     * 会议主题
     **/
    @JsonProperty("title")
    private String title;

    /**
     * 会议嘉宾列表
     **/
    @JsonProperty("guests")
    private List<MeetingGuest> guests;
}