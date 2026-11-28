package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.message.*;
import com.github.shuaidd.enums.MsgType;

import java.util.List;

/**
 * 发送学校通知请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class SendSchoolNotificationRequest {

    @JsonProperty("recv_scope")
    private Integer recvScope;

    @JsonProperty("to_parent_userid")
    private List<String> toParentUserIds;

    @JsonProperty("to_student_userid")
    private List<String> toStudentUserIds;

    @JsonProperty("to_party")
    private List<String> toParty;

    private Integer toall;

    private Integer agentid;

    @JsonProperty("msgtype")
    private MsgType msgType;

    private MsgText text;

    private MsgImage image;

    private MsgVoice voice;

    private MsgVideo video;

    private MsgFile file;

    private MsgNews news;

    private MsgMpNews mpnews;

    private SchoolMiniProgram miniprogram;

    @JsonProperty("enable_id_trans")
    private Integer enableIdTrans;

    @JsonProperty("enable_duplicate_check")
    private Integer enableDuplicateCheck;

    @JsonProperty("duplicate_check_interval")
    private Integer duplicateCheckInterval;

    public Integer getRecvScope() { return recvScope; }
    public void setRecvScope(Integer recvScope) { this.recvScope = recvScope; }
    public List<String> getToParentUserIds() { return toParentUserIds; }
    public void setToParentUserIds(List<String> toParentUserIds) { this.toParentUserIds = toParentUserIds; }
    public List<String> getToStudentUserIds() { return toStudentUserIds; }
    public void setToStudentUserIds(List<String> toStudentUserIds) { this.toStudentUserIds = toStudentUserIds; }
    public List<String> getToParty() { return toParty; }
    public void setToParty(List<String> toParty) { this.toParty = toParty; }
    public Integer getToall() { return toall; }
    public void setToall(Integer toall) { this.toall = toall; }
    public Integer getAgentid() { return agentid; }
    public void setAgentid(Integer agentid) { this.agentid = agentid; }
    public MsgType getMsgType() { return msgType; }
    public void setMsgType(MsgType msgType) { this.msgType = msgType; }
    public MsgText getText() { return text; }
    public void setText(MsgText text) { this.text = text; }
    public MsgImage getImage() { return image; }
    public void setImage(MsgImage image) { this.image = image; }
    public MsgVoice getVoice() { return voice; }
    public void setVoice(MsgVoice voice) { this.voice = voice; }
    public MsgVideo getVideo() { return video; }
    public void setVideo(MsgVideo video) { this.video = video; }
    public MsgFile getFile() { return file; }
    public void setFile(MsgFile file) { this.file = file; }
    public MsgNews getNews() { return news; }
    public void setNews(MsgNews news) { this.news = news; }
    public MsgMpNews getMpnews() { return mpnews; }
    public void setMpnews(MsgMpNews mpnews) { this.mpnews = mpnews; }
    public SchoolMiniProgram getMiniprogram() { return miniprogram; }
    public void setMiniprogram(SchoolMiniProgram miniprogram) { this.miniprogram = miniprogram; }
    public Integer getEnableIdTrans() { return enableIdTrans; }
    public void setEnableIdTrans(Integer enableIdTrans) { this.enableIdTrans = enableIdTrans; }
    public Integer getEnableDuplicateCheck() { return enableDuplicateCheck; }
    public void setEnableDuplicateCheck(Integer enableDuplicateCheck) { this.enableDuplicateCheck = enableDuplicateCheck; }
    public Integer getDuplicateCheckInterval() { return duplicateCheckInterval; }
    public void setDuplicateCheckInterval(Integer duplicateCheckInterval) { this.duplicateCheckInterval = duplicateCheckInterval; }
}