package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.OffsetPageRequest;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:09
 **/
public class GetScheduleByCalendarRequest extends OffsetPageRequest {
    @JsonProperty("cal_id")
    private String calId;

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }
}
