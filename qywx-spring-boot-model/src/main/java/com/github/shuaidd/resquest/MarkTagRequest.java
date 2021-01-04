package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-04 21:35
 **/
public class MarkTagRequest {
    @JsonProperty("userid")
    private String userId;
    @JsonProperty("external_userid")
    private String externalUserId;
    @JsonProperty("add_tag")
    private List<String> addTags;
    @JsonProperty("remove_tag")
    private List<String> removeTags;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public List<String> getAddTags() {
        return addTags;
    }

    public void setAddTags(List<String> addTags) {
        this.addTags = addTags;
    }

    public List<String> getRemoveTags() {
        return removeTags;
    }

    public void setRemoveTags(List<String> removeTags) {
        this.removeTags = removeTags;
    }
}
