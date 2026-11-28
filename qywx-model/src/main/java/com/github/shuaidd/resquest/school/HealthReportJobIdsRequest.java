package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取健康上报任务id列表的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportJobIdsRequest {

    /**
     * 分页偏移量，默认为0
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * 分页请求的数据量，默认为100，取值范围1~100
     */
    @JsonProperty("limit")
    private Integer limit;

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
}
