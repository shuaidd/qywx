package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.GroupMsg;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:02
 **/
public class GroupMsgResponse extends AbstractBaseResponse {
    @JsonProperty("next_cursor")
    private String nextCursor;
    @JsonProperty("group_msg_list")
    private List<GroupMsg> groupMsgList;

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public List<GroupMsg> getGroupMsgList() {
        return groupMsgList;
    }

    public void setGroupMsgList(List<GroupMsg> groupMsgList) {
        this.groupMsgList = groupMsgList;
    }
}
