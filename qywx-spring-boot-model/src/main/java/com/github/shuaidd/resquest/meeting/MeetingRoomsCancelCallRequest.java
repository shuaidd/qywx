package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsMra;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 取消呼叫Rooms会议室请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MeetingRoomsCancelCallRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 呼叫ID
     **/
    @JsonProperty("invite_id")
    private String inviteId;
    /**
     * Rooms会议室 ID，与mraAddress二选一
     **/
    @JsonProperty("meeting_room_id")
    private String meetingRoomId;
    /**
     * MRA地址信息，与meetingRoomId二选一
     **/
    @JsonProperty("mra_address")
    private RoomsMra mraAddress;
}
