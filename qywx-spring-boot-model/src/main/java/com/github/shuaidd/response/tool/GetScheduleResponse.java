package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.ScheduleData;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:02
 **/
public class GetScheduleResponse extends AbstractBaseResponse {
    @JsonProperty("schedule_list")
    private List<ScheduleData> scheduleList;

    public List<ScheduleData> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleData> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
