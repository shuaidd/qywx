package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 健康上报填写答案响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportAnswerResponse extends AbstractBaseResponse {

    /**
     * 填写答案列表
     */
    @JsonProperty("answers")
    private List<HealthReportAnswer> answers;

    public List<HealthReportAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<HealthReportAnswer> answers) {
        this.answers = answers;
    }
}
