package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:42
 **/
public class VacationData {

    private SelectorData selector;

    private Attendance attendance;

    public SelectorData getSelector() {
        return selector;
    }

    public void setSelector(SelectorData selector) {
        this.selector = selector;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationData.class.getSimpleName() + "[", "]")
                .add("selector=" + selector)
                .add("attendance=" + attendance)
                .toString();
    }
}
