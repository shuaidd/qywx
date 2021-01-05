package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.MomentTask;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:43
 **/
public class MomentTaskResponse extends AbstractBaseResponse {

    @JsonProperty("next_cursor")
    private String nextCursor;

    @JsonProperty("task_list")
    private List<MomentTask> momentTasks;

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public List<MomentTask> getMomentTasks() {
        return momentTasks;
    }

    public void setMomentTasks(List<MomentTask> momentTasks) {
        this.momentTasks = momentTasks;
    }
}
