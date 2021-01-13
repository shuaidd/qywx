package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-04 20:59
 **/

public class TagGroup {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("group_name")
    private String groupName;

    @JsonProperty("create_time")
    private Date createTime;
    private Integer order;
    private Boolean deleted;
    private List<CustomTag> tag;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public List<CustomTag> getTag() {
        return tag;
    }

    public void setTag(List<CustomTag> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupId", groupId)
                .append("groupName", groupName)
                .append("createTime", createTime)
                .append("order", order)
                .append("deleted", deleted)
                .append("tag", tag)
                .toString();
    }
}
