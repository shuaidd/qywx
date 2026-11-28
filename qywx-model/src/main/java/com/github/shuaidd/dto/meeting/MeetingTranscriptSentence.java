package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;

/**
 * MeetingTranscriptSentence
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptSentence {
    /**
     * 句子ID
     **/
    @JsonProperty("sid")
    private String sid;

    /**
     * 句子开始时间，单位毫秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 句子结束时间，单位毫秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 词列表
     **/
    @JsonProperty("words")
    private List<MeetingTranscriptWord> words;

}
