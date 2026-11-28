package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * Rooms会议室基本信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsBasicInfo {
    /**
     * Rooms设备ID列表
     **/
    @JsonProperty("rooms_id_list")
    private List<String> roomsIdList;
    /**
     * Rooms会议室名称
     **/
    @JsonProperty("meeting_room_name")
    private String meetingRoomName;
    /**
     * 城市
     **/
    @JsonProperty("city")
    private String city;
    /**
     * 建筑
     **/
    @JsonProperty("building")
    private String building;
    /**
     * 楼层
     **/
    @JsonProperty("floor")
    private String floor;
    /**
     * 容纳人数
     **/
    @JsonProperty("participant_number")
    private Integer participantNumber;
    /**
     * Rooms会议室设备
     **/
    @JsonProperty("device")
    private String device;
    /**
     * 描述，base64编码
     **/
    @JsonProperty("desc")
    private String desc;
    /**
     * 管理员密码，base64编码
     **/
    @JsonProperty("password")
    private String password;
}
