package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

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
}

