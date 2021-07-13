package com.github.shuaidd.callback;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-13 14:53
 **/
public class JsonText {

    @JsonProperty("encrypt_msg")
    private String encryptMsg;

    @JsonProperty("tousername")
    private String toUserName;

    @JsonProperty("agentid")
    private String agentId;

    public String getEncryptMsg() {
        return encryptMsg;
    }

    public void setEncryptMsg(String encryptMsg) {
        this.encryptMsg = encryptMsg;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
