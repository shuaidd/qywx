package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentTask;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-05 21:43
 **/
public class MomentTaskResponse extends AbstractBaseResponse {

    @JsonProperty("task_list")
    private List<MomentTask> momentTasks;

    public List<MomentTask> getMomentTasks() {
        return momentTasks;
    }

    public void setMomentTasks(List<MomentTask> momentTasks) {
        this.momentTasks = momentTasks;
    }
}
