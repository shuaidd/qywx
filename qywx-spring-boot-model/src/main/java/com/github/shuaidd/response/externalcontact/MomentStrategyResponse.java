package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentStrategy;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户朋友圈规则组详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyResponse extends AbstractBaseResponse {

    /**
     * 客户朋友圈规则组详情
     */
    @JsonProperty("strategy")
    private MomentStrategy strategy;

    public MomentStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(MomentStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("strategy", strategy)
                .toString();
    }
}
