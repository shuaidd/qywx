package com.github.shuaidd.resquest.job;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobIdRequest {

    /**
     * jobId
     */
    @JsonProperty("jobid")
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}
