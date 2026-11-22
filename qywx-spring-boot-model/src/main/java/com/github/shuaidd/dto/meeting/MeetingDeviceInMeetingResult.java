package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 成员设备入会检查结果
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class MeetingDeviceInMeetingResult {

    /**
     * 已检查到设备入会的会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 进入会议的终端设备类型
     **/
    @JsonProperty("instance_id")
    private Integer instanceId;
}