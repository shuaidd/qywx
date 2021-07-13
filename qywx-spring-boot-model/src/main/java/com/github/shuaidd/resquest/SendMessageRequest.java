package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.*;
import com.github.shuaidd.enums.MsgType;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-08 17:13
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

    private MsgMarkdown markdown;

    @JsonProperty("miniprogram_notice")
    private MsgMiniprogramNotice miniprogramnotice;

    @JsonProperty("interactive_taskcard")
    private InteractiveTaskCard interactiveTaskCard;

    private Integer safe;

    /**
     * 表示是否开启id转译，0表示否，1表示是，默认0
     */
    @JsonProperty("enable_id_trans")
    private Integer enableIdTrans;

    /**
     * 表示是否开启重复消息检查，0表示否，1表示是，默认0
     */
    @JsonProperty("enable_duplicate_check")
    private Integer enableDuplicateCheck;

    /**
     * 表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
     */
    @JsonProperty("duplicate_check_interval")
    private Integer duplicateCheckInterval;

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

    public Integer getSafe() {
        return safe;
    }

    public void setSafe(Integer safe) {
        this.safe = safe;
    }

    public Integer getEnableIdTrans() {
        return enableIdTrans;
    }

    public void setEnableIdTrans(Integer enableIdTrans) {
        this.enableIdTrans = enableIdTrans;
    }

    public Integer getEnableDuplicateCheck() {
        return enableDuplicateCheck;
    }

    public void setEnableDuplicateCheck(Integer enableDuplicateCheck) {
        this.enableDuplicateCheck = enableDuplicateCheck;
    }

    public Integer getDuplicateCheckInterval() {
        return duplicateCheckInterval;
    }

    public void setDuplicateCheckInterval(Integer duplicateCheckInterval) {
        this.duplicateCheckInterval = duplicateCheckInterval;
    }

    public InteractiveTaskCard getInteractiveTaskCard() {
        return interactiveTaskCard;
    }

    public void setInteractiveTaskCard(InteractiveTaskCard interactiveTaskCard) {
        this.interactiveTaskCard = interactiveTaskCard;
    }
}
