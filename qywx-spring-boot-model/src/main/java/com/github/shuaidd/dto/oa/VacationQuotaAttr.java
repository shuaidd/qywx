package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 17:14
 **/
public class VacationQuotaAttr {

    private Integer type;

    @JsonProperty("autoreset_time")
    private Integer autoResetTime;

    @JsonProperty("autoreset_duration")
    private Integer autoResetDuration;

    @JsonProperty(value = "quota_rule_type")
    private Integer quotaRuleType;

    @JsonProperty(value = "quota_rules")
    private QuotaRule quotaRule;

    @JsonProperty(value = "at_entry_date")
    private Boolean atEntryDate;

    @JsonProperty(value = "auto_reset_month_day")
    private Integer autoResetMonthDay;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAutoResetTime() {
        return autoResetTime;
    }

    public void setAutoResetTime(Integer autoResetTime) {
        this.autoResetTime = autoResetTime;
    }

    public Integer getAutoResetDuration() {
        return autoResetDuration;
    }

    public void setAutoResetDuration(Integer autoResetDuration) {
        this.autoResetDuration = autoResetDuration;
    }

    public Integer getQuotaRuleType() {
        return quotaRuleType;
    }

    public void setQuotaRuleType(Integer quotaRuleType) {
        this.quotaRuleType = quotaRuleType;
    }

    public QuotaRule getQuotaRule() {
        return quotaRule;
    }

    public void setQuotaRule(QuotaRule quotaRule) {
        this.quotaRule = quotaRule;
    }

    public Boolean getAtEntryDate() {
        return atEntryDate;
    }

    public void setAtEntryDate(Boolean atEntryDate) {
        this.atEntryDate = atEntryDate;
    }

    public Integer getAutoResetMonthDay() {
        return autoResetMonthDay;
    }

    public void setAutoResetMonthDay(Integer autoResetMonthDay) {
        this.autoResetMonthDay = autoResetMonthDay;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationQuotaAttr.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("autoResetTime=" + autoResetTime)
                .add("autoResetDuration=" + autoResetDuration)
                .add("quotaRuleType=" + quotaRuleType)
                .add("quotaRule=" + quotaRule)
                .add("atEntryDate=" + atEntryDate)
                .add("autoResetMonthDay=" + autoResetMonthDay)
                .toString();
    }
}
