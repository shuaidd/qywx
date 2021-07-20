package com.github.shuaidd.resquest.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 18:28
 **/
public class UpdateAppChatRequest {

    private String name;
    private String owner;

    @JsonProperty("chatid")
    private String chatId;

    @JsonProperty("add_user_list")
    private List<String> addUserList;

    @JsonProperty("del_user_list")
    private List<String> delUserList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public List<String> getAddUserList() {
        return addUserList;
    }

    public void setAddUserList(List<String> addUserList) {
        this.addUserList = addUserList;
    }

    public List<String> getDelUserList() {
        return delUserList;
    }

    public void setDelUserList(List<String> delUserList) {
        this.delUserList = delUserList;
    }
}
