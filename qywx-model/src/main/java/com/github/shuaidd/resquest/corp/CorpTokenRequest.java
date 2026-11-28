package com.github.shuaidd.resquest.corp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CorpTokenRequest {

    @JsonProperty("corpid")
    private String corpId;

    @JsonProperty("business_type")
    private Integer businessType;

    @JsonProperty("agentid")
    private Integer agentId;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
}
