package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 10:07
 **/

@XmlRootElement(name = "xml")
public class ExternalTagEventData extends BaseEventData {

    @XmlElement(name = "Id")
    private Integer id;

    @XmlElement(name = "TagType")
    private String tagType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
