package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 15:44
 **/
public class TemplateSelector {

    private String type;

    @JsonProperty("exp_type")
    private Integer expType;

    private List<TemplateOption> options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getExpType() {
        return expType;
    }

    public void setExpType(Integer expType) {
        this.expType = expType;
    }

    public List<TemplateOption> getOptions() {
        return options;
    }

    public void setOptions(List<TemplateOption> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateSelector.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("expType=" + expType)
                .add("options=" + options)
                .toString();
    }
}
