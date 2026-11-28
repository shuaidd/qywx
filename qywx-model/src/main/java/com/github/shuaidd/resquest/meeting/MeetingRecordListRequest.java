package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MeetingRecordListRequest
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRecordListRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 会议Code
     **/
    @JsonProperty("meeting_code")
    private String meetingCode;

    /**
     * 查询成员的userid
     **/
    @JsonProperty("userid")
    private String userId;

    /**
     * 查询起始时间戳，单位秒
     **/
    @JsonProperty("start_time")
    private Integer startTime;

    /**
     * 查询结束时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private Integer endTime;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 单次返回最大数量
     **/
    @JsonProperty("limit")
    private Integer limit;

}
