package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 07:27
 **/
public class Tag {

    @JsonProperty("tagid")
    private Integer tagId;

    @JsonProperty("tagname")
    private String tagName;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
                .add("tagId=" + tagId)
                .add("tagName='" + tagName + "'")
                .toString();
    }
}
