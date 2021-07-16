package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 14:16
 **/
public class OverWorkInfo {

    @JsonProperty("workday_over_sec")
    private Integer workdayOverSec;

    @JsonProperty("holidays_over_sec")
    private Integer holidaysOverSec;

    @JsonProperty("restdays_over_sec")
    private Integer restDaysOverSec;

    public Integer getWorkdayOverSec() {
        return workdayOverSec;
    }

    public void setWorkdayOverSec(Integer workdayOverSec) {
        this.workdayOverSec = workdayOverSec;
    }

    public Integer getHolidaysOverSec() {
        return holidaysOverSec;
    }

    public void setHolidaysOverSec(Integer holidaysOverSec) {
        this.holidaysOverSec = holidaysOverSec;
    }

    public Integer getRestDaysOverSec() {
        return restDaysOverSec;
    }

    public void setRestDaysOverSec(Integer restDaysOverSec) {
        this.restDaysOverSec = restDaysOverSec;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OverWorkInfo.class.getSimpleName() + "[", "]")
                .add("workdayOverSec=" + workdayOverSec)
                .add("holidaysOverSec=" + holidaysOverSec)
                .add("restDaysOverSec=" + restDaysOverSec)
                .toString();
    }
}
