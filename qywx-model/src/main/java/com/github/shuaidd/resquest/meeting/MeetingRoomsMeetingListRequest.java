package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询Rooms会议室会议列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRoomsMeetingListRequest {
    /**
     * Rooms会议室 ID，与roomsId二选一
     **/
    @JsonProperty("meeting_room_id")
    private String meetingRoomId;
    /**
     * Rooms设备ID，与meetingRoomId二选一
     **/
    @JsonProperty("rooms_id")
    private String roomsId;
    /**
     * 查询起始时间，Unix秒
     **/
    @JsonProperty("start_time")
    private Long startTime;
    /**
     * 查询结束时间，Unix秒
     **/
    @JsonProperty("end_time")
    private Long endTime;
    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;
    /**
     * 分页大小，最大20，默认20
     **/
    @JsonProperty("limit")
    private Integer limit;
}
