package com.github.shuaidd.dto.oa.formcontrol;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 17:16
 **/
public class SelectorData {

    private String type;

    private List<SelectorOptionData> options;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SelectorOptionData> getOptions() {
        return options;
    }

    public void setOptions(List<SelectorOptionData> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SelectorData.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("options=" + options)
                .toString();
    }
}
