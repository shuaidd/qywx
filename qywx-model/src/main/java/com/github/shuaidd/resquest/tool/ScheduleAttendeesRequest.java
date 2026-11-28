package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 增删日程参与者请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ScheduleAttendeesRequest {

    /**
     * 日程ID，创建日程时返回的ID
     */
    @JsonProperty("schedule_id")
    private String scheduleId;

    /**
     * 日程参与者列表，累计最多支持1000人
     */
    private List<UserId> attendees;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public List<UserId> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<UserId> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("scheduleId", scheduleId)
                .append("attendees", attendees)
                .toString();
    }
}
