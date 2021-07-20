package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-07 16:11
 **/
public class GroupChatStatistic {
    private String owner;

    @JsonProperty("stat_time")
    private Date statTime;

    private GroupChatStatisticData data;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public GroupChatStatisticData getData() {
        return data;
    }

    public void setData(GroupChatStatisticData data) {
        this.data = data;
    }

    public Date getStatTime() {
        return statTime;
    }

    public void setStatTime(Date statTime) {
        this.statTime = statTime;
    }
}
