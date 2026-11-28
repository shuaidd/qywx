package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 16:36
 **/
@XmlRootElement(name = "xml")
public class DeleteUserEventData extends BaseEventData {

    @XmlElement(name = "UserID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
