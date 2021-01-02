package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:42
 **/
public class DialRecordRequest {
    /**
     * 查询的起始时间戳
     */
    @JsonProperty("starttime")
    private Long startTime;

    /**
     * 查询的结束时间戳
     */
    @JsonProperty("endtime")
    private Long endTime;

    private Integer offset;
    private Integer limit;

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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DialRecordRequest.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("offset=" + offset)
                .add("limit=" + limit)
                .toString();
    }
}
