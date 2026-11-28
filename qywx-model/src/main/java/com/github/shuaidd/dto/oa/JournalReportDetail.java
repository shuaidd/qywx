package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-20 09:43
 **/
public class JournalReportDetail {

    @JsonProperty("journal_uuid")
    private String journalId;

    @JsonProperty("template_name")
    private String templateName;

    private UserId submitter;

    private List<UserId> receivers;

    @JsonProperty("readed_receivers")
    private List<UserId> readedReceivers;

    @JsonProperty("apply_data")
    private ApplyData applyData;

    private List<ApprovalComment> comments;

    public String getJournalId() {
        return journalId;
    }

    public void setJournalId(String journalId) {
        this.journalId = journalId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public UserId getSubmitter() {
        return submitter;
    }

    public void setSubmitter(UserId submitter) {
        this.submitter = submitter;
    }

    public List<UserId> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<UserId> receivers) {
        this.receivers = receivers;
    }

    public List<UserId> getReadedReceivers() {
        return readedReceivers;
    }

    public void setReadedReceivers(List<UserId> readedReceivers) {
        this.readedReceivers = readedReceivers;
    }

    public ApplyData getApplyData() {
        return applyData;
    }

    public void setApplyData(ApplyData applyData) {
        this.applyData = applyData;
    }

    public List<ApprovalComment> getComments() {
        return comments;
    }

    public void setComments(List<ApprovalComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportDetail.class.getSimpleName() + "[", "]")
                .add("journalId='" + journalId + "'")
                .add("templateName='" + templateName + "'")
                .add("submitter=" + submitter)
                .add("receivers=" + receivers)
                .add("readedReceivers=" + readedReceivers)
                .add("applyData=" + applyData)
                .add("comments=" + comments)
                .toString();
    }
}
