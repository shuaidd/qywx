package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.shuaidd.json.Date2LongSerializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * 外部系统写入企业微信的打卡记录
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CheckInRecord {

    /**
     * 打卡成员的企业微信userid
     */
    @JsonProperty("userid")
    private String userId;

    /**
     * 实际打卡时间；Unix时间戳
     */
    @JsonProperty("checkin_time")
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date checkinTime;

    /**
     * 打卡地点标题；官方限制1024个字符
     */
    @JsonProperty("location_title")
    private String locationTitle;

    /**
     * 打卡地点详情；官方限制1024个字符
     */
    @JsonProperty("location_detail")
    private String locationDetail;

    /**
     * 打卡备注；官方限制1024个字符
     */
    @JsonProperty("notes")
    private String notes;

    /**
     * 打卡附件media_id列表；使用media/upload上传，官方当前最多允许1个
     */
    @JsonProperty("mediaids")
    private List<String> mediaIds;

    /**
     * 打卡WiFi名称；官方限制1024个字符
     */
    @JsonProperty("wifiname")
    private String wifiName;

    /**
     * 打卡WiFi的MAC地址或BSSID；传入wifiName时必填
     */
    @JsonProperty("wifimac")
    private String wifiMac;

    /**
     * 打卡位置纬度；为实际纬度的1000000倍，采用GCJ-02坐标系，范围[-90000000, 90000000]
     */
    @JsonProperty("lat")
    private Long latitude;

    /**
     * 打卡位置经度；为实际经度的1000000倍，采用GCJ-02坐标系，范围[-180000000, 180000000]
     */
    @JsonProperty("lng")
    private Long longitude;

    /**
     * 打卡设备类型；1-门禁，2-考勤机（人脸识别、指纹识别），3-其他
     */
    @JsonProperty("device_type")
    private Integer deviceType;

    /**
     * 打卡设备品牌或详情；官方限制40个字符
     */
    @JsonProperty("device_detail")
    private String deviceDetail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getMediaIds() {
        return mediaIds;
    }

    public void setMediaIds(List<String> mediaIds) {
        this.mediaIds = mediaIds;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getWifiMac() {
        return wifiMac;
    }

    public void setWifiMac(String wifiMac) {
        this.wifiMac = wifiMac;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceDetail() {
        return deviceDetail;
    }

    public void setDeviceDetail(String deviceDetail) {
        this.deviceDetail = deviceDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("checkinTime", checkinTime)
                .append("locationTitle", locationTitle)
                .append("locationDetail", locationDetail)
                .append("notes", notes)
                .append("mediaIds", mediaIds)
                .append("wifiName", wifiName)
                .append("wifiMac", wifiMac)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("deviceType", deviceType)
                .append("deviceDetail", deviceDetail)
                .toString();
    }
}
