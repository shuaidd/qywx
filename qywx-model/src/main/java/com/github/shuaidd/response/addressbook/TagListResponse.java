package com.github.shuaidd.response.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.Tag;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-06 08:17
 **/
public class TagListResponse extends AbstractBaseResponse {

    @JsonProperty("taglist")
    private List<Tag> tagList;

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
