package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-11 13:37
 **/
public class DialRecord {

    @JsonProperty("call_time")
    private Long callTime;
    @JsonProperty("total_duration")
    private Long totalDuration;
    @JsonProperty("call_type")
    private Integer callType;
    private Caller caller;
    private Callee callee;

    public Long getCallTime() {
        return callTime;
    }

    public void setCallTime(Long callTime) {
        this.callTime = callTime;
    }

    public Long getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Long totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getCallType() {
        return callType;
    }

    public void setCallType(Integer callType) {
        this.callType = callType;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public Callee getCallee() {
        return callee;
    }

    public void setCallee(Callee callee) {
        this.callee = callee;
    }
}
