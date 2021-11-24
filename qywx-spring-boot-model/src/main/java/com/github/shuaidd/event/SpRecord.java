package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 09:27
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class SpRecord {

    @XmlElement(name = "SpStatus")
    private Integer spStatus;

    /**
     * 节点审批方式：1-或签；2-会签
     */
    @XmlElement(name = "ApproverAttr")
    private Integer approverAttr;

    @XmlElement(name = "Details")
    private List<SpRecordDetail> details;

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public Integer getApproverAttr() {
        return approverAttr;
    }

    public void setApproverAttr(Integer approverAttr) {
        this.approverAttr = approverAttr;
    }

    public List<SpRecordDetail> getDetails() {
        return details;
    }

    public void setDetails(List<SpRecordDetail> details) {
        this.details = details;
    }
}
