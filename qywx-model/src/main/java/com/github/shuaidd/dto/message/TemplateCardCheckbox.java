package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 模版卡片选择题样式
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardCheckbox {

    @JsonProperty("question_key")
    private String questionKey;

    @JsonProperty("option_list")
    private List<TemplateCardOption> optionList;

    private Boolean disable;

    private Integer mode;

    public String getQuestionKey() { return questionKey; }
    public void setQuestionKey(String questionKey) { this.questionKey = questionKey; }
    public List<TemplateCardOption> getOptionList() { return optionList; }
    public void setOptionList(List<TemplateCardOption> optionList) { this.optionList = optionList; }
    public Boolean getDisable() { return disable; }
    public void setDisable(Boolean disable) { this.disable = disable; }
    public Integer getMode() { return mode; }
    public void setMode(Integer mode) { this.mode = mode; }
}