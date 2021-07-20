package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.GroupMsgTask;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:12
 **/
public class GroupMsgTaskResponse extends AbstractBaseResponse {

    @JsonProperty("task_list")
    private List<GroupMsgTask> groupMsgTasks;

    public List<GroupMsgTask> getGroupMsgTasks() {
        return groupMsgTasks;
    }

    public void setGroupMsgTasks(List<GroupMsgTask> groupMsgTasks) {
        this.groupMsgTasks = groupMsgTasks;
    }
}
