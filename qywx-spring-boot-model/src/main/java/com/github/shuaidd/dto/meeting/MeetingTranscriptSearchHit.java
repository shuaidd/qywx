package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingTranscriptSearchHit
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptSearchHit {
    /**
     * 段落ID
     **/
    @JsonProperty("pid")
    private String pid;

    /**
     * 句子ID
     **/
    @JsonProperty("sid")
    private String sid;

    /**
     * 相对词偏移量
     **/
    @JsonProperty("offset")
    private Integer offset;

    /**
     * 匹配长度
     **/
    @JsonProperty("length")
    private Integer length;

}
