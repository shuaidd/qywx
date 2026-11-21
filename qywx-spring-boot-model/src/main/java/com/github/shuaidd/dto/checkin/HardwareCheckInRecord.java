package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 考勤设备产生的原始打卡记录
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class HardwareCheckInRecord {

    /**
     * 打卡成员的企业微信userid
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 打卡时间；Unix时间戳
     */
    @JsonProperty("checkin_time")
    private Long checkinTime;

    /**
     * 打卡设备序列号
     */
    @JsonProperty("device_sn")
    private String deviceSn;

    /**
     * 打卡设备名称
     */
    @JsonProperty("device_name")
    private String deviceName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Long checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("checkinTime", checkinTime)
                .append("deviceSn", deviceSn)
                .append("deviceName", deviceName)
                .toString();
    }
}
