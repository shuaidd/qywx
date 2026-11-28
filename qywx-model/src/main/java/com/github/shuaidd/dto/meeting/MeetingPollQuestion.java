package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 会议投票问题
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPollQuestion {

    /**
     * 投票问题描述，最长 50 个字符
     **/
    @JsonProperty("question_desc")
    private String questionDesc;

    /**
     * 问题类型：0 单选、1 多选
     **/
    @JsonProperty("question_type")
    private Integer questionType;

    /**
     * 投票选项列表，创建时最少 2 个、修改时最少 1 个
     **/
    @JsonProperty("poll_option")
    private List<String> pollOption;
}
