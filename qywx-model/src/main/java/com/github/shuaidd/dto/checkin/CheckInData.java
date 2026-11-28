package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 10:23
 **/
public class CheckInData {

    /**
     * 用户id
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 打卡规则名称
     */
    @JsonProperty("groupname")
    private String groupName;

    /**
     * 打卡类型。字符串，目前有：上班打卡，下班打卡，外出打卡
     */
    @JsonProperty("checkin_type")
    private String checkinType;

    /**
     * 异常类型，字符串，包括：时间异常，地点异常，未打卡，wifi异常，非常用设备。如果有多个异常，以分号间隔
     */
    @JsonProperty("exception_type")
    private String exceptionType;

    /**
     * 打卡时间。Unix时间戳
     */
    @JsonProperty("checkin_time")
    private Long checkinTime;

    /**
     * 打卡地点title
     */
    @JsonProperty("location_title")
    private String locationTitle;

    /**
     * 打卡地点详情
     */
    @JsonProperty("location_detail")
    private String locationDetail;

    /**
     * 打卡wifi名称
     */
    @JsonProperty("wifiname")
    private String wifiName;

    /**
     * 打卡备注
     */
    @JsonProperty("notes")
    private String notes;

    /**
     * 打卡的MAC地址/bssid
     */
    @JsonProperty("wifimac")
    private String wifiMac;

    /**
     * 打卡的附件media_id，可使用media/get获取附件
     */
    @JsonProperty("mediaids")
    private List<String> mediaIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCheckinType() {
        return checkinType;
    }

    public void setCheckinType(String checkinType) {
        this.checkinType = checkinType;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Long getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Long checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckInData.class.getSimpleName() + "[", "]")
                .add("userId='" + userId + "'")
                .add("groupName='" + groupName + "'")
                .add("checkinType='" + checkinType + "'")
                .add("exceptionType='" + exceptionType + "'")
                .add("checkinTime=" + checkinTime)
                .add("locationTitle='" + locationTitle + "'")
                .add("locationDetail='" + locationDetail + "'")
                .add("wifiName='" + wifiName + "'")
                .add("notes='" + notes + "'")
                .add("wifiMac='" + wifiMac + "'")
                .add("mediaIds=" + mediaIds)
                .toString();
    }
}
