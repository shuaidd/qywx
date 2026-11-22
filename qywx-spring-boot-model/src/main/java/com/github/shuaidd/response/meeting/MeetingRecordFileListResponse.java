package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingRecordFileInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingRecordFileListResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRecordFileListResponse extends AbstractBaseResponse {
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
     * 会议Code
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;

    /**
     * 会议主题
     **/
    @JsonProperty("title")
    private String title;

    /**
     * 录制文件列表
     **/
    @JsonProperty("record_files")
    private List<MeetingRecordFileInfo> recordFiles;

}
