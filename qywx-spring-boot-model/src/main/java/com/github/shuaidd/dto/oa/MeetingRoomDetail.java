package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentLocation;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 13:03
 **/
public class MeetingRoomDetail {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    private String name;

    private Integer capacity;

    private String city;

    private String building;

    private String floor;

    private List<Integer> equipment;

    private MomentLocation coordinate;

    @JsonProperty("need_approval")
    private Integer needApproval;

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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

    public List<Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Integer> equipment) {
        this.equipment = equipment;
    }

    public MomentLocation getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(MomentLocation coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getNeedApproval() {
        return needApproval;
    }

    public void setNeedApproval(Integer needApproval) {
        this.needApproval = needApproval;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MeetingRoomDetail.class.getSimpleName() + "[", "]")
                .add("meetingRoomId='" + meetingRoomId + "'")
                .add("name='" + name + "'")
                .add("capacity=" + capacity)
                .add("city='" + city + "'")
                .add("building='" + building + "'")
                .add("floor='" + floor + "'")
                .add("equipment=" + equipment)
                .add("coordinate=" + coordinate)
                .add("needApproval=" + needApproval)
                .toString();
    }
}
