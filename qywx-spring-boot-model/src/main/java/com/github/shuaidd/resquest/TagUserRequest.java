package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * author ddshuai
 * date 2019-04-06 07:56
 **/
public class TagUserRequest {

    @JsonProperty("tagid")
    private Integer tagId;

    @JsonProperty("partylist")
    private List<Integer> partyList;

    @JsonProperty("userlist")
    private List<String> userList;

    public List<Integer> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<Integer> partyList) {
        this.partyList = partyList;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
}
