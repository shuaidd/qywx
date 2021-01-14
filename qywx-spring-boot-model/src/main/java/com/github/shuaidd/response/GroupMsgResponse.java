package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.GroupMsg;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:02
 **/
public class GroupMsgResponse extends AbstractBaseResponse {

    @JsonProperty("group_msg_list")
    private List<GroupMsg> groupMsgList;

    public List<GroupMsg> getGroupMsgList() {
        return groupMsgList;
    }

    public void setGroupMsgList(List<GroupMsg> groupMsgList) {
        this.groupMsgList = groupMsgList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupMsgList", groupMsgList)
                .toString();
    }
}
