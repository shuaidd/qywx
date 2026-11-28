package com.github.shuaidd.dto.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 12:57
 **/
public class GroupChatRange {

    /**
     * 客户群列表
     */
    @JsonProperty(value = "chat_id_list")
    private List<String> chatIds;

    public List<String> getChatIds() {
        return chatIds;
    }

    public void setChatIds(List<String> chatIds) {
        this.chatIds = chatIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GroupChatRange.class.getSimpleName() + "[", "]")
                .add("chatIds=" + chatIds)
                .toString();
    }
}
