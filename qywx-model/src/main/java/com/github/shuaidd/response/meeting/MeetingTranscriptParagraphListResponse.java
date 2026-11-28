package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import java.util.List;
import com.github.shuaidd.dto.meeting.MeetingTranscriptParagraph;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * MeetingTranscriptParagraphListResponse
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingTranscriptParagraphListResponse extends AbstractBaseResponse {
    /**
     * 声纹识别状态，0未完成，1已完成
     **/
    @JsonProperty("audio_detect")
    private Integer audioDetect;

    /**
     * 转写段落列表
     **/
    @JsonProperty("paragraphs")
    private List<MeetingTranscriptParagraph> paragraphs;

}
