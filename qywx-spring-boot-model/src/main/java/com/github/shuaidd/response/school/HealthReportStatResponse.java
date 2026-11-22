package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 健康上报使用统计响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportStatResponse extends AbstractBaseResponse {

    /**
     * 应用使用次数
     */
    @JsonProperty("pv")
    private Integer pv;

    /**
     * 应用使用成员数
     */
    @JsonProperty("uv")
    private Integer uv;

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }
}
