package com.github.shuaidd.resquest.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询智能文档导出任务结果请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocExportResultRequest {

    /**
     * 异步导出任务 ID
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