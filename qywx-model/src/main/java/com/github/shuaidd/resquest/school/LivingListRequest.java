package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取指定老师的所有直播id的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingListRequest {

    /**
     * 企业成员的userid
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 上一次调用返回的next_cursor，第一次拉取可以不填
     */
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 每次拉取的数据量，默认值和最大值都为100
     */
    @JsonProperty("limit")
    private Integer limit;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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
