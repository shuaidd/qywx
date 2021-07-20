package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-07 21:41
 **/
public class GetCalendarRequest {
    @JsonProperty("cal_id_list")
    private List<String> calIdList;

    public List<String> getCalIdList() {
        return calIdList;
    }

    public void setCalIdList(List<String> calIdList) {
        this.calIdList = calIdList;
    }
}
