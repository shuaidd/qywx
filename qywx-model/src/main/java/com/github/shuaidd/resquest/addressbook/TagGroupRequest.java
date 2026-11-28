package com.github.shuaidd.resquest.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 21:14
 **/
public class TagGroupRequest {

    @JsonProperty("tag_id")
    private List<String> tagIds;

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }
}
