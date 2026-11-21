package com.github.shuaidd.dto.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 待办提醒信息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class TodoReminder {

    /**
     * 提醒时间，Unix时间戳，单位秒
     */
    @JsonProperty("remind_time")
    private Integer remindTime;

    public Integer getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Integer remindTime) {
        this.remindTime = remindTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("remindTime", remindTime)
                .toString();
    }
}
