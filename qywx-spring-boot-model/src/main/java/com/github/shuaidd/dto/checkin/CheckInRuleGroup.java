package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
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

    private CheckInRuleGroupRange range;

    @JsonProperty("create_time")
    private Long createTime;

    @JsonProperty("white_users")
    private List<String> whiteUsers;

    private Integer type;

    @JsonProperty("reporterinfo")
    private ReporterInfo reporterInfo;

    @JsonProperty("ot_info")
    private OverTimeInfo overTimeInfo;

    @JsonProperty("allow_apply_bk_cnt")
    private Integer allowApplyBkCnt;

    @JsonProperty("option_out_range")
    private Integer optionOutRange;

    @JsonProperty("create_userid")
    private String createUserId;

    @JsonProperty("allow_apply_bk_day_limit")
    private Integer allowApplyBkDayLimit;

    @JsonProperty("use_face_detect")
    private Boolean useFaceDetect;

    @JsonProperty("update_userid")
    private String updateUserId;

    @JsonProperty("offwork_interval_time")
    private Integer offWorkIntervalTime;

    @JsonProperty("schedulelist")
    private List<ScheduleItem> scheduleList;

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

    public CheckInRuleGroupRange getRange() {
        return range;
    }

    public void setRange(CheckInRuleGroupRange range) {
        this.range = range;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<String> getWhiteUsers() {
        return whiteUsers;
    }

    public void setWhiteUsers(List<String> whiteUsers) {
        this.whiteUsers = whiteUsers;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ReporterInfo getReporterInfo() {
        return reporterInfo;
    }

    public void setReporterInfo(ReporterInfo reporterInfo) {
        this.reporterInfo = reporterInfo;
    }

    public OverTimeInfo getOverTimeInfo() {
        return overTimeInfo;
    }

    public void setOverTimeInfo(OverTimeInfo overTimeInfo) {
        this.overTimeInfo = overTimeInfo;
    }

    public Integer getAllowApplyBkCnt() {
        return allowApplyBkCnt;
    }

    public void setAllowApplyBkCnt(Integer allowApplyBkCnt) {
        this.allowApplyBkCnt = allowApplyBkCnt;
    }

    public Integer getOptionOutRange() {
        return optionOutRange;
    }

    public void setOptionOutRange(Integer optionOutRange) {
        this.optionOutRange = optionOutRange;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getAllowApplyBkDayLimit() {
        return allowApplyBkDayLimit;
    }

    public void setAllowApplyBkDayLimit(Integer allowApplyBkDayLimit) {
        this.allowApplyBkDayLimit = allowApplyBkDayLimit;
    }

    public Boolean getUseFaceDetect() {
        return useFaceDetect;
    }

    public void setUseFaceDetect(Boolean useFaceDetect) {
        this.useFaceDetect = useFaceDetect;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getOffWorkIntervalTime() {
        return offWorkIntervalTime;
    }

    public void setOffWorkIntervalTime(Integer offWorkIntervalTime) {
        this.offWorkIntervalTime = offWorkIntervalTime;
    }

    public List<ScheduleItem> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<ScheduleItem> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupType", groupType)
                .append("groupId", groupId)
                .append("syncHolidays", syncHolidays)
                .append("groupName", groupName)
                .append("needPhoto", needPhoto)
                .append("noteCanUseLocalPic", noteCanUseLocalPic)
                .append("allowCheckinOffWorkDay", allowCheckinOffWorkDay)
                .append("allowApplyOffWorkDay", allowApplyOffWorkDay)
                .append("checkInDates", checkInDates)
                .append("speWorkDays", speWorkDays)
                .append("speOffDays", speOffDays)
                .append("wifiMacInfos", wifiMacInfos)
                .append("locations", locations)
                .append("range", range)
                .append("createTime", createTime)
                .append("whiteUsers", whiteUsers)
                .append("type", type)
                .append("reporterInfo", reporterInfo)
                .append("overTimeInfo", overTimeInfo)
                .append("allowApplyBkCnt", allowApplyBkCnt)
                .append("optionOutRange", optionOutRange)
                .append("createUserId", createUserId)
                .append("allowApplyBkDayLimit", allowApplyBkDayLimit)
                .append("useFaceDetect", useFaceDetect)
                .append("updateUserId", updateUserId)
                .append("offWorkIntervalTime", offWorkIntervalTime)
                .append("scheduleList", scheduleList)
                .toString();
    }
}
