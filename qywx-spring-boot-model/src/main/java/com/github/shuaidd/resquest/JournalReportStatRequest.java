package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 10:25
 **/
public class JournalReportStatRequest {

    @JsonProperty("starttime")
    private Long startTime;

    @JsonProperty("endtime")
    private Long endTime;

    @JsonProperty("template_id")
    private String templateId;

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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportStatRequest.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .add("templateId='" + templateId + "'")
                .toString();
    }
}
