package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 链接消息
 *
 * @author ddshuai
 * @date 2021-07-14 10:33
 **/
@XmlRootElement(name = "xml")
public class LinkMsgEventData extends BaseEventData {

    @XmlElement(name = "Title")
    private String title;

    @XmlElement(name = "Description")
    private String description;

     @XmlElement(name = "Url")
    private String url;

     @XmlElement(name = "PicUrl")
    private String picUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
