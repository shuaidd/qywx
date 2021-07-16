package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述 时长组件
 *
 * @author ddshuai
 * @date 2021-07-16 18:01
 **/
public class DateRangeFormControl implements ApplyFormControl {

    @JsonProperty("date_range")
    private AttendanceDateRange dateRange;

    public AttendanceDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(AttendanceDateRange dateRange) {
        this.dateRange = dateRange;
    }

    @Override
    public String formControlName() {
        return "DateRange";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateRangeFormControl.class.getSimpleName() + "[", "]")
                .add("dateRange=" + dateRange)
                .toString();
    }
}
