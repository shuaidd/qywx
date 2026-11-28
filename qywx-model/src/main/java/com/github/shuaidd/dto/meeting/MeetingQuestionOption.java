package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 会议报名问题选项
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingQuestionOption {

    /**
     * 报名问题选项内容
     **/
    @JsonProperty("content")
    private String content;
}