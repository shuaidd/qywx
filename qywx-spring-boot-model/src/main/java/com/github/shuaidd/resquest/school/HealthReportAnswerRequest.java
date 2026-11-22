package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取健康上报填写答案的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportAnswerRequest {

    /**
     * 健康上报任务id
     */
    @JsonProperty("jobid")
    private String jobid;

    /**
     * 具体某天的填写答案日期，格式YYYY-MM-DD，仅支持获取最近14天数据
     */
    @JsonProperty("date")
    private String date;

    /**
     * 数据偏移量，默认为0
     */
    @JsonProperty("offset")
    private Integer offset;

    /**
     * 拉取的数据量，最大值100
     */
    @JsonProperty("limit")
    private Integer limit;

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
