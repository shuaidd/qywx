package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:38
 **/
public class DeleteWelcomeTemplateResponse {
    @JsonProperty("template_id")
    private String templateId;
    @JsonProperty("agentid")
    private String agentId;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
