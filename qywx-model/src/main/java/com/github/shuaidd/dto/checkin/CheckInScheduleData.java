package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 14:28
 **/
public class CheckInScheduleData {

    @JsonProperty("scheduleList")
    private List<CheckInScheduleDataDetail> scheduleDataDetails;

    public List<CheckInScheduleDataDetail> getScheduleDataDetails() {
        return scheduleDataDetails;
    }

    public void setScheduleDataDetails(List<CheckInScheduleDataDetail> scheduleDataDetails) {
        this.scheduleDataDetails = scheduleDataDetails;
    }
}
