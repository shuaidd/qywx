package com.github.shuaidd.dto.oa.formcontrol;

import com.github.shuaidd.dto.template.TemplateText;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:31
 **/
public class TableChildrenDetail {
    private String control;
    private String id;
    private List<TemplateText> title;
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

    public List<TemplateText> getTitle() {
        return title;
    }

    public void setTitle(List<TemplateText> title) {
        this.title = title;
    }

    public ApplyFormControl getValue() {
        return value;
    }

    public void setValue(ApplyFormControl value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TableChildrenDetail.class.getSimpleName() + "[", "]")
                .add("control='" + control + "'")
                .add("id='" + id + "'")
                .add("title=" + title)
                .add("value=" + value)
                .toString();
    }
}
