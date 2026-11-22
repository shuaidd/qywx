package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取员工会议发起记录请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingStartListRequest {

    /**
     * 查询类型：1 发起成功、2 发起失败
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 查询范围起始时间戳，单位秒
     **/
    @JsonProperty("begin_time")
    private Long beginTime;

    /**
     * 查询范围结束时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 单次拉取记录数量
     **/
    @JsonProperty("limit")
    private Integer limit;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;
}