package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentLocation;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-20 11:13
 **/
public class MeetingRoomRequest {

    @JsonProperty("meetingroom_id")
    private String meetingRoomId;

    private String name;

    private Integer capacity;

    private String city;

    private String building;

    private String floor;

    private List<Integer> equipment;

    private MomentLocation coordinate;

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

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId) {
        this.meetingRoomId = meetingRoomId;
    }
}
