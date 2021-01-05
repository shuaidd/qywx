package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.GroupChat;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:00
 **/
public class GroupChatListResponse extends AbstractBaseResponse {
    @JsonProperty("group_chat_list")
    private List<GroupChat> groupChats;

    @JsonProperty("next_cursor")
    private String nextCursor;

    public List<GroupChat> getGroupChats() {
        return groupChats;
    }

    public void setGroupChats(List<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
