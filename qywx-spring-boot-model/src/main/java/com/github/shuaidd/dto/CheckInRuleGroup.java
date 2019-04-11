package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-11 12:22
 **/
public class CheckInRuleGroup {
    /**
     * 打卡规则类型。1：固定时间上下班；2：按班次上下班；3：自由上下班
     */
    @JsonProperty("grouptype")
    private Integer groupType;

    @JsonProperty("groupid")
    private Integer groupId;

    @JsonProperty("sync_holidays")
    private Boolean syncHolidays;

    @JsonProperty("groupname")
    private String groupName;

    @JsonProperty("need_photo")
    private Boolean needPhoto;

    @JsonProperty("note_can_use_local_pic")
    private Boolean noteCanUseLocalPic;

    @JsonProperty("allow_checkin_offworkday")
    private Boolean allowCheckinOffWorkDay;

    @JsonProperty("allow_apply_offworkday")
    private Boolean allowApplyOffWorkDay;

    @JsonProperty("checkindate")
    private List<CheckInDate> checkInDates;

    @JsonProperty("spe_workdays")
    private List<SpeWorkDay> speWorkDays;

    @JsonProperty("spe_offdays")
    private List<SpeWorkDay> speOffDays;

    @JsonProperty("wifimac_infos")
    private List<WifiMacInfo> wifiMacInfos;

    @JsonProperty("loc_infos")
    private List<CheckInLocation> locations;

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Boolean getSyncHolidays() {
        return syncHolidays;
    }

    public void setSyncHolidays(Boolean syncHolidays) {
        this.syncHolidays = syncHolidays;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getNeedPhoto() {
        return needPhoto;
    }

    public void setNeedPhoto(Boolean needPhoto) {
        this.needPhoto = needPhoto;
    }

    public Boolean getNoteCanUseLocalPic() {
        return noteCanUseLocalPic;
    }

    public void setNoteCanUseLocalPic(Boolean noteCanUseLocalPic) {
        this.noteCanUseLocalPic = noteCanUseLocalPic;
    }

    public Boolean getAllowCheckinOffWorkDay() {
        return allowCheckinOffWorkDay;
    }

    public void setAllowCheckinOffWorkDay(Boolean allowCheckinOffWorkDay) {
        this.allowCheckinOffWorkDay = allowCheckinOffWorkDay;
    }

    public Boolean getAllowApplyOffWorkDay() {
        return allowApplyOffWorkDay;
    }

    public void setAllowApplyOffWorkDay(Boolean allowApplyOffWorkDay) {
        this.allowApplyOffWorkDay = allowApplyOffWorkDay;
    }

    public List<CheckInDate> getCheckInDates() {
        return checkInDates;
    }

    public void setCheckInDates(List<CheckInDate> checkInDates) {
        this.checkInDates = checkInDates;
    }

    public List<SpeWorkDay> getSpeWorkDays() {
        return speWorkDays;
    }

    public void setSpeWorkDays(List<SpeWorkDay> speWorkDays) {
        this.speWorkDays = speWorkDays;
    }

    public List<SpeWorkDay> getSpeOffDays() {
        return speOffDays;
    }

    public void setSpeOffDays(List<SpeWorkDay> speOffDays) {
        this.speOffDays = speOffDays;
    }

    public List<WifiMacInfo> getWifiMacInfos() {
        return wifiMacInfos;
    }

    public void setWifiMacInfos(List<WifiMacInfo> wifiMacInfos) {
        this.wifiMacInfos = wifiMacInfos;
    }

    public List<CheckInLocation> getLocations() {
        return locations;
    }

    public void setLocations(List<CheckInLocation> locations) {
        this.locations = locations;
    }
}
