package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询Rooms控制器列表请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingRoomsControllerListRequest {
    /**
     * 控制器名称，支持模糊匹配
     **/
    @JsonProperty("controller_name")
    private String controllerName;
    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;
    /**
     * 分页大小，1到50，默认20
     **/
    @JsonProperty("limit")
    private Integer limit;
}
