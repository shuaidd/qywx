package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

import lombok.Data;

/**
 * MeetingTranscriptDetail
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingTranscriptDetail {
    /**
     * 段落列表
     **/
    @JsonProperty("paragraphs")
    private List<MeetingTranscriptParagraph> paragraphs;

    /**
     * 关键词列表
     **/
    @JsonProperty("keywords")
    private List<String> keywords;

    /**
     * 声纹识别状态，0未完成，1已完成
     **/
    @JsonProperty("audio_detect")
    private Integer audioDetect;

}
