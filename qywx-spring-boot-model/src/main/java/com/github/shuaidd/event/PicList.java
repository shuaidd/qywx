package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 11:22
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class PicList {

    @XmlElement(name = "item")
    private List<PicListItem> picListItems;

    public List<PicListItem> getPicListItems() {
        return picListItems;
    }

    public void setPicListItems(List<PicListItem> picListItems) {
        this.picListItems = picListItems;
    }
}
