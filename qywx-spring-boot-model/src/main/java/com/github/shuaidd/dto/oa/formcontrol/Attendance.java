package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:43
 **/
public class Attendance {

    private Integer type;

    @JsonProperty("date_range")
    private AttendanceDateRange dateRange;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public AttendanceDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(AttendanceDateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Attendance.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("dateRange=" + dateRange)
                .toString();
    }
}
