package com.github.shuaidd.resquest.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.*;
import com.github.shuaidd.enums.MsgType;


import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 19:14
 **/
public class SendAppChatRequest {

    @JsonProperty("msgtype")
    private MsgType msgType;

    @JsonProperty("chatid")
    private String chatId;

    private Integer safe;

    private MsgText text;

    private MsgImage image;

    private MsgVoice voice;

    private MsgVideo video;

    private MsgFile file;

    private MsgTextCard textcard;

    private MsgNews news;

    private MsgMpNews mpnews;

    private MsgMarkdown markdown;

    @JsonProperty("miniprogram_notice")
    private MsgMiniprogramNotice miniprogramnotice;

    public MsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
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

    public MsgMarkdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(MsgMarkdown markdown) {
        this.markdown = markdown;
    }

    public MsgMiniprogramNotice getMiniprogramnotice() {
        return miniprogramnotice;
    }

    public void setMiniprogramnotice(MsgMiniprogramNotice miniprogramnotice) {
        this.miniprogramnotice = miniprogramnotice;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SendAppChatRequest.class.getSimpleName() + "[", "]")
                .add("msgType=" + msgType)
                .add("chatId='" + chatId + "'")
                .add("safe=" + safe)
                .add("text=" + text)
                .add("image=" + image)
                .add("voice=" + voice)
                .add("video=" + video)
                .add("file=" + file)
                .add("textcard=" + textcard)
                .add("news=" + news)
                .add("mpnews=" + mpnews)
                .add("markdown=" + markdown)
                .add("miniprogramnotice=" + miniprogramnotice)
                .toString();
    }
}
