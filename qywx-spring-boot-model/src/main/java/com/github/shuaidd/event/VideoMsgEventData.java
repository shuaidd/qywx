package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 视频消息
 *
 * @author ddshuai
 * @date 2021-07-14 10:33
 **/
@XmlRootElement(name = "xml")
public class VideoMsgEventData extends BaseEventData {

    @XmlElement(name = "ThumbMediaId")
    private String thumbMediaId;

    @XmlElement(name = "MediaId")
    private String mediaId;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
