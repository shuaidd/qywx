package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 07:30
 **/
public class CreateTagResponse extends AbstractBaseResponse {

    @JsonProperty("tagid")
    private Integer tagId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateTagResponse.class.getSimpleName() + "[", "]")
                .add("tagId=" + tagId)
                .toString();
    }
}
