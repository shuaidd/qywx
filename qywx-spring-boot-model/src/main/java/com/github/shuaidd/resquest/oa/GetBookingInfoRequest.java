package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 13:12
 **/
public class GetBookingInfoRequest {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    private String city;

    private String building;

    private String floor;

    @JsonProperty("start_time")
    private Long startTime;

    @JsonProperty("end_time")
    private Long endTime;

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetBookingInfoRequest.class.getSimpleName() + "[", "]")
                .add("meetingRoomId='" + meetingRoomId + "'")
                .add("city='" + city + "'")
                .add("building='" + building + "'")
                .add("floor='" + floor + "'")
                .add("startTime=" + startTime)
                .add("endTime=" + endTime)
                .toString();
    }
}
