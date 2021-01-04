package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.TagGroup;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-04 21:12
 **/
public class TagGroupResponse extends AbstractBaseResponse {
    @JsonProperty("tag_group")
    private List<TagGroup> tagGroups;

    public List<TagGroup> getTagGroups() {
        return tagGroups;
    }

    public void setTagGroups(List<TagGroup> tagGroups) {
        this.tagGroups = tagGroups;
    }
}
