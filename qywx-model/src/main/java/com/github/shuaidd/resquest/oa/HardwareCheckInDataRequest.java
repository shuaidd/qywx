package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获取考勤设备打卡数据请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class HardwareCheckInDataRequest {

    /**
     * 过滤类型；1-按打卡时间过滤，2-按设备上传记录时间过滤，不传时默认为1
     */
    @JsonProperty("filter_type")
    private Integer filterType;

    /**
     * 记录开始时间；Unix时间戳，含义随filterType变化
     */
    @JsonProperty("starttime")
    private Long startTime;

    /**
     * 记录结束时间；Unix时间戳，含义随filterType变化
     */
    @JsonProperty("endtime")
    private Long endTime;

    /**
     * 需要获取打卡记录的用户列表；官方限制最多100人
     */
    @JsonProperty("useridlist")
    private List<String> userIdList;

    public Integer getFilterType() {
        return filterType;
    }

    public void setFilterType(Integer filterType) {
        this.filterType = filterType;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("filterType", filterType)
                .append("startTime", startTime)
                .append("endTime", endTime)
                .append("userIdList", userIdList)
                .toString();
    }
}
