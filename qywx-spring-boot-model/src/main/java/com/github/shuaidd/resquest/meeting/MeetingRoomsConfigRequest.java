package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询Rooms会议室配置请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRoomsConfigRequest {
    /**
     * Rooms会议室 ID
     **/
    @JsonProperty("meeting_room_id")
    private String meetingRoomId;
}
