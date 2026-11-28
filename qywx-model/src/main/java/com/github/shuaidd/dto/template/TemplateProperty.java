package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 15:41
 **/
public class TemplateProperty {
    private String control;
    private String id;
    private List<TemplateText> title;
    private List<TemplateText> placeholder;
    private Integer require;
    @JsonProperty("un_print")
    private Integer unPrint;

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

    public List<TemplateText> getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(List<TemplateText> placeholder) {
        this.placeholder = placeholder;
    }

    public Integer getRequire() {
        return require;
    }

    public void setRequire(Integer require) {
        this.require = require;
    }

    public Integer getUnPrint() {
        return unPrint;
    }

    public void setUnPrint(Integer unPrint) {
        this.unPrint = unPrint;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateProperty.class.getSimpleName() + "[", "]")
                .add("control='" + control + "'")
                .add("id='" + id + "'")
                .add("title=" + title)
                .add("placeholder=" + placeholder)
                .add("require=" + require)
                .add("unPrint=" + unPrint)
                .toString();
    }
}
