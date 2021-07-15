package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:37
 **/
public class ApproveTemplate {

    @JsonProperty("template_names")
    private List<TemplateText> templateNames;

    @JsonProperty("template_content")
    private TemplateContent content;

    public List<TemplateText> getTemplateNames() {
        return templateNames;
    }

    public void setTemplateNames(List<TemplateText> templateNames) {
        this.templateNames = templateNames;
    }

    public TemplateContent getContent() {
        return content;
    }

    public void setContent(TemplateContent content) {
        this.content = content;
    }
}
