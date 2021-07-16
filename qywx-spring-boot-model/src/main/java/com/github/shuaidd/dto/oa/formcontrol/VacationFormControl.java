package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 假勤组件-请假组件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
public class VacationFormControl implements ApplyFormControl {

    private VacationData vacation;

    public VacationData getVacation() {
        return vacation;
    }

    public void setVacation(VacationData vacation) {
        this.vacation = vacation;
    }

    @Override
    public String formControlName() {
        return "Vacation";
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationFormControl.class.getSimpleName() + "[", "]")
                .add("vacation=" + vacation)
                .toString();
    }
}
