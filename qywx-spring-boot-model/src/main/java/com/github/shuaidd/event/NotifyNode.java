package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 13:41
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class NotifyNode {

    @XmlElement(name = "ItemName")
    private String itemName;

    @XmlElement(name = "ItemUserId")
    private String itemUserId;

    @XmlElement(name = "ItemImage")
    private String itemImage;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemUserId() {
        return itemUserId;
    }

    public void setItemUserId(String itemUserId) {
        this.itemUserId = itemUserId;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
