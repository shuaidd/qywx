package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsMeetingSettings;
import com.github.shuaidd.dto.meeting.RoomsRecordSettings;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms会议室配置响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsConfigResponse extends AbstractBaseResponse {
    /**
     * Rooms会议室会议配置
     **/
    @JsonProperty("meeting_settings")
    private RoomsMeetingSettings meetingSettings;
    /**
     * Rooms会议室录制配置
     **/
    @JsonProperty("record_settings")
    private RoomsRecordSettings recordSettings;
}
