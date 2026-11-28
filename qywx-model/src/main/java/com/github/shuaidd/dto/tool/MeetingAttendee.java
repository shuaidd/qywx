package com.github.shuaidd.dto.tool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-11 14:27
 **/
public class MeetingAttendee {

    @JsonProperty("userid")
    private List<String> userIds;

    @JsonProperty("external_userid")
    private List<String> externalUserId;

    @JsonProperty("device_sn")
    private List<String> deviceSn;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(List<String> externalUserId) {
        this.externalUserId = externalUserId;
    }

    public List<String> getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(List<String> deviceSn) {
        this.deviceSn = deviceSn;
    }
}
