package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 20:32
 **/
public class AsyncJobResponse extends AbstractBaseResponse {

    @JsonProperty("jobid")
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
