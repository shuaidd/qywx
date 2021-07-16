package com.github.shuaidd.dto.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-16 18:36
 **/
public class TemplateAttendance {
    private Integer type;

    @JsonProperty("date_range")
    private TemplateAttendanceDateRange dateRange;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public TemplateAttendanceDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(TemplateAttendanceDateRange dateRange) {
        this.dateRange = dateRange;
    }
}
