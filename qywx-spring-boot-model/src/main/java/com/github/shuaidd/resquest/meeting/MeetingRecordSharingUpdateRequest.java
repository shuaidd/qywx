package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRecordSharingConfig;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MeetingRecordSharingUpdateRequest
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRecordSharingUpdateRequest {
    /**
     * 会议录制ID
     **/
    @JsonProperty("meeting_record_id")
    private String meetingRecordId;

    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 录制共享配置
     **/
    @JsonProperty("sharing_config")
    private MeetingRecordSharingConfig sharingConfig;

}
