package com.github.shuaidd.resquest.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询企业支付资金流水请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@Accessors(chain = true)
public class FundFlowRequest {

    /**
     * 资金流水开始时间戳，单位秒
     **/
    @JsonProperty("begin_time")
    private Long beginTime;

    /**
     * 资金流水结束时间戳，单位秒
     **/
    @JsonProperty("end_time")
    private Long endTime;

    /**
     * 商户号，可选
     **/
    @JsonProperty("mch_id")
    private String mchId;

    /**
     * 分页游标
     **/
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 返回记录数上限，默认 100，最大 200
     **/
    @JsonProperty("limit")
    private Integer limit;
}
