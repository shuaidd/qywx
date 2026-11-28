package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingRecordAttachment
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingRecordAttachment {
    /**
     * 文件下载地址
     **/
    @JsonProperty("download_address")
    private String downloadAddress;

    /**
     * 文件类型
     **/
    @JsonProperty("file_type")
    private String fileType;

}
