package com.github.shuaidd.dto.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-09-01 16:07
 **/
public class KnowledgeGroup {

    @JsonProperty(value = "group_id")
    private String groupId;

    private String name;

    @JsonProperty(value = "is_default")
    private Integer defaultGroup;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(Integer defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", KnowledgeGroup.class.getSimpleName() + "[", "]")
                .add("groupId='" + groupId + "'")
                .add("name='" + name + "'")
                .add("defaultGroup=" + defaultGroup)
                .toString();
    }
}
