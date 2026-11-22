package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;

/**
 * MeetingTranscriptParagraph
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptParagraph {
    /**
     * 段落ID
     **/
    @JsonProperty("pid")
    private String pid;

    /**
     * 段落开始时间，单位毫秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 段落结束时间，单位毫秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 发言人信息
     **/
    @JsonProperty("speaker_info")
    private MeetingTranscriptSpeaker speakerInfo;

    /**
     * 句子列表
     **/
    @JsonProperty("sentences")
    private List<MeetingTranscriptSentence> sentences;

}
