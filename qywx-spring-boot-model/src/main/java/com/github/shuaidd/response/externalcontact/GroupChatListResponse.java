package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.GroupChat;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-05 21:00
 **/
public class GroupChatListResponse extends AbstractBaseResponse {

    @JsonProperty("group_chat_list")
    private List<GroupChat> groupChats;

    public List<GroupChat> getGroupChats() {
        return groupChats;
    }

    public void setGroupChats(List<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupChats", groupChats)
                .toString();
    }
}
