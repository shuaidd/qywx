package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 17:11
 **/
public class VacationConfig {
    private Integer id;
    private String name;

    @JsonProperty("time_attr")
    private Integer timeAttr;

    @JsonProperty("duration_type")
    private Integer durationType;

    @JsonProperty("perday_duration")
    private Integer perDayDuration;

    @JsonProperty("quota_attr")
    private VacationQuotaAttr quotaAttr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimeAttr() {
        return timeAttr;
    }

    public void setTimeAttr(Integer timeAttr) {
        this.timeAttr = timeAttr;
    }

    public Integer getDurationType() {
        return durationType;
    }

    public void setDurationType(Integer durationType) {
        this.durationType = durationType;
    }

    public Integer getPerDayDuration() {
        return perDayDuration;
    }

    public void setPerDayDuration(Integer perDayDuration) {
        this.perDayDuration = perDayDuration;
    }

    public VacationQuotaAttr getQuotaAttr() {
        return quotaAttr;
    }

    public void setQuotaAttr(VacationQuotaAttr quotaAttr) {
        this.quotaAttr = quotaAttr;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", VacationConfig.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("timeAttr=" + timeAttr)
                .add("durationType=" + durationType)
                .add("perDayDuration=" + perDayDuration)
                .add("quotaAttr=" + quotaAttr)
                .toString();
    }
}
