package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询对外收款记录请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class ExternalBillListRequest {

    /**
     * 收款记录开始时间戳，单位秒
     **/
    @JsonProperty("begin_time")
    private Long beginTime;

    /**
     * 收款记录结束时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 企业收款成员 userid，可选
     **/
    @JsonProperty("payee_userid")
    private String payeeUserId;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 返回记录数上限，最大 1000
     **/
    @JsonProperty("limit")
    private Integer limit;
}
