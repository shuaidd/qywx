package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户群 opengid 转换响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class OpenGidToChatIdResponse extends AbstractBaseResponse {

    /**
     * 转换得到的客户群 ID；可用于调用客户群详情等接口
     */
    @JsonProperty("chat_id")
    private String chatId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("chatId", chatId)
                .toString();
    }
}
