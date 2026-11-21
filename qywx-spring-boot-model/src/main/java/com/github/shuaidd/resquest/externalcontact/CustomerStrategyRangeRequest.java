package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取客户联系规则组管理范围请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyRangeRequest {

    /**
     * 客户联系规则组 ID
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 分页游标；首次调用不填
     */
    @JsonProperty("cursor")
    private String cursor;

    /**
     * 每页成员/部门节点数量；默认 1000，最大 1000
     */
    @JsonProperty("limit")
    private Integer limit = 1000;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .append("cursor", cursor)
                .append("limit", limit)
                .toString();
    }
}