package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;

/**
 * MeetingRecord
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecord {
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
     * 主持人userid
     **/
    @JsonProperty("host_user_id")
    private String hostUserId;

    /**
     * 会议开始时间，单位毫秒
     **/
    @JsonProperty("meeting_start_time")
    private Long meetingStartTime;

    /**
     * 会议主题
     **/
    @JsonProperty("title")
    private String title;

    /**
     * 录制状态，1录制中，3转码完成
     **/
    @JsonProperty("state")
    private Integer state;

    /**
     * 录制文件列表
     **/
    @JsonProperty("record_file_list")
    private List<MeetingRecordFile> recordFileList;

}
