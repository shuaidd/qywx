package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerStrategy;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客户联系规则组列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyListResponse extends AbstractBaseResponse {

    /**
     * 客户联系规则组列表
     */
    @JsonProperty("strategy")
    private List<CustomerStrategy> strategy;

    public List<CustomerStrategy> getStrategy() {
        return strategy;
    }

    public void setStrategy(List<CustomerStrategy> strategy) {
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