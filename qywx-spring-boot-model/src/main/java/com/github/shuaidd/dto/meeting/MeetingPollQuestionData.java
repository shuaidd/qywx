package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 会议投票问题和结果数据
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingPollQuestionData {

    /**
     * 投票问题描述
     **/
    @JsonProperty("question_desc")
    private String questionDesc;

    /**
     * 问题类型：0 单选、1 多选
     **/
    @JsonProperty("question_type")
    private Integer questionType;

    /**
     * 投票问题 ID
     **/
    @JsonProperty("question_id")
    private String questionId;

    /**
     * 问题选项统计列表
     **/
    @JsonProperty("option_info")
    private List<MeetingPollOptionData> optionInfo;
}
