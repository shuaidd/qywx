package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * 会议报名答题详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingAnswer {

    /**
     * 报名问题回答内容
     **/
    @JsonProperty("answer_content")
    private List<String> answerContent;

    /**
     * 问题是否必填：1 否、2 是
     **/
    @JsonProperty("is_required")
    private Integer isRequired;

    /**
     * 报名问题编号
     **/
    @JsonProperty("question_num")
    private Integer questionNum;

    /**
     * 报名问题标题
     **/
    @JsonProperty("question_title")
    private String questionTitle;

    /**
     * 报名问题类型：1 单选、2 多选、3 简答
     **/
    @JsonProperty("question_type")
    private Integer questionType;

    /**
     * 特殊问题类型：1 无、2 手机号、3 邮箱、4 姓名、5 公司名称
     **/
    @JsonProperty("special_type")
    private Integer specialType;
}