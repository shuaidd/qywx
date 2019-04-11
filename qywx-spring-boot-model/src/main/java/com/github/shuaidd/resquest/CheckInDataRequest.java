package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-11 10:18
 **/
public class CheckInDataRequest {

    /**
     * 打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
     */
    @JsonProperty("opencheckindatatype")
    private Integer openCheckInDataType;

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

    public Integer getOpenCheckInDataType() {
        return openCheckInDataType;
    }

    public void setOpenCheckInDataType(Integer openCheckInDataType) {
        this.openCheckInDataType = openCheckInDataType;
    }

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
