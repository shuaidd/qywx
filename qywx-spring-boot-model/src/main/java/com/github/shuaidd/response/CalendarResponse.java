package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-07 21:37
 **/
public class CalendarResponse extends AbstractBaseResponse {
    @JsonProperty("cal_id")
    private String calId;

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }
}
