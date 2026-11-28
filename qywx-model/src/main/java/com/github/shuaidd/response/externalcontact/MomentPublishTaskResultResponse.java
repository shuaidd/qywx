package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 企业朋友圈发表任务创建结果响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishTaskResultResponse extends AbstractBaseResponse {

    /**
     * 任务状态；1-开始创建，2-正在创建，3-创建完成
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 操作类型；此处固定为 add_moment_task
     */
    @JsonProperty("type")
    private String type;

    /**
     * 任务详细处理结果；任务完成后有效
     */
    @JsonProperty("result")
    private MomentPublishTaskResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MomentPublishTaskResult getResult() {
        return result;
    }

    public void setResult(MomentPublishTaskResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("status", status)
                .append("type", type)
                .append("result", result)
                .toString();
    }
}