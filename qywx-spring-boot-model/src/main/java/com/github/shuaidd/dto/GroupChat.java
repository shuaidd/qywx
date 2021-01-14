package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 20:58
 **/
public class GroupChat {

    @JsonProperty("chat_id")
    private String chatId;

    /**
     * 客户群跟进状态。
     * 0 - 跟进人正常
     * 1 - 跟进人离职
     * 2 - 离职继承中
     * 3 - 离职继承完成
     */
    private Integer status;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
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
                .append("chatId", chatId)
                .append("status", status)
                .toString();
    }
}
