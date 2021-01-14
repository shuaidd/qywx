package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 12:31
 **/
public class CheckInTime {

    @JsonProperty("work_sec")
    private Long workSec;

    @JsonProperty("off_work_sec")
    private Long offWorkSec;

    @JsonProperty("remind_work_sec")
    private Long remindWorkSec;

    @JsonProperty("remind_off_work_sec")
    private Long remindOffWorkSec;

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
}
