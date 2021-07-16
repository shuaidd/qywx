package com.github.shuaidd.dto.template;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:38
 **/
public class TemplateContent {

    private List<TemplateControl> controls;

    public List<TemplateControl> getControls() {
        return controls;
    }

    public void setControls(List<TemplateControl> controls) {
        this.controls = controls;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateContent.class.getSimpleName() + "[", "]")
                .add("controls=" + controls)
                .toString();
    }
}
