package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报任务详情
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportJobInfo {

    /**
     * 任务名称
     */
    @JsonProperty("title")
    private String title;

    /**
     * 发起人的userid
     */
    @JsonProperty("creator")
    private String creator;

    /**
     * 任务类型。1表示企业内部成员，2表示家长
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 任务适用范围
     */
    @JsonProperty("apply_range")
    private HealthReportApplyRange applyRange;

    /**
     * 任务汇报对象
     */
    @JsonProperty("report_to")
    private HealthReportReportTo reportTo;

    /**
     * 上报方式。1表示仅上报一次，2表示每天都上报
     */
    @JsonProperty("report_type")
    private Integer reportType;

    /**
     * 非工作日是否需要上报。0表示周末需要上报，1表示周末不需要上报
     */
    @JsonProperty("skip_weekend")
    private Integer skipWeekend;

    /**
     * 已填表人数
     */
    @JsonProperty("finish_cnt")
    private Integer finishCnt;

    /**
     * 健康上报问题模板列表
     */
    @JsonProperty("question_templates")
    private List<HealthReportQuestionTemplate> questionTemplates;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public HealthReportApplyRange getApplyRange() {
        return applyRange;
    }

    public void setApplyRange(HealthReportApplyRange applyRange) {
        this.applyRange = applyRange;
    }

    public HealthReportReportTo getReportTo() {
        return reportTo;
    }

    public void setReportTo(HealthReportReportTo reportTo) {
        this.reportTo = reportTo;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getSkipWeekend() {
        return skipWeekend;
    }

    public void setSkipWeekend(Integer skipWeekend) {
        this.skipWeekend = skipWeekend;
    }

    public Integer getFinishCnt() {
        return finishCnt;
    }

    public void setFinishCnt(Integer finishCnt) {
        this.finishCnt = finishCnt;
    }

    public List<HealthReportQuestionTemplate> getQuestionTemplates() {
        return questionTemplates;
    }

    public void setQuestionTemplates(List<HealthReportQuestionTemplate> questionTemplates) {
        this.questionTemplates = questionTemplates;
    }
}
