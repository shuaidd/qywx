package com.github.shuaidd.dto.oa;

import com.github.shuaidd.dto.oa.formcontrol.ApplyFormControl;
import com.github.shuaidd.dto.template.TemplateText;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 13:40
 **/
public class ApplyDataContent {
    private String control;
    private String id;
    private ApplyFormControl value;

    private List<TemplateText> title;

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

    public List<TemplateText> getTitle() {
        return title;
    }

    public void setTitle(List<TemplateText> title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApplyDataContent.class.getSimpleName() + "[", "]")
                .add("control='" + control + "'")
                .add("id='" + id + "'")
                .add("value=" + value)
                .add("title=" + title)
                .toString();
    }
}
