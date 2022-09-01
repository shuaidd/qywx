package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;
import com.github.shuaidd.resquest.OffsetPageRequest;

import java.util.Date;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 13:42
 **/
public class DialRecordRequest extends OffsetPageRequest {
    /**
     * 查询的起始时间戳
     */
    @JsonProperty("starttime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    /**
     * 查询的结束时间戳
     */
    @JsonProperty("endtime")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", DialRecordRequest.class.getSimpleName() + "[", "]")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}
