package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表答案明细集合
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswerReply {

    /**
     * 每个问题的答案列表
     */
    @JsonProperty("items")
    private List<FormAnswerDetail> items;

    public List<FormAnswerDetail> getItems() {
        return items;
    }

    public void setItems(List<FormAnswerDetail> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("items", items)
                .toString();
    }
}
