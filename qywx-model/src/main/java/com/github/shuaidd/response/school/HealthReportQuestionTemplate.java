package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报问题模板
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportQuestionTemplate {

    /**
     * 问题的question_id
     */
    @JsonProperty("question_id")
    private Integer questionId;

    /**
     * 问题标题
     */
    @JsonProperty("title")
    private String title;

    /**
     * 问题类型。1表示填空题(包括地理位置和日期)，2表示单选题，3表示多选题，4表示收集文件/图片题，5表示签名链接，6表示行程卡截图
     */
    @JsonProperty("question_type")
    private Integer questionType;

    /**
     * 问题是否必填。1表示是，0表示否
     */
    @JsonProperty("is_required")
    private Integer isRequired;

    /**
     * 选项列表，仅单选/多选题返回
     */
    @JsonProperty("option_list")
    private List<HealthReportQuestionOption> optionList;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
    }

    public List<HealthReportQuestionOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<HealthReportQuestionOption> optionList) {
        this.optionList = optionList;
    }
}
