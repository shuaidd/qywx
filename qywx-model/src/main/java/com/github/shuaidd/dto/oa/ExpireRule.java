package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ddshuai
 * date 2022-09-01 20:40
 **/
public class ExpireRule {

    private Integer type;

    private Integer duration;

    @JsonProperty(value = "extern_duration_enable")
    private Boolean externDurationEnable;

    private ExpireRuleDate date;

    @JsonProperty(value = "extern_duration")
    private ExpireRuleDate externDuration;

    public static class ExpireRuleDate {
        private Integer month;
        private Integer day;

        public Integer getMonth() {
            return month;
        }

        public void setMonth(Integer month) {
            this.month = month;
        }

        public Integer getDay() {
            return day;
        }

        public void setDay(Integer day) {
            this.day = day;
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getExternDurationEnable() {
        return externDurationEnable;
    }

    public void setExternDurationEnable(Boolean externDurationEnable) {
        this.externDurationEnable = externDurationEnable;
    }

    public ExpireRuleDate getDate() {
        return date;
    }

    public void setDate(ExpireRuleDate date) {
        this.date = date;
    }

    public ExpireRuleDate getExternDuration() {
        return externDuration;
    }

    public void setExternDuration(ExpireRuleDate externDuration) {
        this.externDuration = externDuration;
    }
}
