package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 22:31
 **/
public class GroupChatTransferRequest {

    @JsonProperty("chat_id_list")
    private List<String> chatIdList;

    @JsonProperty("new_owner")
    private String newOwner;

    public List<String> getChatIdList() {
        return chatIdList;
    }

    public void setChatIdList(List<String> chatIdList) {
        this.chatIdList = chatIdList;
    }

    public String getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(String newOwner) {
        this.newOwner = newOwner;
    }
}
