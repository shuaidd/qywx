package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室简要信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsMeetingRoom {
    /**
     * Rooms会议室 ID
     **/
    @JsonProperty("meeting_room_id")
    private String meetingRoomId;
    /**
     * Rooms会议室名称
     **/
    @JsonProperty("meeting_room_name")
    private String meetingRoomName;
    /**
     * Rooms会议室地址
     **/
    @JsonProperty("meeting_room_location")
    private String meetingRoomLocation;
    /**
     * 账号类型：0普通、1专款、2试用
     **/
    @JsonProperty("account_type")
    private Integer accountType;
    /**
     * 激活码
     **/
    @JsonProperty("active_code")
    private String activeCode;
    /**
     * 容纳人数
     **/
    @JsonProperty("participant_number")
    private Integer participantNumber;
    /**
     * 会议室状态：0未激活、1未绑定、2空闲、3使用中、4离线
     **/
    @JsonProperty("meeting_room_status")
    private Integer meetingRoomStatus;
    /**
     * 预定状态：0未开放预定、1开放预定
     **/
    @JsonProperty("scheduled_status")
    private Integer scheduledStatus;
    /**
     * 是否允许被呼叫
     **/
    @JsonProperty("is_allow_call")
    private Boolean allowCall;
}
