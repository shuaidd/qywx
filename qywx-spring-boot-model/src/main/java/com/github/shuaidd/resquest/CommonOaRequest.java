package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-15 11:08
 **/
public class CommonOaRequest {

    /**
     * 获取打卡记录的开始时间。Unix时间戳
     */
    @JsonProperty("starttime")
    private Long startTime;

    /**
     * 获取打卡记录的结束时间。Unix时间戳
     */
    @JsonProperty("endtime")
    private Long endTime;

    /**
     * 需要获取打卡记录的用户列表
     */
    @JsonProperty("useridlist")
    private List<String> userIdList;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
