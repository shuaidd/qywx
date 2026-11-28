package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 客户联系规则组下的企业客户标签
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTag {

    /**
     * 标签 ID
     */
    @JsonProperty("id")
    private String id;

    /**
     * 标签名称
     */
    @JsonProperty("name")
    private String name;

    /**
     * 标签创建时间
     */
    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;

    /**
     * 标签排序值；值越大排序越靠前，有效范围是 [0, 2^32)
     */
    @JsonProperty("order")
    private Long order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("createTime", createTime)
                .append("order", order)
                .toString();
    }
}
