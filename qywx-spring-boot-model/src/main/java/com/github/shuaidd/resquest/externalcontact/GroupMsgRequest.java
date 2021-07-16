package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;
import com.github.shuaidd.resquest.CursorPageRequest;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 21:57
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
