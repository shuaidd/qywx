package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.CalendarData;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-07 21:42
 **/
public class GetCalendarResponse extends AbstractBaseResponse {
    @JsonProperty("calendar_list")
    private List<CalendarData> calendarList;

    public List<CalendarData> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<CalendarData> calendarList) {
        this.calendarList = calendarList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calendarList", calendarList)
                .toString();
    }
}
