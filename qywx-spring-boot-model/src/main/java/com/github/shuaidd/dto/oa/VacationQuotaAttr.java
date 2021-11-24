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

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationQuotaAttr.class.getSimpleName() + "[", "]")
                .add("type=" + type)
                .add("autoResetTime=" + autoResetTime)
                .add("autoResetDuration=" + autoResetDuration)
                .toString();
    }
}
