package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 13:31
 **/
public class EmergencyCallRequest {

    @JsonProperty("callee_userid")
    private List<String> calleeUserIds;

    public List<String> getCalleeUserIds() {
        return calleeUserIds;
    }

    public void setCalleeUserIds(List<String> calleeUserIds) {
        this.calleeUserIds = calleeUserIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmergencyCallRequest.class.getSimpleName() + "[", "]")
                .add("calleeUserIds=" + calleeUserIds)
                .toString();
    }
}
