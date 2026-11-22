package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MeetingRecordStatisticsRequest
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRecordStatisticsRequest {
    /**
     * 会议录制ID
     **/
    @JsonProperty("meeting_record_id")
    private String meetingRecordId;

    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

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

}
