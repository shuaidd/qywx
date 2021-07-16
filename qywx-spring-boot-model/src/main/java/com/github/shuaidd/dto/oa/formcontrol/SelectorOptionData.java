package com.github.shuaidd.dto.oa.formcontrol;

import com.github.shuaidd.dto.template.TemplateText;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 17:17
 **/
public class SelectorOptionData {
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
        return new StringJoiner(", ", SelectorOptionData.class.getSimpleName() + "[", "]")
                .add("key='" + key + "'")
                .toString();
    }
}
