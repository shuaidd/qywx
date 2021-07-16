package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 日期/日期+时间控件
 *
 * @author ddshuai
 * @date 2021-07-16 14:25
 **/
public class DateFormControl implements ApplyFormControl {

    private DateFormControlData date;

    public DateFormControlData getDate() {
        return date;
    }

    public void setDate(DateFormControlData date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DateFormControl.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .toString();
    }

    @Override
    public String formControlName() {
        return "Date";
    }
}
