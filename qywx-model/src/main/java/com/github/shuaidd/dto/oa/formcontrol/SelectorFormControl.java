package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述 单选/多选控件
 *
 * @author ddshuai
 * date 2021-07-16 14:25
 **/
public class SelectorFormControl implements ApplyFormControl {

    private SelectorData selector;

    public SelectorData getSelector() {
        return selector;
    }

    public void setSelector(SelectorData selector) {
        this.selector = selector;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SelectorFormControl.class.getSimpleName() + "[", "]")
                .add("selector=" + selector)
                .toString();
    }

    @Override
    public String formControlName() {
        return "Selector";
    }
}
