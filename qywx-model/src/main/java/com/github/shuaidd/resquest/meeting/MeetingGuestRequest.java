package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingGuest;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.List;

/**
 * 获取或更新会议嘉宾请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingGuestRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 会议嘉宾列表，更新时必填
     **/
    @JsonProperty("guests")
    private List<MeetingGuest> guests;
}