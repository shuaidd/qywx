package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 已服务的外部联系人信息
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class ServedContact {

    /**
     * 是否被成员标记为客户；true-是客户，false-其他外部联系人
     */
    @JsonProperty("is_customer")
    private Boolean isCustomer;

    /**
     * 外部联系人临时ID，有效期为4小时，可用于关联同一外部联系人的多次数据
     */
    @JsonProperty("tmp_openid")
    private String tmpOpenid;

    /**
     * 外部联系人的external_userid，仅当该联系人是客户时返回
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    /**
     * 脱敏后的外部联系人昵称，仅当该联系人是其他外部联系人时返回
     */
    private String name;

    /**
     * 添加此外部联系人的企业成员userid；外部联系人来自群聊时为该群聊群主的userid
     */
    @JsonProperty("follow_userid")
    private String followUserId;

    /**
     * 外部联系人所在的群聊ID，仅当群聊被成员标记为客户群时返回
     */
    @JsonProperty("chat_id")
    private String chatId;

    /**
     * 外部联系人所在群聊的群名，仅当群聊未被成员标记为客户群时返回
     */
    @JsonProperty("chat_name")
    private String chatName;

    /**
     * 外部联系人首次添加/进群的时间戳（秒）
     */
    @JsonProperty("add_time")
    private Long addTime;

    public Boolean getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(Boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    public String getTmpOpenid() {
        return tmpOpenid;
    }

    public void setTmpOpenid(String tmpOpenid) {
        this.tmpOpenid = tmpOpenid;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowUserId() {
        return followUserId;
    }

    public void setFollowUserId(String followUserId) {
        this.followUserId = followUserId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}
