package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 * @author ddshuai
 * date 2021-07-14 13:24
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalNode {

    @XmlElement(name = "NodeStatus")
    private String nodeStatus;

    @XmlElement(name = "NodeAttr")
    private String nodeAttr;

    @XmlElement(name = "NodeType")
    private String nodeType;

    @XmlElement(name = "Items")
    private ApprovalNodeItems approvalNodeItems;

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeAttr() {
        return nodeAttr;
    }

    public void setNodeAttr(String nodeAttr) {
        this.nodeAttr = nodeAttr;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public ApprovalNodeItems getApprovalNodeItems() {
        return approvalNodeItems;
    }

    public void setApprovalNodeItems(ApprovalNodeItems approvalNodeItems) {
        this.approvalNodeItems = approvalNodeItems;
    }
}
