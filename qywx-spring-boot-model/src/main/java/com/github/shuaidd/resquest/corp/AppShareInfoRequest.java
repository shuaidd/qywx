package com.github.shuaidd.resquest.corp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.CursorPageRequest;

public class AppShareInfoRequest extends CursorPageRequest {

    @JsonProperty("agentid")
    private Integer agentId;

    @JsonProperty("business_type")
    private Integer businessType;

    @JsonProperty("corpid")
    private String corpId;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }
}
