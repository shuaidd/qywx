package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 14:42
 **/
public class GetMeetingForUserRequest extends CursorPageRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("begin_time")
    private Date beginTime;

    @JsonProperty("end_time")
    private Date endTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
