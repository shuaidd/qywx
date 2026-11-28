package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms设备健康信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsDeviceMonitorInfo {
    /**
     * 摄像头健康状态
     **/
    @JsonProperty("camera_status")
    private Boolean cameraStatus;
    /**
     * 麦克风健康状态
     **/
    @JsonProperty("microphone_status")
    private Boolean microphoneStatus;
    /**
     * 扬声器健康状态
     **/
    @JsonProperty("speaker_status")
    private Boolean speakerStatus;
}
