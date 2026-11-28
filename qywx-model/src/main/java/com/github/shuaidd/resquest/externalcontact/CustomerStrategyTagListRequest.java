package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 查询客户联系规则组企业客户标签请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagListRequest {

    /**
     * 客户联系规则组 ID；不填时查询全部规则组标签
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 要查询的标签 ID 列表
     */
    @JsonProperty("tag_id")
    private List<String> tagId;

    /**
     * 要查询的标签组 ID 列表；返回标签组及其下全部标签
     */
    @JsonProperty("group_id")
    private List<String> groupId;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public List<String> getTagId() {
        return tagId;
    }

    public void setTagId(List<String> tagId) {
        this.tagId = tagId;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<String> groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .append("tagId", tagId)
                .append("groupId", groupId)
                .toString();
    }
}
