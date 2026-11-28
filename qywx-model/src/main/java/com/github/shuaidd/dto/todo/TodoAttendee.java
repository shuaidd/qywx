package com.github.shuaidd.dto.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 待办参与人信息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TodoAttendee {

    /**
     * 参与待办的企业成员userid
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 该成员的待办状态：0-已完成，1-进行中
     */
    @JsonProperty("status")
    private Integer status;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("status", status)
                .toString();
    }
}
