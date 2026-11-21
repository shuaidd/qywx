package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerStrategy;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户联系规则组详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyResponse extends AbstractBaseResponse {

    /**
     * 客户联系规则组详情
     */
    @JsonProperty("strategy")
    private CustomerStrategy strategy;

    public CustomerStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(CustomerStrategy strategy) {
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