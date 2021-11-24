package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 17:26
 **/
public class UserVacationQuota {

    private Integer id;

    @JsonProperty("assignduration")
    private Integer assignDuration;

    @JsonProperty("leftduration")
    private Integer leftDuration;

    @JsonProperty("usedduration")
    private Integer usedDuration;

    @JsonProperty("vacationname")
    private String vacationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAssignDuration() {
        return assignDuration;
    }

    public void setAssignDuration(Integer assignDuration) {
        this.assignDuration = assignDuration;
    }

    public Integer getLeftDuration() {
        return leftDuration;
    }

    public void setLeftDuration(Integer leftDuration) {
        this.leftDuration = leftDuration;
    }

    public Integer getUsedDuration() {
        return usedDuration;
    }

    public void setUsedDuration(Integer usedDuration) {
        this.usedDuration = usedDuration;
    }

    public String getVacationName() {
        return vacationName;
    }

    public void setVacationName(String vacationName) {
        this.vacationName = vacationName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserVacationQuota.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("assignDuration=" + assignDuration)
                .add("leftDuration=" + leftDuration)
                .add("usedDuration=" + usedDuration)
                .add("vacationName='" + vacationName + "'")
                .toString();
    }
}
