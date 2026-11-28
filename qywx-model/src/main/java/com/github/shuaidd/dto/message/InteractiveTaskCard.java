package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 13:19
 **/
public class InteractiveTaskCard {
    private String title;
    private String description;
    private String url;
    @JsonProperty("task_id")
    private String taskId;
    List<TaskCardBtn> btn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<TaskCardBtn> getBtn() {
        return btn;
    }

    public void setBtn(List<TaskCardBtn> btn) {
        this.btn = btn;
    }
}
