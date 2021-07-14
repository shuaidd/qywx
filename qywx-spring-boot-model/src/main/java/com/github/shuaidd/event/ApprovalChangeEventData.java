package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 13:17
 **/

@XmlRootElement(name = "xml")
public class ApprovalChangeEventData extends BaseEventData {

    @XmlElement(name = "ApprovalInfo")
    private ApprovalInfo approvalInfo;

    public ApprovalInfo getApprovalInfo() {
        return approvalInfo;
    }

    public void setApprovalInfo(ApprovalInfo approvalInfo) {
        this.approvalInfo = approvalInfo;
    }
}
