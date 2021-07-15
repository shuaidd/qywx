package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 * @author ddshuai
 * date 2021-07-14 13:26
 **/

@XmlAccessorType(XmlAccessType.FIELD)
public class ApprovalNodeItem {

    @XmlElement(name = "ItemName")
    private String itemName;

    @XmlElement(name = "ItemUserId")
    private String itemUserId;

    @XmlElement(name = "ItemImage")
    private String itemImage;

    @XmlElement(name = "ItemStatus")
    private String itemStatus;

    @XmlElement(name = "ItemSpeech")
    private String itemSpeech;

    @XmlElement(name = "ItemOpTime")
    private String itemOpTime;

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

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemSpeech() {
        return itemSpeech;
    }

    public void setItemSpeech(String itemSpeech) {
        this.itemSpeech = itemSpeech;
    }

    public String getItemOpTime() {
        return itemOpTime;
    }

    public void setItemOpTime(String itemOpTime) {
        this.itemOpTime = itemOpTime;
    }
}
