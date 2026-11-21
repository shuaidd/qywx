package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 创建审批模板响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CreateApprovalTemplateResponse extends AbstractBaseResponse {

    /**
     * 模板创建成功后返回的模板id
     */
    @JsonProperty("template_id")
    private String templateId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
