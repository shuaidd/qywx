package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 描述
 * @author ddshuai
 * date 2021-11-25 11:23
 **/
public class SyncMsgResponse extends AbstractBaseResponse {

    @JsonProperty("msg_list")
    private List<MsgDetail> msgList;

    public static class MsgDetail {
        @JsonProperty("msgid")
        private String msgId;

        @JsonProperty("open_kfid")
        private String openKfId;

        @JsonProperty("external_userid")
        private String externalUserId;

        @JsonProperty("send_time")
        private String sendTime;

        private Integer origin;

        @JsonProperty("servicer_userid")
        private String servicerUserId;

        @JsonProperty("msgtype")
        private String msgType;

        private Map<String,Object> text;
        private Map<String,Object> image;
        private Map<String,Object> voice;
        private Map<String,Object> video;
        private Map<String,Object> file;
        private Map<String,Object> location;
        private Map<String,Object> link;
        @JsonProperty("business_card")
        private Map<String,Object> businessCard;
        private Map<String,Object> miniprogram;
        private Map<String,Object> msgmenu;
        private Map<String,Object> event;

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
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

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public Integer getOrigin() {
            return origin;
        }

        public void setOrigin(Integer origin) {
            this.origin = origin;
        }

        public String getServicerUserId() {
            return servicerUserId;
        }

        public void setServicerUserId(String servicerUserId) {
            this.servicerUserId = servicerUserId;
        }

        public String getMsgType() {
            return msgType;
        }

        public void setMsgType(String msgType) {
            this.msgType = msgType;
        }

        public Map<String, Object> getText() {
            return text;
        }

        public void setText(Map<String, Object> text) {
            this.text = text;
        }

        public Map<String, Object> getImage() {
            return image;
        }

        public void setImage(Map<String, Object> image) {
            this.image = image;
        }

        public Map<String, Object> getVoice() {
            return voice;
        }

        public void setVoice(Map<String, Object> voice) {
            this.voice = voice;
        }

        public Map<String, Object> getVideo() {
            return video;
        }

        public void setVideo(Map<String, Object> video) {
            this.video = video;
        }

        public Map<String, Object> getFile() {
            return file;
        }

        public void setFile(Map<String, Object> file) {
            this.file = file;
        }

        public Map<String, Object> getLocation() {
            return location;
        }

        public void setLocation(Map<String, Object> location) {
            this.location = location;
        }

        public Map<String, Object> getLink() {
            return link;
        }

        public void setLink(Map<String, Object> link) {
            this.link = link;
        }

        public Map<String, Object> getBusinessCard() {
            return businessCard;
        }

        public void setBusinessCard(Map<String, Object> businessCard) {
            this.businessCard = businessCard;
        }

        public Map<String, Object> getMiniprogram() {
            return miniprogram;
        }

        public void setMiniprogram(Map<String, Object> miniprogram) {
            this.miniprogram = miniprogram;
        }

        public Map<String, Object> getMsgmenu() {
            return msgmenu;
        }

        public void setMsgmenu(Map<String, Object> msgmenu) {
            this.msgmenu = msgmenu;
        }

        public Map<String, Object> getEvent() {
            return event;
        }

        public void setEvent(Map<String, Object> event) {
            this.event = event;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", MsgDetail.class.getSimpleName() + "[", "]")
                    .add("msgId='" + msgId + "'")
                    .add("openKfId='" + openKfId + "'")
                    .add("externalUserId='" + externalUserId + "'")
                    .add("sendTime='" + sendTime + "'")
                    .add("origin=" + origin)
                    .add("servicerUserId='" + servicerUserId + "'")
                    .add("msgType='" + msgType + "'")
                    .add("text=" + text)
                    .add("image=" + image)
                    .add("voice=" + voice)
                    .add("video=" + video)
                    .add("file=" + file)
                    .add("location=" + location)
                    .add("link=" + link)
                    .add("businessCard=" + businessCard)
                    .add("miniprogram=" + miniprogram)
                    .add("msgmenu=" + msgmenu)
                    .add("event=" + event)
                    .toString();
        }
    }

    public List<MsgDetail> getMsgList() {
        return msgList;
    }

    public void setMsgList(List<MsgDetail> msgList) {
        this.msgList = msgList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SyncMsgResponse.class.getSimpleName() + "[", "]")
                .add("msgList=" + msgList)
                .toString();
    }
}
