package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.ScheduleData;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 13:51
 **/
public class ScheduleRequest {
    private ScheduleData schedule;
    @JsonProperty("agentid")
    private String agentId;

    public ScheduleData getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleData schedule) {
        this.schedule = schedule;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
