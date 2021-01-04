package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-04 22:36
 **/
public class FailedChatData extends AbstractBaseResponse {
    @JsonProperty("chat_id")
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
