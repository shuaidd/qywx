package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 安全管理操作日志查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class SecurityOperLogListRequest {
    /**
     * 查询开始时间，Unix秒级时间戳
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 查询结束时间，Unix秒级时间戳
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 操作类型，不填表示全部
     **/
    @JsonProperty("oper_type")
    private Integer operType;

    /**
     * 操作者userid过滤条件，可选
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 分页查询游标，首次查询可不填
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 单页最大记录数，默认400，取值范围1~400
     **/
    @JsonProperty("limit")
    private Integer limit;
}