package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 按规则组 ID 查询客户联系规则组请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyIdRequest {

    /**
     * 客户联系规则组 ID
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .toString();
    }
}