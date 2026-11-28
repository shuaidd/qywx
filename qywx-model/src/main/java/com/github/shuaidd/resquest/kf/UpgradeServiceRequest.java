package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * @author ddshuai
 * date 2022-08-31 13:34
 **/
public class UpgradeServiceRequest {

    /**
     * 客服帐号ID
     */
    @JsonProperty("open_kfid")
    private String openKfId;

    /**
     * 微信客户的external_userid
     */
    @JsonProperty("external_userid")
    private String externalUserId;

    /**
     * 表示是升级到专员服务还是客户群服务。1:专员服务。2:客户群服务
     */
    private Integer type;

    private Member member;

    @JsonProperty(value = "groupchat")
    private GroupChat groupChat;

    public static class Member {

        /**
         * 服务专员的userid
         */
        @JsonProperty(value = "userid")
        private String userId;

        /**
         * 推荐语
         */
        private String wording;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getWording() {
            return wording;
        }

        public void setWording(String wording) {
            this.wording = wording;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Member.class.getSimpleName() + "[", "]")
                    .add("userId='" + userId + "'")
                    .add("wording='" + wording + "'")
                    .toString();
        }
    }

    public static class GroupChat {

        /**
         * 客户群id
         */
        @JsonProperty(value = "chat_id")
        private String chatId;

        /**
         * 推荐语
         */
        private String wording;

        public String getChatId() {
            return chatId;
        }

        public void setChatId(String chatId) {
            this.chatId = chatId;
        }

        public String getWording() {
            return wording;
        }

        public void setWording(String wording) {
            this.wording = wording;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", GroupChat.class.getSimpleName() + "[", "]")
                    .add("chatId='" + chatId + "'")
                    .add("wording='" + wording + "'")
                    .toString();
        }
    }

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }
}
