package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.template.TemplateContent;
import com.github.shuaidd.dto.template.TemplateText;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 创建审批模板请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CreateApprovalTemplateRequest {

    /**
     * 模板名称数组；text为模板名称，不能与现有模板重复且不超过40个字符，lang为显示语言，中文使用zh_CN
     */
    @JsonProperty("template_name")
    private List<TemplateText> templateName;

    /**
     * 审批模板控件设置，由多个表单控件及其内容组成
     */
    @JsonProperty("template_content")
    private TemplateContent templateContent;

    public List<TemplateText> getTemplateName() {
        return templateName;
    }

    public void setTemplateName(List<TemplateText> templateName) {
        this.templateName = templateName;
    }

    public TemplateContent getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(TemplateContent templateContent) {
        this.templateContent = templateContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("templateName", templateName)
                .append("templateContent", templateContent)
                .toString();
    }
}
