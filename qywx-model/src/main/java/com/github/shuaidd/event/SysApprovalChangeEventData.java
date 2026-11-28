package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 描述 审批申请状态变化回调通知
 *
 * @author ddshuai
 * date 2021-07-19 09:09
 **/
@XmlRootElement(name = "xml")
public class SysApprovalChangeEventData extends BaseEventData {

    @XmlElement(name = "ApprovalInfo")
    private ApprovalChangeInfo approvalChangeInfo;

    public ApprovalChangeInfo getApprovalChangeInfo() {
        return approvalChangeInfo;
    }

    public void setApprovalChangeInfo(ApprovalChangeInfo approvalChangeInfo) {
        this.approvalChangeInfo = approvalChangeInfo;
    }
}
