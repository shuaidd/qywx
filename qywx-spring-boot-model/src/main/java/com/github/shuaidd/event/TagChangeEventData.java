package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 17:43
 **/
@XmlRootElement(name = "xml")
public class TagChangeEventData extends BaseEventData {

    @XmlElement(name = "TagId")
    private Long tagId;

    @XmlElement(name = "AddUserItems")
    private String addUserItems;

    @XmlElement(name = "DelUserItems")
    private String delUserItems;

    @XmlElement(name = "AddPartyItems")
    private String addPartyItems;

    @XmlElement(name = "DelPartyItems")
    private String delPartyItems;

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getAddUserItems() {
        return addUserItems;
    }

    public void setAddUserItems(String addUserItems) {
        this.addUserItems = addUserItems;
    }

    public String getDelUserItems() {
        return delUserItems;
    }

    public void setDelUserItems(String delUserItems) {
        this.delUserItems = delUserItems;
    }

    public String getAddPartyItems() {
        return addPartyItems;
    }

    public void setAddPartyItems(String addPartyItems) {
        this.addPartyItems = addPartyItems;
    }

    public String getDelPartyItems() {
        return delPartyItems;
    }

    public void setDelPartyItems(String delPartyItems) {
        this.delPartyItems = delPartyItems;
    }
}
