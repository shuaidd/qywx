package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-19 09:25
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class Applyer {

    @XmlElement(name = "UserId")
    private String userId;

    @XmlElement(name = "Party")
    private Integer party;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getParty() {
        return party;
    }

    public void setParty(Integer party) {
        this.party = party;
    }
}
