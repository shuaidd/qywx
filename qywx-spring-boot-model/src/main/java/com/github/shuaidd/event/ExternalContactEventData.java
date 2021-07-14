package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 企业客户事件
 *
 * @author ddshuai
 * @date 2021-07-14 09:29
 **/
@XmlRootElement(name = "xml")
public class ExternalContactEventData extends BaseEventData {

    @XmlElement(name = "UserID")
    private String userId;

    @XmlElement(name = "ExternalUserID")
    private String externalUserId;

    @XmlElement(name = "State")
    private String state;

    @XmlElement(name = "WelcomeCode")
    private String welcomeCode;

    @XmlElement(name = "FailReason")
    private String failReason;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWelcomeCode() {
        return welcomeCode;
    }

    public void setWelcomeCode(String welcomeCode) {
        this.welcomeCode = welcomeCode;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
