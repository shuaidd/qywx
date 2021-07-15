package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 11:33
 **/
@XmlRootElement(name = "xml")
public class LocationSelectEventData extends BaseEventData {

    @XmlElement(name = "SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }
}
