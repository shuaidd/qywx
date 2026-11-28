package com.github.shuaidd.dto.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 19:06
 **/
public class ChatInfo {

    private String name;
    private String owner;

    @JsonProperty("chatid")
    private String chatId;

    @JsonProperty("userlist")
    private List<String> userList;

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

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatInfo.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("owner='" + owner + "'")
                .add("chatId='" + chatId + "'")
                .add("userList=" + userList)
                .toString();
    }
}
