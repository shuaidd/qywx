package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 截屏操作记录查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ScreenOperRecordListRequest {
    /**
     * 查询开始时间，Unix秒级时间戳
     **/
    @JsonProperty("start_time")
    private Long startTime;

    /**
     * 查询结束时间，Unix秒级时间戳，与开始时间跨度不能超过14天
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 需要查询的截屏操作者userid列表，单次最多100个
     **/
    @JsonProperty("userid_list")
    private List<String> useridList;

    /**
     * 需要查询的截屏操作者部门id列表，单次最多100个
     **/
    @JsonProperty("department_id_list")
    private List<Integer> departmentIdList;

    /**
     * 截屏内容类型，不填表示全部
     **/
    @JsonProperty("screen_shot_type")
    private Integer screenShotType;

    /**
     * 分页查询游标，首次查询可不填
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 单页最大记录数，取值范围1~1000
     **/
    @JsonProperty("limit")
    private Integer limit;
}
