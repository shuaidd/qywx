package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建客户朋友圈规则组响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyIdResponse extends AbstractBaseResponse {

    /**
     * 新创建的客户朋友圈规则组 ID
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
                .appendSuper(super.toString())
                .append("strategyId", strategyId)
                .toString();
    }
}
