package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 13:37
 **/
public class GetCallStateRequest {

    @JsonProperty("callee_userid")
    private String calleeUserId;

    @JsonProperty("callid")
    private String callId;

    public String getCalleeUserId() {
        return calleeUserId;
    }

    public void setCalleeUserId(String calleeUserId) {
        this.calleeUserId = calleeUserId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetCallStateRequest.class.getSimpleName() + "[", "]")
                .add("calleeUserId='" + calleeUserId + "'")
                .add("callId='" + callId + "'")
                .toString();
    }
}
