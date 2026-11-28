package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取待办详情请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TodoDetailRequest {

    /**
     * 待办id，企业微信生成的待办唯一标识
     */
    @JsonProperty("todo_id")
    private String todoId;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("todoId", todoId)
                .toString();
    }
}
