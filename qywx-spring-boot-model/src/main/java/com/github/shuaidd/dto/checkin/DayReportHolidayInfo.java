package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 13:11
 **/
public class DayReportHolidayInfo {

    @JsonProperty("sp_number")
    private String spNumber;

    @JsonProperty("sp_description")
    private SpDescription spDescription;

    @JsonProperty("sp_title")
    private SpTitle spTitle;

    @Override
    public String toString() {
        return new StringJoiner(", ", DayReportHolidayInfo.class.getSimpleName() + "[", "]")
                .add("spNumber='" + spNumber + "'")
                .add("spDescription=" + spDescription)
                .add("spTitle=" + spTitle)
                .toString();
    }
}

