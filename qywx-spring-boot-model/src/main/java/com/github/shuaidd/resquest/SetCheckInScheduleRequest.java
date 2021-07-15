package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.SetCheckInScheduleItem;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 14:40
 **/
public class SetCheckInScheduleRequest {

    @JsonProperty("groupid")
    private Integer groupId;

    @JsonProperty("yearmonth")
    private String yearMonth;

    private List<SetCheckInScheduleItem> items;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public List<SetCheckInScheduleItem> getItems() {
        return items;
    }

    public void setItems(List<SetCheckInScheduleItem> items) {
        this.items = items;
    }
}
