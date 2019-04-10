package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.*;
import com.github.shuaidd.enums.MsgType;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2019-04-08 17:13
 **/
public class SendMessageRequest {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("toparty")
    private String toParty;

    @JsonProperty("totag")
    private String toTag;

    @JsonProperty("msgtype")
    private MsgType msgType;

    @JsonProperty("agentid")
    private String agentId;

    private MsgText text;

    private MsgImage image;

    private MsgVoice voice;

    private MsgVideo video;

    private MsgFile file;

    private MsgTextCard textcard;

    private MsgNews news;

    private MsgMpNews mpnews;

    @JsonProperty("miniprogram_notice")
    private MsgMiniprogramNotice miniprogramnotice;

    private Integer safe;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public MsgText getText() {
        return text;
    }

    public void setText(MsgText text) {
        this.text = text;
    }

    public MsgImage getImage() {
        return image;
    }

    public void setImage(MsgImage image) {
        this.image = image;
    }

    public MsgVoice getVoice() {
        return voice;
    }

    public void setVoice(MsgVoice voice) {
        this.voice = voice;
    }

    public MsgVideo getVideo() {
        return video;
    }

    public void setVideo(MsgVideo video) {
        this.video = video;
    }

    public MsgFile getFile() {
        return file;
    }

    public void setFile(MsgFile file) {
        this.file = file;
    }

    public MsgTextCard getTextcard() {
        return textcard;
    }

    public void setTextcard(MsgTextCard textcard) {
        this.textcard = textcard;
    }

    public MsgNews getNews() {
        return news;
    }

    public void setNews(MsgNews news) {
        this.news = news;
    }

    public MsgMpNews getMpnews() {
        return mpnews;
    }

    public void setMpnews(MsgMpNews mpnews) {
        this.mpnews = mpnews;
    }

    public MsgMiniprogramNotice getMiniprogramnotice() {
        return miniprogramnotice;
    }

    public void setMiniprogramnotice(MsgMiniprogramNotice miniprogramnotice) {
        this.miniprogramnotice = miniprogramnotice;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }
}
