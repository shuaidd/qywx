package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.RequestFilter;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 09:24
 **/
public class JournalRecordRequest {

    @JsonProperty("starttime")
    private Long startTime;

    @JsonProperty("endtime")
    private Long endTime;

    private Integer cursor;

    private Integer limit = 100;

    private List<RequestFilter> filters;

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

    public Integer getCursor() {
        return cursor;
    }

    public void setCursor(Integer cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<RequestFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<RequestFilter> filters) {
        this.filters = filters;
    }
}
