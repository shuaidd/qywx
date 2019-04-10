package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.WeChatUser;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-06 07:50
 **/
public class QueryTagUserResponse extends AbstractBaseResponse {

    @JsonProperty("tagname")
    private String tagName;

    @JsonProperty("partylist")
    private List<Integer> partyList;

    @JsonProperty("userlist")
    private List<WeChatUser> userList;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public List<Integer> getPartyList() {
        return partyList;
    }

    public void setPartyList(List<Integer> partyList) {
        this.partyList = partyList;
    }

    public List<WeChatUser> getUserList() {
        return userList;
    }

    public void setUserList(List<WeChatUser> userList) {
        this.userList = userList;
    }
}
