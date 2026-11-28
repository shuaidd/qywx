package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsMeetingInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms会议室会议列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsMeetingListResponse extends AbstractBaseResponse {
    /**
     * Rooms会议室关联会议列表
     **/
    @JsonProperty("meeting_info_list")
    private List<RoomsMeetingInfo> meetingInfoList;
}
