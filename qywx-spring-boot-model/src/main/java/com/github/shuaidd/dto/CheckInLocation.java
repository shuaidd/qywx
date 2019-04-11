package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-11 12:42
 **/
public class CheckInLocation {
    /**
     * 位置打卡地点纬度
     */
    private Long lat;
    /**
     * 位置打卡地点经度
     */
    private Long lng;

    @JsonProperty("loc_title")
    private String locationTitle;

    @JsonProperty("loc_detail")
    private String locationDetail;

    private Integer distance;

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CheckInLocation.class.getSimpleName() + "[", "]")
                .add("lat=" + lat)
                .add("lng=" + lng)
                .add("locationTitle='" + locationTitle + "'")
                .add("locationDetail='" + locationDetail + "'")
                .add("distance=" + distance)
                .toString();
    }
}
