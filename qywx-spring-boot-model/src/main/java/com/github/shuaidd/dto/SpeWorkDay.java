package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-11 12:34
 **/
public class SpeWorkDay {

    private Long timestamp;
    private String notes;
    @JsonProperty("checkintime")
    private List<CheckInTime> checkInTimes;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<CheckInTime> getCheckInTimes() {
        return checkInTimes;
    }

    public void setCheckInTimes(List<CheckInTime> checkInTimes) {
        this.checkInTimes = checkInTimes;
    }
}
