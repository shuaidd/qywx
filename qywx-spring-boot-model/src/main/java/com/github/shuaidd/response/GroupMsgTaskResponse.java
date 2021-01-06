package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.GroupMsgTask;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-06 22:12
 **/
public class GroupMsgTaskResponse extends AbstractBaseResponse {
    @JsonProperty("next_cursor")
    private String nextCursor;
    @JsonProperty("task_list")
    private List<GroupMsgTask> groupMsgTasks;

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public List<GroupMsgTask> getGroupMsgTasks() {
        return groupMsgTasks;
    }

    public void setGroupMsgTasks(List<GroupMsgTask> groupMsgTasks) {
        this.groupMsgTasks = groupMsgTasks;
    }
}
