package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 模版卡片下拉选择器
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TemplateCardSelection {

    @JsonProperty("question_key")
    private String questionKey;

    private String title;

    @JsonProperty("option_list")
    private List<TemplateCardOption> optionList;

    @JsonProperty("selected_id")
    private String selectedId;

    private Boolean disable;

    public String getQuestionKey() { return questionKey; }
    public void setQuestionKey(String questionKey) { this.questionKey = questionKey; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<TemplateCardOption> getOptionList() { return optionList; }
    public void setOptionList(List<TemplateCardOption> optionList) { this.optionList = optionList; }
    public String getSelectedId() { return selectedId; }
    public void setSelectedId(String selectedId) { this.selectedId = selectedId; }
    public Boolean getDisable() { return disable; }
    public void setDisable(Boolean disable) { this.disable = disable; }
}