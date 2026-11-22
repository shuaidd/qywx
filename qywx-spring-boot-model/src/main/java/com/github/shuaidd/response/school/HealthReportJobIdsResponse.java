package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 健康上报任务id列表响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportJobIdsResponse extends AbstractBaseResponse {

    /**
     * 是否结束。0表示还有更多数据需要继续拉取，1表示已经拉取完所有数据
     */
    @JsonProperty("ending")
    private Integer ending;

    /**
     * 健康上报任务id列表
     */
    @JsonProperty("jobids")
    private List<String> jobids;

    public Integer getEnding() {
        return ending;
    }

    public void setEnding(Integer ending) {
        this.ending = ending;
    }

    public List<String> getJobids() {
        return jobids;
    }

    public void setJobids(List<String> jobids) {
        this.jobids = jobids;
    }
}
