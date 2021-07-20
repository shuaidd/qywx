package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 * "istalked":1,
 *     "calltime":1557306531,
 *     "talktime":2,
 *     "reason":0
 * @author ddshuai
 * @date 2021-07-20 13:39
 **/
public class GetCallStateResponse extends AbstractBaseResponse {

    @JsonProperty("istalked")
    private Integer isTalked;

    @JsonProperty("calltime")
    private Long callTime;

    @JsonProperty("talktime")
    private Integer talkTime;

    private Integer reason;

    public Integer getIsTalked() {
        return isTalked;
    }

    public void setIsTalked(Integer isTalked) {
        this.isTalked = isTalked;
    }

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetCallStateResponse.class.getSimpleName() + "[", "]")
                .add("isTalked=" + isTalked)
                .add("callTime=" + callTime)
                .add("talkTime=" + talkTime)
                .add("reason=" + reason)
                .toString();
    }
}
