package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 健康上报单题答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class HealthReportAnswerValue {

    /**
     * 问题的id
     */
    @JsonProperty("question_id")
    private Integer questionId;

    /**
     * 单选题答案编号
     */
    @JsonProperty("single_choice")
    private Integer singleChoice;

    /**
     * 填空题答案内容
     */
    @JsonProperty("text")
    private String text;

    /**
     * 多选题答案编号列表
     */
    @JsonProperty("multi_choice")
    private List<Integer> multiChoice;

    /**
     * 文件id列表，可以用下载文件接口下载
     */
    @JsonProperty("fileid")
    private List<String> fileid;

    /**
     * 签名的url，仅签名类型的问题返回
     */
    @JsonProperty("url")
    private String url;

    /**
     * 行程卡类型。0表示行程码状态识别失败，1表示绿码，2表示红码，3表示黄码，4表示橙码，5表示未识别出行程卡
     */
    @JsonProperty("itinerary_card_type")
    private Integer itineraryCardType;

    /**
     * 高风险行程信息，仅行程卡类型的问题返回
     */
    @JsonProperty("high_risk_area")
    private String highRiskArea;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getSingleChoice() {
        return singleChoice;
    }

    public void setSingleChoice(Integer singleChoice) {
        this.singleChoice = singleChoice;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Integer> getMultiChoice() {
        return multiChoice;
    }

    public void setMultiChoice(List<Integer> multiChoice) {
        this.multiChoice = multiChoice;
    }

    public List<String> getFileid() {
        return fileid;
    }

    public void setFileid(List<String> fileid) {
        this.fileid = fileid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getItineraryCardType() {
        return itineraryCardType;
    }

    public void setItineraryCardType(Integer itineraryCardType) {
        this.itineraryCardType = itineraryCardType;
    }

    public String getHighRiskArea() {
        return highRiskArea;
    }

    public void setHighRiskArea(String highRiskArea) {
        this.highRiskArea = highRiskArea;
    }
}
