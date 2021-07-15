package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 语音消息
 *
 * @author ddshuai
 * date 2021-07-14 10:33
 **/
@XmlRootElement(name = "xml")
public class VoiceMsgEventData extends BaseEventData {

    @XmlElement(name = "Format")
    private String format;

    @XmlElement(name = "MediaId")
    private String mediaId;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
