package com.github.shuaidd.resquest.exmail;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取应用收件箱邮件列表请求。
 */
public class GetMailListRequest {

    /**
     * 开始时间，Unix 时间戳
     */
    @JsonProperty("begin_time")
    private Integer beginTime;

    /**
     * 结束时间，Unix 时间戳
     */
    @JsonProperty("end_time")
    private Integer endTime;

    /**
     * 分页游标；第一次拉取不填，后续传上一次返回的 next_cursor
     */
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 单页数据量，默认100，最大1000
     */
    @JsonProperty("limit")
    private Integer limit;

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
