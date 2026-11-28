package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 停止发表企业朋友圈请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentPublishCancelRequest {

    /**
     * 要停止的朋友圈 ID；来自发表任务结果中的 moment_id
     */
    @JsonProperty("moment_id")
    private String momentId;

    public String getMomentId() {
        return momentId;
    }

    public void setMomentId(String momentId) {
        this.momentId = momentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("momentId", momentId)
                .toString();
    }
}
