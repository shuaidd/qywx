package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 21:13
 **/
public class AuthenticationResponse extends AbstractBaseResponse {

    @JsonProperty("UserId")
    private String userId;

    @JsonProperty("DeviceId")
    private String deviceId;

    @JsonProperty("OpenId")
    private String openId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
