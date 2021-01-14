package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-06 22:43
 **/
public class BehaviorData {
    @JsonProperty("stat_time")
    private Date statTime;

    @JsonProperty("chat_cnt")
    private Integer chatCnt;

    @JsonProperty("message_cnt")
    private Integer messageCnt;

    @JsonProperty("reply_percentage")
    private double replyPercentage;

    @JsonProperty("avg_reply_time")
    private Integer avgReplyTime;

    @JsonProperty("negative_feedback_cnt")
    private Integer negativeFeedbackCnt;

    @JsonProperty("new_apply_cnt")
    private Integer newApplyCnt;

    @JsonProperty("new_contact_cnt")
    private Integer newContactCnt;

    public Date getStatTime() {
        return statTime;
    }

    public void setStatTime(Date statTime) {
        this.statTime = statTime;
    }

    public Integer getChatCnt() {
        return chatCnt;
    }

    public void setChatCnt(Integer chatCnt) {
        this.chatCnt = chatCnt;
    }

    public Integer getMessageCnt() {
        return messageCnt;
    }

    public void setMessageCnt(Integer messageCnt) {
        this.messageCnt = messageCnt;
    }

    public double getReplyPercentage() {
        return replyPercentage;
    }

    public void setReplyPercentage(double replyPercentage) {
        this.replyPercentage = replyPercentage;
    }

    public Integer getAvgReplyTime() {
        return avgReplyTime;
    }

    public void setAvgReplyTime(Integer avgReplyTime) {
        this.avgReplyTime = avgReplyTime;
    }

    public Integer getNegativeFeedbackCnt() {
        return negativeFeedbackCnt;
    }

    public void setNegativeFeedbackCnt(Integer negativeFeedbackCnt) {
        this.negativeFeedbackCnt = negativeFeedbackCnt;
    }

    public Integer getNewApplyCnt() {
        return newApplyCnt;
    }

    public void setNewApplyCnt(Integer newApplyCnt) {
        this.newApplyCnt = newApplyCnt;
    }

    public Integer getNewContactCnt() {
        return newContactCnt;
    }

    public void setNewContactCnt(Integer newContactCnt) {
        this.newContactCnt = newContactCnt;
    }
}
