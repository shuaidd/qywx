package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 编辑客户联系规则组企业客户标签或标签组请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagEditRequest {

    /**
     * 要编辑的标签或标签组 ID
     */
    @JsonProperty("id")
    private String id;

    /**
     * 新名称；标签组不能与已有标签组重名，标签不能与同组其他标签重名，最长 30 个字符
     */
    @JsonProperty("name")
    private String name;

    /**
     * 新排序值；值越大排序越靠前，有效范围是 [0, 2^32)
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
                .append("order", order)
                .toString();
    }
}
