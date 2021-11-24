package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 13:33
 **/
public class EmergencyCallState {
    private Integer code;

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("callid")
    private String callId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }
}
