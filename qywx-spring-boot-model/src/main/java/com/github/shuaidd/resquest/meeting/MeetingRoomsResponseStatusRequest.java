package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsMra;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询Rooms呼叫应答状态请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRoomsResponseStatusRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
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
