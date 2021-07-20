package com.github.shuaidd.dto.oa.formcontrol;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-16 17:51
 **/
public class LocationData {

    private String latitude;
    private String longitude;
    private String title;
    private String address;
    private Long time;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LocationData.class.getSimpleName() + "[", "]")
                .add("latitude='" + latitude + "'")
                .add("longitude='" + longitude + "'")
                .add("title='" + title + "'")
                .add("address='" + address + "'")
                .add("time=" + time)
                .toString();
    }
}
