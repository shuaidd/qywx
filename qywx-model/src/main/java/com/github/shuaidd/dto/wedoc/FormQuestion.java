package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表问题列表
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormQuestion {

    /**
     * 问题数组，最多200个
     */
    @JsonProperty("items")
    private List<FormQuestionItem> items;

    public List<FormQuestionItem> getItems() {
        return items;
    }

    public void setItems(List<FormQuestionItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("items", items)
                .toString();
    }
}
