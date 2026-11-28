package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingMraDevice;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 设置会议MRA举手状态请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingMraRaiseHandRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 举手操作：true 举手、false 手放下
     **/
    @JsonProperty("raise_hand")
    private Boolean raiseHand;
    /**
     * 被操作MRA设备
     **/
    @JsonProperty("mra")
    private MeetingMraDevice mra;
}
