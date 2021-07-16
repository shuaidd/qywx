package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 18:26
 **/
public class TemplateVacation {

    @JsonProperty("item")
    private List<TemplateVacationItem> items;

    public List<TemplateVacationItem> getItems() {
        return items;
    }

    public void setItems(List<TemplateVacationItem> items) {
        this.items = items;
    }
}
