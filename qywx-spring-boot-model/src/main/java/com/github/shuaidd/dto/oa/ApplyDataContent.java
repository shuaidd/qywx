package com.github.shuaidd.dto.oa;

import com.github.shuaidd.dto.oa.formcontrol.ApplyFormControl;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 13:40
 **/
public class ApplyDataContent {
    private String control;
    private String id;
    private ApplyFormControl value;

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApplyFormControl getValue() {
        return value;
    }

    public void setValue(ApplyFormControl value) {
        this.value = value;
    }
}
