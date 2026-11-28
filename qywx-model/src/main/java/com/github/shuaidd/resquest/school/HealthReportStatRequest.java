package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取健康上报使用统计的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportStatRequest {

    /**
     * 具体某天的使用统计日期，格式YYYY-MM-DD，最长支持获取30天前数据
     */
    @JsonProperty("date")
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
