package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 16:17
 **/
public class OtWorkingDayRestInfo {
    private Integer type;

    @JsonProperty("fix_time_rule")
    private FixTimeRule fixTimeRule;

    @JsonProperty("cal_ottime_rule")
    private CalOtTimeRule calOtTimeRule;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public FixTimeRule getFixTimeRule() {
        return fixTimeRule;
    }

    public void setFixTimeRule(FixTimeRule fixTimeRule) {
        this.fixTimeRule = fixTimeRule;
    }

    public CalOtTimeRule getCalOtTimeRule() {
        return calOtTimeRule;
    }

    public void setCalOtTimeRule(CalOtTimeRule calOtTimeRule) {
        this.calOtTimeRule = calOtTimeRule;
    }
}
