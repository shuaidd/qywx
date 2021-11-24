package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 09:29
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class SpRecordDetail {

    @XmlElement(name = "Approver")
    private XmlUserId approver;

    @XmlElement(name = "Speech")
    private String speech;

    @XmlElement(name = "SpStatus")
    private Integer spStatus;

    @XmlElement(name = "SpTime")
    private Long spTime;

    @XmlElement(name = "Attach")
    private String attach;

    public XmlUserId getApprover() {
        return approver;
    }

    public void setApprover(XmlUserId approver) {
        this.approver = approver;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    public Integer getSpStatus() {
        return spStatus;
    }

    public void setSpStatus(Integer spStatus) {
        this.spStatus = spStatus;
    }

    public Long getSpTime() {
        return spTime;
    }

    public void setSpTime(Long spTime) {
        this.spTime = spTime;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
