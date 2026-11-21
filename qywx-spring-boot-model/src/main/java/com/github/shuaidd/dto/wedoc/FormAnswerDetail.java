package com.github.shuaidd.dto.wedoc;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;

/**
 * 收集表问题答案
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class FormAnswerDetail {

    /**
     * 问题id
     */
    @JsonProperty("question_id")
    private Long questionId;

    /**
     * 文本类答案
     */
    @JsonProperty("text_reply")
    private String textReply;

    /**
     * 选择题答案id列表，多选题有多个答案
     */
    @JsonProperty("option_reply")
    private List<Integer> optionReply;

    /**
     * 选择题其他选项答案列表
     */
    @JsonProperty("option_extend_reply")
    private List<FormOptionExtendReply> optionExtendReply;

    /**
     * 文件题答案列表
     */
    @JsonProperty("file_extend_reply")
    private List<FormFileReply> fileExtendReply;

    /**
     * 部门题答案
     */
    @JsonProperty("department_reply")
    private FormDepartmentReply departmentReply;

    /**
     * 成员题答案
     */
    @JsonProperty("member_reply")
    private FormMemberReply memberReply;

    /**
     * 时长题答案
     */
    @JsonProperty("duration_reply")
    private FormDurationReply durationReply;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTextReply() {
        return textReply;
    }

    public void setTextReply(String textReply) {
        this.textReply = textReply;
    }

    public List<Integer> getOptionReply() {
        return optionReply;
    }

    public void setOptionReply(List<Integer> optionReply) {
        this.optionReply = optionReply;
    }

    public List<FormOptionExtendReply> getOptionExtendReply() {
        return optionExtendReply;
    }

    public void setOptionExtendReply(List<FormOptionExtendReply> optionExtendReply) {
        this.optionExtendReply = optionExtendReply;
    }

    public List<FormFileReply> getFileExtendReply() {
        return fileExtendReply;
    }

    public void setFileExtendReply(List<FormFileReply> fileExtendReply) {
        this.fileExtendReply = fileExtendReply;
    }

    public FormDepartmentReply getDepartmentReply() {
        return departmentReply;
    }

    public void setDepartmentReply(FormDepartmentReply departmentReply) {
        this.departmentReply = departmentReply;
    }

    public FormMemberReply getMemberReply() {
        return memberReply;
    }

    public void setMemberReply(FormMemberReply memberReply) {
        this.memberReply = memberReply;
    }

    public FormDurationReply getDurationReply() {
        return durationReply;
    }

    public void setDurationReply(FormDurationReply durationReply) {
        this.durationReply = durationReply;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("questionId", questionId)
                .append("textReply", textReply)
                .append("optionReply", optionReply)
                .append("optionExtendReply", optionExtendReply)
                .append("fileExtendReply", fileExtendReply)
                .append("departmentReply", departmentReply)
                .append("memberReply", memberReply)
                .append("durationReply", durationReply)
                .toString();
    }
}
