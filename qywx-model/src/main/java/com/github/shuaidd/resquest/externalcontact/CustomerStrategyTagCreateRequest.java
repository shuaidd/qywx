package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerStrategyTag;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 创建客户联系规则组企业客户标签请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagCreateRequest {

    /**
     * 客户联系规则组 ID；仅可在一级规则组下添加标签
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 已有标签组 ID；填写后忽略标签组名称和标签组排序值
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * 新标签组名称；与规则组下已有标签组同名时加入同名标签组，最长 30 个字符
     */
    @JsonProperty("group_name")
    private String groupName;

    /**
     * 新标签组排序值；值越大排序越靠前，有效范围是 [0, 2^32)
     */
    @JsonProperty("order")
    private Long order;

    /**
     * 要创建的标签列表；不能为空，标签组内标签名称不可重复
     */
    @JsonProperty("tag")
    private List<CustomerStrategyTag> tag;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public List<CustomerStrategyTag> getTag() {
        return tag;
    }

    public void setTag(List<CustomerStrategyTag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .append("groupId", groupId)
                .append("groupName", groupName)
                .append("order", order)
                .append("tag", tag)
                .toString();
    }
}
