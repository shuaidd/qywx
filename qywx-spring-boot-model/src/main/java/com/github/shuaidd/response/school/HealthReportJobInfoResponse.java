package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 健康上报任务详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportJobInfoResponse extends AbstractBaseResponse {

    /**
     * 健康上报任务详情
     */
    @JsonProperty("job_info")
    private HealthReportJobInfo jobInfo;

    public HealthReportJobInfo getJobInfo() {
        return jobInfo;
    }

    public void setJobInfo(HealthReportJobInfo jobInfo) {
        this.jobInfo = jobInfo;
    }
}
