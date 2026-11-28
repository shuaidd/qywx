package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询等候室成员请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingWaitingRoomUserListRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 分页大小
     **/
    @JsonProperty("limit")
    private Integer limit;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;
}