package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-13 15:56
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class ExtAttrItemData {

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Type")
    private Integer type;

    @XmlElement(name = "Text")
    private ExtAttrItemTextData textData;

    @XmlElement(name = "Web")
    private ExtAttrItemWebData webData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ExtAttrItemTextData getTextData() {
        return textData;
    }

    public void setTextData(ExtAttrItemTextData textData) {
        this.textData = textData;
    }

    public ExtAttrItemWebData getWebData() {
        return webData;
    }

    public void setWebData(ExtAttrItemWebData webData) {
        this.webData = webData;
    }
}
