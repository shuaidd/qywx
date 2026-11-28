package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomTag;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 21:19
 **/
public class AddCorpTagRequest {
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("group_name")
    private String groupName;
    private Integer order;
    @JsonProperty("agentid")
    private Integer agentId;
    @JsonProperty("tag")
    private List<CustomTag> tags;

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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public List<CustomTag> getTags() {
        return tags;
    }

    public void setTags(List<CustomTag> tags) {
        this.tags = tags;
    }
}
