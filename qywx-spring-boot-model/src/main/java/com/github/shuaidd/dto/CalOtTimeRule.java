package com.github.shuaidd.dto;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 16:20
 **/
public class CalOtTimeRule {
    private List<CalOtTimeRuleItem> items;

    public List<CalOtTimeRuleItem> getItems() {
        return items;
    }

    public void setItems(List<CalOtTimeRuleItem> items) {
        this.items = items;
    }
}
