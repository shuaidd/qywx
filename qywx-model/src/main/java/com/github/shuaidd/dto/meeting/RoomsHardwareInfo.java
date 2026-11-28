package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室硬件信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsHardwareInfo {
    /**
     * 设备厂家
     **/
    @JsonProperty("factory")
    private String factory;
    /**
     * 设备型号
     **/
    @JsonProperty("device_model")
    private String deviceModel;
    /**
     * 设备序列号
     **/
    @JsonProperty("sn")
    private String sn;
    /**
     * 设备IP地址
     **/
    @JsonProperty("ip")
    private String ip;
    /**
     * 设备MAC地址
     **/
    @JsonProperty("mac")
    private String mac;
    /**
     * Rooms软件版本
     **/
    @JsonProperty("rooms_version")
    private String roomsVersion;
    /**
     * 设备固件版本
     **/
    @JsonProperty("firmware_version")
    private String firmwareVersion;
    /**
     * 设备健康状况
     **/
    @JsonProperty("health_status")
    private String healthStatus;
    /**
     * 设备系统版本
     **/
    @JsonProperty("system_type")
    private String systemType;
    /**
     * 会议室状态：0未激活、1未绑定、2空闲、3使用中、4离线
     **/
    @JsonProperty("meeting_room_status")
    private Integer meetingRoomStatus;
    /**
     * 设备激活时间
     **/
    @JsonProperty("active_time")
    private String activeTime;
    /**
     * CPU信息
     **/
    @JsonProperty("cpu_info")
    private String cpuInfo;
    /**
     * CPU最大占用率
     **/
    @JsonProperty("cpu_usage")
    private String cpuUsage;
    /**
     * GPU信息
     **/
    @JsonProperty("gpu_info")
    private String gpuInfo;
    /**
     * 网络类型
     **/
    @JsonProperty("net_type")
    private String netType;
    /**
     * 内存信息
     **/
    @JsonProperty("memory_info")
    private String memoryInfo;
    /**
     * 显示器刷新率
     **/
    @JsonProperty("monitor_frequency")
    private String monitorFrequency;
    /**
     * 摄像头型号
     **/
    @JsonProperty("camera_model")
    private String cameraModel;
    /**
     * 是否开启视频镜像
     **/
    @JsonProperty("enable_video_mirror")
    private Boolean enableVideoMirror;
    /**
     * 麦克风信息
     **/
    @JsonProperty("microphone_info")
    private String microphoneInfo;
    /**
     * 扬声器信息
     **/
    @JsonProperty("speaker_info")
    private String speakerInfo;
}
