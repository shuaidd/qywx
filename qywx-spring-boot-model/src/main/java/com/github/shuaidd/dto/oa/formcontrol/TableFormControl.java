package com.github.shuaidd.dto.oa.formcontrol;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述 明细控件
 *
 * @author ddshuai
 * @date 2021-07-16 14:25
 **/
public class TableFormControl implements ApplyFormControl {

    private List<TableChildren> children;

    public List<TableChildren> getChildren() {
        return children;
    }

    public void setChildren(List<TableChildren> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableFormControl.class.getSimpleName() + "[", "]")
                .add("children=" + children)
                .toString();
    }

    @Override
    public String formControlName() {
        return "Table";
    }
}
