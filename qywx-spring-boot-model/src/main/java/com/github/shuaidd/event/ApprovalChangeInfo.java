package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述 审批详情
 *
 * @author ddshuai
 * date 2021-07-19 09:08
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalChangeInfo {

    @XmlElement(name = "SpNo")
    private String spNo;

    @XmlElement(name = "SpName")
    private String spName;

    @XmlElement(name = "TemplateId")
    private String templateId;

    @XmlElement(name = "SpStatus")
    private Integer spStatus;

    @XmlElement(name = "ApplyTime")
    private String applyTime;

    /**
     * 审批申请状态变化类型：1-提单；2-同意；3-驳回；4-转审；5-催办；6-撤销；8-通过后撤销；10-添加备注
     */
    @XmlElement(name = "StatuChangeEvent")
    private String statusChangeEvent;

    @XmlElement(name = "Comments")
    private List<ApprovalComment> comments;

    @XmlElement(name = "Notifyer")
    private List<XmlUserId> notifyer;

    @XmlElement(name = "Applyer")
    private Applyer applyer;

    @XmlElement(name = "SpRecord")
    private List<SpRecord> records;

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

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatusChangeEvent() {
        return statusChangeEvent;
    }

    public void setStatusChangeEvent(String statusChangeEvent) {
        this.statusChangeEvent = statusChangeEvent;
    }

    public List<ApprovalComment> getComments() {
        return comments;
    }

    public void setComments(List<ApprovalComment> comments) {
        this.comments = comments;
    }

    public List<XmlUserId> getNotifyer() {
        return notifyer;
    }

    public void setNotifyer(List<XmlUserId> notifyer) {
        this.notifyer = notifyer;
    }

    public Applyer getApplyer() {
        return applyer;
    }

    public void setApplyer(Applyer applyer) {
        this.applyer = applyer;
    }

    public List<SpRecord> getRecords() {
        return records;
    }

    public void setRecords(List<SpRecord> records) {
        this.records = records;
    }
}
