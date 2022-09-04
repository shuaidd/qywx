package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-01 20:33
 **/
public class QuotaRule {

    private List<QuotaRuleItem> list;

    @JsonProperty(value = "based_on_actual_work_time")
    private Boolean basedOnActualWorkTime;

    public List<QuotaRuleItem> getList() {
        return list;
    }

    public void setList(List<QuotaRuleItem> list) {
        this.list = list;
    }

    public Boolean getBasedOnActualWorkTime() {
        return basedOnActualWorkTime;
    }

    public void setBasedOnActualWorkTime(Boolean basedOnActualWorkTime) {
        this.basedOnActualWorkTime = basedOnActualWorkTime;
    }
}
