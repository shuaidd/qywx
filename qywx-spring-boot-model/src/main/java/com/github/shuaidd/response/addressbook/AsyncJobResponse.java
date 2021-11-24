package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
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

    @Override
    public String toString() {
        return new StringJoiner(", ", AsyncJobResponse.class.getSimpleName() + "[", "]")
                .add("jobId='" + jobId + "'")
                .toString();
    }
}
