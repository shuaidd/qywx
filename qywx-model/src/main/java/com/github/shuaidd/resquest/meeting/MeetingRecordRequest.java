package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MeetingRecordRequest
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRecordRequest {
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

}
