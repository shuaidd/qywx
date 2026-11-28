package com.github.shuaidd.dto.checkin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-11 12:40
 **/
public class WifiMacInfo {

    @JsonProperty("wifiname")
    private String wifiName;

    @JsonProperty("wifimac")
    private String wifiMac;

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
}
