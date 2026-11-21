package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;
import java.util.Map;

/**
 * 收集表问题
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormQuestionItem {

    /**
     * 问题id，从1开始；如果是家校范围收集表，id从2开始
     */
    @JsonProperty("question_id")
    private Integer questionId;

    /**
     * 问题描述
     */
    @JsonProperty("title")
    private String title;

    /**
     * 问题序号，从1开始
     */
    @JsonProperty("pos")
    private Integer pos;

    /**
     * 问题状态；1-正常，2-被删除
     */
    @JsonProperty("status")
    private Integer status;

    /**
     * 问题类型；1-文本，2-单选，3-多选，5-位置，9-图片，10-文件，11-日期，14-时间，15-下拉列表，16-体温，17-签名，18-部门，19-成员，22-时长
     */
    @JsonProperty("reply_type")
    private Integer replyType;

    /**
     * 是否必答
     */
    @JsonProperty("must_reply")
    private Boolean mustReply;

    /**
     * 问题备注
     */
    @JsonProperty("note")
    private String note;

    /**
     * 输入提示文案
     */
    @JsonProperty("placeholder")
    private String placeholder;

    /**
     * 单选、多选或下拉列表题的选项列表
     */
    @JsonProperty("option_item")
    private List<FormOptionItem> optionItem;

    /**
     * 问题的额外设置，不同问题类型结构不同，按官方设置结构透传
     */
    @JsonProperty("question_extend_setting")
    private Map<String, Object> questionExtendSetting;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReplyType() {
        return replyType;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Boolean getMustReply() {
        return mustReply;
    }

    public void setMustReply(Boolean mustReply) {
        this.mustReply = mustReply;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public List<FormOptionItem> getOptionItem() {
        return optionItem;
    }

    public void setOptionItem(List<FormOptionItem> optionItem) {
        this.optionItem = optionItem;
    }

    public Map<String, Object> getQuestionExtendSetting() {
        return questionExtendSetting;
    }

    public void setQuestionExtendSetting(Map<String, Object> questionExtendSetting) {
        this.questionExtendSetting = questionExtendSetting;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("questionId", questionId)
                .append("title", title)
                .append("pos", pos)
                .append("status", status)
                .append("replyType", replyType)
                .append("mustReply", mustReply)
                .append("note", note)
                .append("placeholder", placeholder)
                .append("optionItem", optionItem)
                .append("questionExtendSetting", questionExtendSetting)
                .toString();
    }
}
