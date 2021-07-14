package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 16:41
 **/
public class TimeSection {

    @JsonProperty("time_id")
    private Integer timeId;

    @JsonProperty("work_sec")
    private Long workSec;

    @JsonProperty("off_work_sec")
    private Long offWorkSec;

    @JsonProperty("remind_work_sec")
    private Long remindWorkSec;

    @JsonProperty("remind_off_work_sec")
    private Long remindOffWorkSec;

    @JsonProperty("rest_begin_time")
    private Long restBeginTime;

    @JsonProperty("rest_end_time")
    private Long restEndTime;

    @JsonProperty("allow_rest")
    private Boolean allowRest;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Long getWorkSec() {
        return workSec;
    }

    public void setWorkSec(Long workSec) {
        this.workSec = workSec;
    }

    public Long getOffWorkSec() {
        return offWorkSec;
    }

    public void setOffWorkSec(Long offWorkSec) {
        this.offWorkSec = offWorkSec;
    }

    public Long getRemindWorkSec() {
        return remindWorkSec;
    }

    public void setRemindWorkSec(Long remindWorkSec) {
        this.remindWorkSec = remindWorkSec;
    }

    public Long getRemindOffWorkSec() {
        return remindOffWorkSec;
    }

    public void setRemindOffWorkSec(Long remindOffWorkSec) {
        this.remindOffWorkSec = remindOffWorkSec;
    }

    public Long getRestBeginTime() {
        return restBeginTime;
    }

    public void setRestBeginTime(Long restBeginTime) {
        this.restBeginTime = restBeginTime;
    }

    public Long getRestEndTime() {
        return restEndTime;
    }

    public void setRestEndTime(Long restEndTime) {
        this.restEndTime = restEndTime;
    }

    public Boolean getAllowRest() {
        return allowRest;
    }

    public void setAllowRest(Boolean allowRest) {
        this.allowRest = allowRest;
    }
}
