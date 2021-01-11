package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-11 17:46
 **/
public class MeetingDetailAttendee {
    private List<DeviceData> device;
    private List<UserId> member;
    @JsonProperty("external_user")
    private List<ExternalUser> externalUser;

    public List<DeviceData> getDevice() {
        return device;
    }

    public void setDevice(List<DeviceData> device) {
        this.device = device;
    }

    public List<UserId> getMember() {
        return member;
    }

    public void setMember(List<UserId> member) {
        this.member = member;
    }

    public List<ExternalUser> getExternalUser() {
        return externalUser;
    }

    public void setExternalUser(List<ExternalUser> externalUser) {
        this.externalUser = externalUser;
    }
}
