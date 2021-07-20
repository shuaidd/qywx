package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 13:28
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalNodeItems {

    @XmlElement(name = "Item")
    private List<ApprovalNodeItem> approvalNodeItems;

    public List<ApprovalNodeItem> getApprovalNodeItems() {
        return approvalNodeItems;
    }

    public void setApprovalNodeItems(List<ApprovalNodeItem> approvalNodeItems) {
        this.approvalNodeItems = approvalNodeItems;
    }
}
