package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取成员多次收消息详情请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionChatKeyRequest {

    /**
     * 成员多次收消息事件回调返回的会话信息凭据；官方限制回调后 30 分钟内有效
     */
    @JsonProperty("chat_key")
    private String chatKey;

    public String getChatKey() {
        return chatKey;
    }

    public void setChatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("chatKey", chatKey)
                .toString();
    }
}
