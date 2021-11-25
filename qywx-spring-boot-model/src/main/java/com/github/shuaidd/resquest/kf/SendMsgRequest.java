package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 13:05
 **/
public class SendMsgRequest {
    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("open_kfid")
    private String openKfId;

    @JsonProperty("msgid")
    private String msgId;

    @JsonProperty("msgtype")
    private String msgType;

    private Map<String,String> text;
    private Map<String,String> image;
    private Map<String,String> voice;
    private Map<String,String> video;
    private Map<String,String> file;
    private Map<String,String> link;
    private Map<String,String> miniprogram;
    private Map<String,Object> msgmenu;
    private Map<String,Object> location;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }

    public Map<String, String> getVoice() {
        return voice;
    }

    public void setVoice(Map<String, String> voice) {
        this.voice = voice;
    }

    public Map<String, String> getVideo() {
        return video;
    }

    public void setVideo(Map<String, String> video) {
        this.video = video;
    }

    public Map<String, String> getFile() {
        return file;
    }

    public void setFile(Map<String, String> file) {
        this.file = file;
    }

    public Map<String, String> getLink() {
        return link;
    }

    public void setLink(Map<String, String> link) {
        this.link = link;
    }

    public Map<String, String> getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Map<String, String> miniprogram) {
        this.miniprogram = miniprogram;
    }

    public Map<String, Object> getMsgmenu() {
        return msgmenu;
    }

    public void setMsgmenu(Map<String, Object> msgmenu) {
        this.msgmenu = msgmenu;
    }

    public Map<String, Object> getLocation() {
        return location;
    }

    public void setLocation(Map<String, Object> location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SendMsgRequest.class.getSimpleName() + "[", "]")
                .add("toUser='" + toUser + "'")
                .add("open_kfid='" + openKfId + "'")
                .add("msgId='" + msgId + "'")
                .add("msgType='" + msgType + "'")
                .add("text=" + text)
                .add("image=" + image)
                .add("voice=" + voice)
                .add("video=" + video)
                .add("file=" + file)
                .add("link=" + link)
                .add("miniprogram=" + miniprogram)
                .add("msgmenu=" + msgmenu)
                .add("location=" + location)
                .toString();
    }
}
