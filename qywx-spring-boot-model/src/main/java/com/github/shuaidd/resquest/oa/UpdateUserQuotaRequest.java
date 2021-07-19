package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 17:36
 **/
public class UpdateUserQuotaRequest {

    @JsonProperty("userid")
    private String userId;

    @JsonProperty("vacation_id")
    private Integer vacationId;

    @JsonProperty("leftduration")
    private Integer leftDuration;

    @JsonProperty("time_attr")
    private Integer timeAttr;

    private String remarks;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getVacationId() {
        return vacationId;
    }

    public void setVacationId(Integer vacationId) {
        this.vacationId = vacationId;
    }

    public Integer getLeftDuration() {
        return leftDuration;
    }

    public void setLeftDuration(Integer leftDuration) {
        this.leftDuration = leftDuration;
    }

    public Integer getTimeAttr() {
        return timeAttr;
    }

    public void setTimeAttr(Integer timeAttr) {
        this.timeAttr = timeAttr;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UpdateUserQuotaRequest.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("vacationId=" + vacationId)
                .add("leftDuration=" + leftDuration)
                .add("timeAttr=" + timeAttr)
                .add("remarks='" + remarks + "'")
                .toString();
    }
}
