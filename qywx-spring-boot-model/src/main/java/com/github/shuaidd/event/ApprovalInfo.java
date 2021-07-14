package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 * @author ddshuai
 * @date 2021-07-14 13:18
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalInfo {

    @XmlElement(name = "ThirdNo")
    private String thirdNo;

    @XmlElement(name = "OpenSpName")
    private String openSpName;

    @XmlElement(name = "OpenTemplateId")
    private String openTemplateId;

    @XmlElement(name = "OpenSpStatus")
    private String openSpStatus;

    @XmlElement(name = "ApplyTime")
    private String applyTime;

    @XmlElement(name = "ApplyUserName")
    private String applyUserName;

    @XmlElement(name = "ApplyUserParty")
    private String applyUserParty;

    @XmlElement(name = "ApplyUserImage")
    private String applyUserImage;

    @XmlElement(name = "ApplyUserId")
    private String applyUserId;

    @XmlElement(name = "approverstep")
    private Integer approverStep;

    @XmlElement(name = "ApprovalNodes")
    private ApprovalNodes approvalNodes;

    @XmlElement(name = "NotifyNodes")
    private NotifyNodes notifyNodes;

    public String getThirdNo() {
        return thirdNo;
    }

    public void setThirdNo(String thirdNo) {
        this.thirdNo = thirdNo;
    }

    public String getOpenSpName() {
        return openSpName;
    }

    public void setOpenSpName(String openSpName) {
        this.openSpName = openSpName;
    }

    public String getOpenTemplateId() {
        return openTemplateId;
    }

    public void setOpenTemplateId(String openTemplateId) {
        this.openTemplateId = openTemplateId;
    }

    public String getOpenSpStatus() {
        return openSpStatus;
    }

    public void setOpenSpStatus(String openSpStatus) {
        this.openSpStatus = openSpStatus;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserParty() {
        return applyUserParty;
    }

    public void setApplyUserParty(String applyUserParty) {
        this.applyUserParty = applyUserParty;
    }

    public String getApplyUserImage() {
        return applyUserImage;
    }

    public void setApplyUserImage(String applyUserImage) {
        this.applyUserImage = applyUserImage;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Integer getApproverStep() {
        return approverStep;
    }

    public void setApproverStep(Integer approverStep) {
        this.approverStep = approverStep;
    }

    public ApprovalNodes getApprovalNodes() {
        return approvalNodes;
    }

    public void setApprovalNodes(ApprovalNodes approvalNodes) {
        this.approvalNodes = approvalNodes;
    }

    public NotifyNodes getNotifyNodes() {
        return notifyNodes;
    }

    public void setNotifyNodes(NotifyNodes notifyNodes) {
        this.notifyNodes = notifyNodes;
    }
}
