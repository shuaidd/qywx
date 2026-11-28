package com.github.shuaidd.response.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.meeting.RoomsAccountInfo;
import com.github.shuaidd.dto.meeting.RoomsBasicInfo;
import com.github.shuaidd.dto.meeting.RoomsHardwareInfo;
import com.github.shuaidd.dto.meeting.RoomsPmiInfo;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Rooms会议室详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class MeetingRoomsInfoResponse extends AbstractBaseResponse {
    /**
     * Rooms会议室基本信息
     **/
    @JsonProperty("basic_info")
    private RoomsBasicInfo basicInfo;
    /**
     * Rooms会议室账号信息
     **/
    @JsonProperty("account_info")
    private RoomsAccountInfo accountInfo;
    /**
     * Rooms会议室硬件信息
     **/
    @JsonProperty("hardware_info")
    private RoomsHardwareInfo hardwareInfo;
    /**
     * Rooms会议室PMI信息
     **/
    @JsonProperty("pmi_info")
    private RoomsPmiInfo pmiInfo;
    /**
     * 告警通知状态：0未开启、1已开启
     **/
    @JsonProperty("monitor_status")
    private Integer monitorStatus;
    /**
     * 是否允许被呼叫
     **/
    @JsonProperty("is_allow_call")
    private Boolean allowCall;
    /**
     * 预定状态：0未开放预定、1开放预定
     **/
    @JsonProperty("scheduled_status")
    private Integer scheduledStatus;
}
