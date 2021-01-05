package com.github.shuaidd.dto;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:32
 **/
public class MomentLocation {
    private String latitude;
    private String longitude;
    private String name;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
