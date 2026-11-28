package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingTranscriptWord
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptWord {
    /**
     * 词ID
     **/
    @JsonProperty("wid")
    private String wid;

    /**
     * 词开始时间，单位毫秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 词结束时间，单位毫秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 文本内容
     **/
    @JsonProperty("text")
    private String text;

}
