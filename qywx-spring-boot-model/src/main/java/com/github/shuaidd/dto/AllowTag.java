package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 22:47
 **/
public class AllowTag {

    @JsonProperty("tagid")
    private List<Integer> tagIdList;

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AllowTag.class.getSimpleName() + "[", "]")
                .add("tagIdList=" + tagIdList)
                .toString();
    }
}
