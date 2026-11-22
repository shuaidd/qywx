package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报任务汇报对象
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportReportTo {

    /**
     * 汇报对象的企业通讯录userid列表
     */
    @JsonProperty("userids")
    private List<String> userids;

    public List<String> getUserids() {
        return userids;
    }

    public void setUserids(List<String> userids) {
        this.userids = userids;
    }
}
