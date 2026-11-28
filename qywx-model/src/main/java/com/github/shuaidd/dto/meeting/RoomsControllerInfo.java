package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms控制器信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsControllerInfo {
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
     * 控制器名称
     **/
    @JsonProperty("controller_name")
    private String controllerName;
    /**
     * 控制器厂商
     **/
    @JsonProperty("manufacture_name")
    private String manufactureName;
    /**
     * 控制器型号
     **/
    @JsonProperty("controller_model")
    private String controllerModel;
    /**
     * 应用程序版本
     **/
    @JsonProperty("app_version")
    private String appVersion;
    /**
     * 设备状态：0离线、1在线
     **/
    @JsonProperty("status")
    private String status;
    /**
     * 控制器固件版本
     **/
    @JsonProperty("framework_version")
    private String frameworkVersion;
    /**
     * 控制器IP地址
     **/
    @JsonProperty("ip_address")
    private String ipAddress;
    /**
     * 控制器MAC地址
     **/
    @JsonProperty("mac_address")
    private String macAddress;
    /**
     * CPU类型
     **/
    @JsonProperty("cpu_type")
    private String cpuType;
    /**
     * CPU当前占用率
     **/
    @JsonProperty("cpu_usage")
    private String cpuUsage;
    /**
     * 网络类型
     **/
    @JsonProperty("network_type")
    private String networkType;
    /**
     * 内存使用大小
     **/
    @JsonProperty("mem_usage")
    private String memUsage;
}
