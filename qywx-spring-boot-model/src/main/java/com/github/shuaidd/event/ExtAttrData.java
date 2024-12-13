package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 15:56
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtAttrData {

    @XmlElement(name = "Item")
    private List<ExtAttrItemData> itemData;

    public List<ExtAttrItemData> getItemData() {
        return itemData;
    }

    public void setItemData(List<ExtAttrItemData> itemData) {
        this.itemData = itemData;
    }
}
