package com.github.shuaidd.resquest.advanced;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 批量获取申请单ID请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class GetApplyIdListRequest {
    /**
     * 申请的高级账号类型：1-邮件；2-文档；3-微盘；4-会议
     **/
    @JsonProperty("business_type")
    private Integer businessType;

    /**
     * 申请的userid
     **/
    @JsonProperty("userid")
    private String userid;

    /**
     * 分页查询的数据上限，默认100，最大200；不保证每次返回刚好为指定数量，须用has_more判断是否继续请求
     **/
    @JsonProperty("limit")
    private Integer limit;

    /**
     * 分页查询游标，由上一次调用返回，首次调用可不填
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 申请单类型：0-所有；1-仅api单；2-非api申请单，默认为0
     **/
    @JsonProperty("req_type")
    private Integer reqType;
}