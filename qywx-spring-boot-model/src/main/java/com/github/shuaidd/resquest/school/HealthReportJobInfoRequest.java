package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取健康上报任务详情的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportJobInfoRequest {

    /**
     * 健康上报任务id
     */
    @JsonProperty("jobid")
    private String jobid;

    /**
     * 具体某天的任务详情日期，格式YYYY-MM-DD，仅支持获取最近14天数据
     */
    @JsonProperty("date")
    private String date;

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
}
