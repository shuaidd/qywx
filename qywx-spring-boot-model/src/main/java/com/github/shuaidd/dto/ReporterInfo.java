package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 15:58
 **/
public class ReporterInfo {

    @JsonProperty("updatetime")
    private Long updateTime;

    private List<ReporterUser> reporters;

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<ReporterUser> getReporters() {
        return reporters;
    }

    public void setReporters(List<ReporterUser> reporters) {
        this.reporters = reporters;
    }
}
