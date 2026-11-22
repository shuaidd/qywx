package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MeetingRecordFileRequest
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRecordFileRequest {
    /**
     * 录制文件ID
     **/
    @JsonProperty("record_file_id")
    private String recordFileId;

    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

}
