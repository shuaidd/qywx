package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询会议报名列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingEnrollListRequest {

    /**
     * 会议 ID
     **/
    @JsonProperty("meetingid")
    private String meetingId;

    /**
     * 审批状态筛选：0 全部、1 待审批、2 已拒绝、3 已批准
     **/
    @JsonProperty("status")
    private Integer status;

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