package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingMraDevice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 挂断会议MRA呼叫请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingMraHangupRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 被操作MRA设备
     **/
    @JsonProperty("mra")
    private MeetingMraDevice mra;
}
