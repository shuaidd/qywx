package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * 客户联系规则组下的企业客户标签组
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagGroup {

    /**
     * 标签组 ID
     */
    @JsonProperty("group_id")
    private String groupId;

    /**
     * 标签组名称
     */
    @JsonProperty("group_name")
    private String groupName;

    /**
     * 标签组创建时间
     */
    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;

    /**
     * 标签组排序值；值越大排序越靠前，有效范围是 [0, 2^32)
     */
    @JsonProperty("order")
    private Long order;

    /**
     * 标签组所属的客户联系规则组 ID
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 标签组内的标签列表
     */
    @JsonProperty("tag")
    private List<CustomerStrategyTag> tag;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
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
                .append("groupId", groupId)
                .append("groupName", groupName)
                .append("createTime", createTime)
                .append("order", order)
                .append("strategyId", strategyId)
                .append("tag", tag)
                .toString();
    }
}
