package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsMeetingRoom;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms会议室列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsListResponse extends AbstractBaseResponse {
    /**
     * Rooms会议室列表
     **/
    @JsonProperty("meeting_room_list")
    private List<RoomsMeetingRoom> meetingRoomList;
}
