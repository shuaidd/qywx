package com.github.shuaidd.dto;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 16:26
 **/
public class FollowUserTag {
    private String groupName;
    private String tagName;
    private String tagId;
    private Integer type;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
