package com.github.shuaidd.response.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.todo.TodoAttendee;
import com.github.shuaidd.dto.todo.TodoReminder;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 待办详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TodoDetailResponse extends AbstractBaseResponse {

    /**
     * 待办内容描述
     */
    @JsonProperty("content")
    private String content;

    /**
     * 创建待办的企业成员userid
     */
    @JsonProperty("creator")
    private String creator;

    /**
     * 待办整体状态：0-已完成，1-进行中
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 待办创建时间，Unix时间戳，单位秒
     */
    @JsonProperty("create_time")
    private Integer createTime;

    /**
     * 待办参与人列表
     */
    @JsonProperty("attendees")
    private List<TodoAttendee> attendees;

    /**
     * 待办截止时间，Unix时间戳，单位秒
     */
    @JsonProperty("end_time")
    private Integer endTime;

    /**
     * 待办提醒配置列表
     */
    @JsonProperty("reminders")
    private List<TodoReminder> reminders;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public List<TodoAttendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<TodoAttendee> attendees) {
        this.attendees = attendees;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public List<TodoReminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<TodoReminder> reminders) {
        this.reminders = reminders;
    }
}
