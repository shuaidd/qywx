package com.github.shuaidd.resquest.meeting;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 高级功能账号列表查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class MeetingVipListRequest {
    /**
     * 分页查询游标，首次查询可不填
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 分页返回数据上限，默认100，最大200
     **/
    @JsonProperty("limit")
    private Integer limit;
}
