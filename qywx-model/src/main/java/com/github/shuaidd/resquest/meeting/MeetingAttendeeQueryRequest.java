package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询会议成员请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingAttendeeQueryRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 周期性会议子会议 ID
     **/
    @JsonProperty("sub_meetingid")
    private String subMeetingId;

    /**
     * 参会时间过滤起始时间戳，单位秒
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 参会时间过滤终止时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 分页大小
     **/
    @JsonProperty("limit")
    private Integer limit;
}