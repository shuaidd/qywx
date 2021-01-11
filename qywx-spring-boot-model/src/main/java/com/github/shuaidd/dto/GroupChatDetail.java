package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 描述
 * "chat_id": "wrOgQhDgAAMYQiS5ol9G7gK9JVAAAA",
 * "name": "销售客服群",
 * "owner": "ZhuShengBen",
 * "create_time": 1572505490,
 * "notice" : "文明沟通，拒绝脏话",
 *
 * @author ddshuai
 * @date 2021-01-05 21:06
 **/
public class GroupChatDetail {

    @JsonProperty("chat_id")
    private String chatId;
    private String name;
    private String owner;
    private String notice;
    @JsonProperty("create_time")
    private Date createTime;
    @JsonProperty("member_list")
    private List<Member> members;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
