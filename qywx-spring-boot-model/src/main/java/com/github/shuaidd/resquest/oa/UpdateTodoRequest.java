package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.todo.TodoAttendee;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 更新待办状态请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UpdateTodoRequest {

    /**
     * 待办id，企业微信生成的待办唯一标识
     */
    @JsonProperty("todo_id")
    private String todoId;

    /**
     * 待办状态：0-已完成，1-进行中；不填时保持原状态
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 需要更新状态的待办参与人列表，最多20个；不填时不更新参与人
     */
    @JsonProperty("attendees")
    private List<TodoAttendee> attendees;

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TodoAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<TodoAttendee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("todoId", todoId)
                .append("status", status)
                .append("attendees", attendees)
                .toString();
    }
}
