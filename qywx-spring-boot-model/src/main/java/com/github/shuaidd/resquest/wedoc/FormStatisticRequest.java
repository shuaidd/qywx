package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 收集表统计信息查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormStatisticRequest {

    /**
     * 操作的收集表周期id
     */
    @JsonProperty("repeated_id")
    private String repeatedId;

    /**
     * 请求类型；1-仅统计，2-已提交列表，3-未提交列表
     */
    @JsonProperty("req_type")
    private Integer reqType;

    /**
     * 已提交列表筛选开始时间；请求类型为2时必填
     */
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 已提交列表筛选结束时间；请求类型为2时必填
     */
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 分页批次大小，最大10000
     */
    @JsonProperty("limit")
    private Long limit;

    /**
     * 分页游标，首次请求不传
     */
    @JsonProperty("cursor")
    private Long cursor;

    public String getRepeatedId() {
        return repeatedId;
    }

    public void setRepeatedId(String repeatedId) {
        this.repeatedId = repeatedId;
    }

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

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

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getCursor() {
        return cursor;
    }

    public void setCursor(Long cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("repeatedId", repeatedId)
                .append("reqType", reqType)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("limit", limit)
                .append("cursor", cursor)
                .toString();
    }
}
