package com.github.shuaidd.dto.oa.formcontrol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述 数字控件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
public class NumberFormControl implements ApplyFormControl {

    @JsonProperty("new_number")
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NumberFormControl.class.getSimpleName() + "[", "]")
                .add("number='" + number + "'")
                .toString();
    }

    @Override
    public String formControlName() {
        return "Number";
    }
}
