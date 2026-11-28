package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-01 17:59
 **/
public class DelKnowledgeIntentRequest {

    @JsonProperty(value = "intent_id")
    private String intentId;

    public String getIntentId() {
        return intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }
}
