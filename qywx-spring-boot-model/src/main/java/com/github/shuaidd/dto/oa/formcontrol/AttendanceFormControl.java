package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 假勤组件-出差/外出/加班组件
 *
 * @author ddshuai
 * @date 2021-07-16 17:48
 **/
public class AttendanceFormControl implements ApplyFormControl {

    private Attendance attendance;

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String formControlName() {
        return "Attendance";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AttendanceFormControl.class.getSimpleName() + "[", "]")
                .add("attendance=" + attendance)
                .toString();
    }
}
