package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 描述
 * "external_userid": "wmqfasd1e19278asdasAAAA",
 * "chat_id":"wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA",
 * "userid": "zhangsan",
 * "status": 1,
 * "send_time": 1552536375
 *
 * @author ddshuai
 * date 2021-01-06 22:17
 **/
public class GroupMsgSendResult {
    @JsonProperty("external_userid")
    private String externalUserId;
    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("userid")
    private String userId;
    private Integer status;
    @JsonProperty("send_time")
    private Date sendTime;

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
