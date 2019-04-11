package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-11 13:17
 **/
public class ApprovalDataRequest {
    /**
     * 获取审批记录的开始时间。Unix时间戳
     */
    @JsonProperty("starttime")
    private Long startTime;

    /**
     * 获取审批记录的结束时间。Unix时间戳
     */
    @JsonProperty("endtime")
    private Long endTime;

    @JsonProperty("next_spnum")
    private String nextSpNum;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getNextSpNum() {
        return nextSpNum;
    }

    public void setNextSpNum(String nextSpNum) {
        this.nextSpNum = nextSpNum;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalDataRequest.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("nextSpNum='" + nextSpNum + "'")
                .toString();
    }
}
