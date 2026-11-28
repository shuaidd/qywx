package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 21:08
 **/
public class CustomTag {
    private String id;
    private String name;
    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;
    private Boolean deleted;
    private Integer order;

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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("createTime", createTime)
                .append("deleted", deleted)
                .append("order", order)
                .toString();
    }
}
