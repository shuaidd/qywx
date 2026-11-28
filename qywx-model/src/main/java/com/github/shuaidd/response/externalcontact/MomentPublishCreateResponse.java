package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建企业朋友圈发表任务响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishCreateResponse extends AbstractBaseResponse {

    /**
     * 异步任务 ID；最长 64 字节，24 小时内有效
     */
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
        return new ToStringBuilder(this)
                .append("jobId", jobId)
                .toString();
    }
}