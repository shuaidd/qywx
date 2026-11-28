package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 提交智能文档导出任务响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ExportSmartDocResponse extends AbstractBaseResponse {

    /**
     * 异步导出任务 ID，用于轮询导出结果
     */
    @JsonProperty("task_id")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .toString();
    }
}