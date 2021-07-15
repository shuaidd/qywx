package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述 加班信息
 *
 * @author ddshuai
 * date 2021-07-14 16:03
 **/
public class OverTimeInfo {

    /**
     * 加班类型 0：以加班申请核算打卡记录（根据打卡记录和加班申请核算）,1：以打卡时间为准（根据打卡时间计算），2: 以加班申请审批为准（只根据加班申请计算）
     */
    private Integer type;

    @JsonProperty("allow_ot_workingday")
    private Boolean allowOtWorkingDay;

    @JsonProperty("allow_ot_nonworkingday")
    private Boolean allowOtNonWorkingDay;

    @JsonProperty("otcheckinfo")
    private OtCheckInfo otCheckInfo;

    private Long uptime;

    @JsonProperty("otapplyinfo")
    private OverTimeApplyInfo overTimeApplyInfo;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getAllowOtWorkingDay() {
        return allowOtWorkingDay;
    }

    public void setAllowOtWorkingDay(Boolean allowOtWorkingDay) {
        this.allowOtWorkingDay = allowOtWorkingDay;
    }

    public Boolean getAllowOtNonWorkingDay() {
        return allowOtNonWorkingDay;
    }

    public void setAllowOtNonWorkingDay(Boolean allowOtNonWorkingDay) {
        this.allowOtNonWorkingDay = allowOtNonWorkingDay;
    }

    public OtCheckInfo getOtCheckInfo() {
        return otCheckInfo;
    }

    public void setOtCheckInfo(OtCheckInfo otCheckInfo) {
        this.otCheckInfo = otCheckInfo;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

    public OverTimeApplyInfo getOverTimeApplyInfo() {
        return overTimeApplyInfo;
    }

    public void setOverTimeApplyInfo(OverTimeApplyInfo overTimeApplyInfo) {
        this.overTimeApplyInfo = overTimeApplyInfo;
    }
}
