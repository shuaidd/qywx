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

    /**
     * 发放时长，单位为秒
     */
    @JsonProperty("assignduration")
    private Integer assignDuration;

    /**
     * 剩余时长，单位为秒
     */
    @JsonProperty("leftduration")
    private Integer leftDuration;

    /**
     * 使用时长，单位为秒
     */
    @JsonProperty("usedduration")
    private Integer usedDuration;

    /**
     * 假期名称
     */
    @JsonProperty("vacationname")
    private String vacationName;

    /**
     * 假期的实际发放时长，通常在设置了按照实际工作时间发放假期后进行计算
     */
    @JsonProperty(value = "real_assignduration")
    private Integer realAssignDuration;

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

    public Integer getRealAssignDuration() {
        return realAssignDuration;
    }

    public void setRealAssignDuration(Integer realAssignDuration) {
        this.realAssignDuration = realAssignDuration;
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
