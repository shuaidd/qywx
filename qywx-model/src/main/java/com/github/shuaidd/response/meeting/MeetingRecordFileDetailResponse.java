package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.MeetingRecordAttachment;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingRecordFileDetailResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRecordFileDetailResponse extends AbstractBaseResponse {
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
     * 录制文件ID
     **/
    @JsonProperty("record_file_id")
    private String recordFileId;

    /**
     * 播放地址
     **/
    @JsonProperty("view_address")
    private String viewAddress;

    /**
     * 视频下载地址
     **/
    @JsonProperty("download_address")
    private String downloadAddress;

    /**
     * 视频下载文件格式
     **/
    @JsonProperty("download_address_file_type")
    private String downloadAddressFileType;

    /**
     * 音频下载地址
     **/
    @JsonProperty("audio_address")
    private String audioAddress;

    /**
     * 音频下载文件格式
     **/
    @JsonProperty("audio_address_file_type")
    private String audioAddressFileType;

    /**
     * 录制文件名
     **/
    @JsonProperty("record_name")
    private String recordName;

    /**
     * 录制开始时间，单位毫秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 录制结束时间，单位毫秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 会议录制名
     **/
    @JsonProperty("meeting_record_name")
    private String meetingRecordName;

    /**
     * 会议纪要文件列表
     **/
    @JsonProperty("meeting_summary")
    private List<MeetingRecordAttachment> meetingSummary;

    /**
     * 智能优化版录制转写文件列表
     **/
    @JsonProperty("ai_meeting_transcripts")
    private List<MeetingRecordAttachment> aiMeetingTranscripts;

}
