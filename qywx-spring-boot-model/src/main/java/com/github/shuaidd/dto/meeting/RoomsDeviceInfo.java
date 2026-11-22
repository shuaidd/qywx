package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms设备信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsDeviceInfo {
    /**
     * Rooms会议室 ID
     **/
    @JsonProperty("meeting_room_id")
    private String meetingRoomId;
    /**
     * Rooms设备ID
     **/
    @JsonProperty("rooms_id")
    private String roomsId;
    /**
     * Rooms会议室名称
     **/
    @JsonProperty("meeting_room_name")
    private String meetingRoomName;
    /**
     * Rooms会议室地址
     **/
    @JsonProperty("meeting_room_location")
    private String meetingRoomLocation;
    /**
     * 设备型号
     **/
    @JsonProperty("device_model")
    private String deviceModel;
    /**
     * 应用程序版本
     **/
    @JsonProperty("app_version")
    private String appVersion;
    /**
     * 会议室状态：0未激活、1未绑定、2空闲、3使用中、4离线
     **/
    @JsonProperty("meeting_room_status")
    private Integer meetingRoomStatus;
    /**
     * 设备健康信息
     **/
    @JsonProperty("device_monitor_info")
    private RoomsDeviceMonitorInfo deviceMonitorInfo;
}
