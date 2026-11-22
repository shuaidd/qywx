package com.github.shuaidd.dto.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Rooms会议室关联会议信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
public class RoomsMeetingInfo {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 有效会议Code
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;
    /**
     * 会议主题
     **/
    @JsonProperty("subject")
    private String subject;
    /**
     * 会议状态
     **/
    @JsonProperty("status")
    private String status;
    /**
     * 会议类型
     **/
    @JsonProperty("meeting_type")
    private Integer meetingType;
    /**
     * 会议预订开始时间，Unix秒
     **/
    @JsonProperty("start_time")
    private Long startTime;
    /**
     * 会议预订结束时间，Unix秒
     **/
    @JsonProperty("end_time")
    private Long endTime;
}
