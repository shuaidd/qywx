package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 删除客户联系规则组企业客户标签或标签组请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyTagDeleteRequest {

    /**
     * 要删除的标签 ID 列表
     */
    @JsonProperty("tag_id")
    private List<String> tagId;

    /**
     * 要删除的标签组 ID 列表
     */
    @JsonProperty("group_id")
    private List<String> groupId;

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
                .append("tagId", tagId)
                .append("groupId", groupId)
                .toString();
    }
}
