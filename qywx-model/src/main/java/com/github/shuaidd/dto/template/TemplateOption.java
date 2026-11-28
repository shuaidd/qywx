package com.github.shuaidd.dto.template;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 15:47
 **/
public class TemplateOption {
    private String key;
    private List<TemplateText> value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<TemplateText> getValue() {
        return value;
    }

    public void setValue(List<TemplateText> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TemplateOption.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .add("value=" + value)
                .toString();
    }
}
