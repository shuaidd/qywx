package com.github.shuaidd.response.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.github.shuaidd.dto.payment.*;
import java.util.List;

/**
 * 查询企业支付资金流水响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class FundFlowResponse extends AbstractBaseResponse {

    /**
     * 资金流水记录列表
     **/
    @JsonProperty("fund_flow_list")
    private List<FundFlowRecord> fundFlowList;
}
