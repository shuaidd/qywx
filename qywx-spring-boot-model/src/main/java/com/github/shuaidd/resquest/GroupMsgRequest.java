package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;

import java.util.Date;

/**
 * 描述
 * "chat_type":"single",
 * "start_time":1605171726,
 * "end_time":1605172726,
 * "creator":"zhangshan",
 * "filter_type":1,
 * "limit":50,
 * "cursor":"CURSOR"
 *
 * @author ddshuai
 * @date 2021-01-06 21:57
 **/
public class GroupMsgRequest extends CursorPageRequest {
    @JsonProperty("chat_type")
    private String chatType;

    @JsonProperty("start_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    @JsonProperty("end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

    private String creator;
    @JsonProperty("filter_type")
    private Integer filterType;

    public String getChatType() {
        return chatType;
    }

    public void setChatType(String chatType) {
        this.chatType = chatType;
    }

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
