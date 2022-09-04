package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-04 21:16
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformExternalUserIdRequest {

    @JsonProperty("external_userid")
    private String externalUserId;

    @JsonProperty("source_agentid")
    private int sourceAgentId;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public int getSourceAgentId() {
        return sourceAgentId;
    }

    public void setSourceAgentId(int sourceAgentId) {
        this.sourceAgentId = sourceAgentId;
    }
}
