package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户朋友圈规则组 ID
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyId {

    /**
     * 客户朋友圈规则组 ID
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
