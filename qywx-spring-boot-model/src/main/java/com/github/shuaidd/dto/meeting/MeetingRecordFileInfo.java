package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;

/**
 * MeetingRecordFileInfo
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecordFileInfo {
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
     * 会议纪要文件列表
     **/
    @JsonProperty("meeting_summary")
    private List<MeetingRecordAttachment> meetingSummary;

}
