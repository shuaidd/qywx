package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 文本/多行文本控件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
public class TextFormControl implements ApplyFormControl {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TextFormControl.class.getSimpleName() + "[", "]")
                .add("text='" + text + "'")
                .toString();
    }

    @Override
    public String formControlName() {
        return "Text";
    }
}
