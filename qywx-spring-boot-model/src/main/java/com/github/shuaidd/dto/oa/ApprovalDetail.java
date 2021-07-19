package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-19 10:02
 **/
public class ApprovalDetail {

    @JsonProperty("sp_no")
    private String spNo;

    @JsonProperty("sp_name")
    private String spName;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty("sp_status")
    private Integer spStatus ;

    @JsonProperty("apply_time")
    private Long applyTime;

    private Applyer applyer;

    private List<UserId> notifyer;

    @JsonProperty("sp_record")
    private List<SpRecord> spRecords;

    private List<ApprovalComment> comments;

    @JsonProperty("apply_data")
    private ApplyData applyData;

    public String getSpNo() {
        return spNo;
    }

    public void setSpNo(String spNo) {
        this.spNo = spNo;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public Long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Long applyTime) {
        this.applyTime = applyTime;
    }

    public Applyer getApplyer() {
        return applyer;
    }

    public void setApplyer(Applyer applyer) {
        this.applyer = applyer;
    }

    public List<UserId> getNotifyer() {
        return notifyer;
    }

    public void setNotifyer(List<UserId> notifyer) {
        this.notifyer = notifyer;
    }

    public List<SpRecord> getSpRecords() {
        return spRecords;
    }

    public void setSpRecords(List<SpRecord> spRecords) {
        this.spRecords = spRecords;
    }

    public List<ApprovalComment> getComments() {
        return comments;
    }

    public void setComments(List<ApprovalComment> comments) {
        this.comments = comments;
    }

    public ApplyData getApplyData() {
        return applyData;
    }

    public void setApplyData(ApplyData applyData) {
        this.applyData = applyData;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApprovalDetail.class.getSimpleName() + "[", "]")
                .add("spNo='" + spNo + "'")
                .add("spName='" + spName + "'")
                .add("templateId='" + templateId + "'")
                .add("spStatus=" + spStatus)
                .add("applyTime=" + applyTime)
                .add("applyer=" + applyer)
                .add("notifyer=" + notifyer)
                .add("spRecords=" + spRecords)
                .add("comments=" + comments)
                .add("applyData=" + applyData)
                .toString();
    }
}
