package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingRecordFile
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecordFile {
    /**
     * 录制文件ID
     **/
    @JsonProperty("record_file_id")
    private String recordFileId;

    /**
     * 开始录制时间，单位毫秒
     **/
    @JsonProperty("record_start_time")
    private Long recordStartTime;

    /**
     * 结束录制时间，单位毫秒
     **/
    @JsonProperty("record_end_time")
    private Long recordEndTime;

    /**
     * 文件大小，单位字节
     **/
    @JsonProperty("record_size")
    private Integer recordSize;

    /**
     * 共享状态，0未开启，1开启
     **/
    @JsonProperty("sharing_state")
    private Integer sharingState;

    /**
     * 共享链接
     **/
    @JsonProperty("sharing_url")
    private String sharingUrl;

    /**
     * 是否仅企业成员可查看
     **/
    @JsonProperty("required_same_corp")
    private Boolean requiredSameCorp;

    /**
     * 是否仅参会成员可查看
     **/
    @JsonProperty("required_attendee")
    private Boolean requiredAttendee;

    /**
     * 访问密码
     **/
    @JsonProperty("password")
    private String password;

    /**
     * 共享链接有效期，单位毫秒
     **/
    @JsonProperty("sharing_expire")
    private Long sharingExpire;

    /**
     * 是否允许下载
     **/
    @JsonProperty("allow_download")
    private Boolean allowDownload;

}
