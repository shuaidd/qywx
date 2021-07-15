package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 13:23
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalNodes {

    @XmlElement(name = "ApprovalNode")
    private List<ApprovalNode> approvalNodes;

    public List<ApprovalNode> getApprovalNodes() {
        return approvalNodes;
    }

    public void setApprovalNodes(List<ApprovalNode> approvalNodes) {
        this.approvalNodes = approvalNodes;
    }
}
