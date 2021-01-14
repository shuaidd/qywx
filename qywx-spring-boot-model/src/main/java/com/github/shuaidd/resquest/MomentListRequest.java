package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:19
 **/
public class MomentListRequest extends CursorPageRequest {

    @JsonProperty("start_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    @JsonProperty("end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

    private String creator;

    @JsonProperty("filter_type")
    private Integer filterType;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
    }
}
