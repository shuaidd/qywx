package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:42
 **/
public class MomentTask {
    @JsonProperty("userid")
    private String userId;

    /**
     * 成员发表状态。0:未发表 1：已发表
     */
    @JsonProperty("publish_status")
    private Integer publishStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }
}
