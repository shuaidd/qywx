package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 健康上报问题选项
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportQuestionOption {

    /**
     * 选项id
     */
    @JsonProperty("option_id")
    private Integer optionId;

    /**
     * 选项文案说明
     */
    @JsonProperty("option_text")
    private String optionText;

    public Integer getOptionId() {
        return optionId;
    }

    public void setOptionId(Integer optionId) {
        this.optionId = optionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
