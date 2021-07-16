package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:41
 **/
public class UserBehaviorRequest {
    @JsonProperty("userid")
    private List<String> userIds;
    @JsonProperty("partyid")
    private List<Integer> partyIds;

    @JsonProperty("start_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date startTime;

    @JsonProperty("end_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date endTime;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getPartyIds() {
        return partyIds;
    }

    public void setPartyIds(List<Integer> partyIds) {
        this.partyIds = partyIds;
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
}
