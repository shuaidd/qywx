package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormInfo {

    /**
     * 收集表id
     */
    @JsonProperty("formid")
    private String formId;

    /**
     * 收集表标题
     */
    @JsonProperty("form_title")
    private String formTitle;

    /**
     * 收集表描述
     */
    @JsonProperty("form_desc")
    private String formDesc;

    /**
     * 收集表表头背景图链接
     */
    @JsonProperty("form_header")
    private String formHeader;

    /**
     * 收集表的问题列表
     */
    @JsonProperty("form_question")
    private FormQuestion formQuestion;

    /**
     * 收集表的设置
     */
    @JsonProperty("form_setting")
    private FormSetting formSetting;

    /**
     * 收集表的周期id列表，用于获取统计和答案
     */
    @JsonProperty("repeated_id")
    private List<String> repeatedId;

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormDesc() {
        return formDesc;
    }

    public void setFormDesc(String formDesc) {
        this.formDesc = formDesc;
    }

    public String getFormHeader() {
        return formHeader;
    }

    public void setFormHeader(String formHeader) {
        this.formHeader = formHeader;
    }

    public FormQuestion getFormQuestion() {
        return formQuestion;
    }

    public void setFormQuestion(FormQuestion formQuestion) {
        this.formQuestion = formQuestion;
    }

    public FormSetting getFormSetting() {
        return formSetting;
    }

    public void setFormSetting(FormSetting formSetting) {
        this.formSetting = formSetting;
    }

    public List<String> getRepeatedId() {
        return repeatedId;
    }

    public void setRepeatedId(List<String> repeatedId) {
        this.repeatedId = repeatedId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("formId", formId)
                .append("formTitle", formTitle)
                .append("formDesc", formDesc)
                .append("formHeader", formHeader)
                .append("formQuestion", formQuestion)
                .append("formSetting", formSetting)
                .append("repeatedId", repeatedId)
                .toString();
    }
}
