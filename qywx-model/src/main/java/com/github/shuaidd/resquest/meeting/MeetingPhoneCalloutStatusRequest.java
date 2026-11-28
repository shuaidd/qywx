package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询电话入会外呼状态请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingPhoneCalloutStatusRequest {
    /**
     * 会议ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;
    /**
     * 分页游标，第一次查询可不传
     **/
    @JsonProperty("cursor")
    private String cursor;
    /**
     * 分页大小，默认20，最大100
     **/
    @JsonProperty("limit")
    private Integer limit;
}
