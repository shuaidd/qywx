package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:00
 **/
public class GetScheduleRequest {
    @JsonProperty("schedule_id_list")
    private List<String> scheduleIdList;

    public List<String> getScheduleIdList() {
        return scheduleIdList;
    }

    public void setScheduleIdList(List<String> scheduleIdList) {
        this.scheduleIdList = scheduleIdList;
    }
}
