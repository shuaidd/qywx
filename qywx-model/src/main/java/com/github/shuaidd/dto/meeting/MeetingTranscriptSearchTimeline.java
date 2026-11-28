package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * MeetingTranscriptSearchTimeline
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptSearchTimeline {
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
     * 开始时间，单位毫秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

}
