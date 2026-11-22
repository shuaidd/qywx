package com.github.shuaidd.resquest.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 可信设备信息查询请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class TrustDeviceListRequest {
    /**
     * 分页查询游标，首次查询可不填
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 查询设备类型，1-可信企业设备，2-未知设备，3-可信个人设备
     **/
    @JsonProperty("type")
    private Integer type;

    /**
     * 单页最大记录数，默认100，取值范围1~100
     **/
    @JsonProperty("limit")
    private Integer limit;
}
