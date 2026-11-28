package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 预定Rooms会议室请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRoomsBookRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 待预定的Rooms会议室ID列表
     **/
    @JsonProperty("meeting_room_id_list")
    private List<String> meetingRoomIdList;
    /**
     * 会议开始前一小时内是否在Room显示会议主题
     **/
    @JsonProperty("subject_visible")
    private Boolean subjectVisible;
}
