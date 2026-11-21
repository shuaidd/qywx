package com.github.shuaidd.response.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询智能文档导出任务结果响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GetSmartDocExportResultResponse extends AbstractBaseResponse {

    /**
     * 导出任务是否已完成
     */
    @JsonProperty("task_done")
    private Boolean taskDone;

    /**
     * 导出任务完成时返回的 Markdown 内容
     */
    @JsonProperty("content")
    private String content;

    public Boolean getTaskDone() {
        return taskDone;
    }

    public void setTaskDone(Boolean taskDone) {
        this.taskDone = taskDone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskDone", taskDone)
                .append("content", content)
                .toString();
    }
}