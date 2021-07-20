package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述 图片消息
 *
 * @author ddshuai
 * date 2021-07-14 10:33
 **/
@XmlRootElement(name = "xml")
public class LocationMsgEventData extends BaseEventData {

    @XmlElement(name = "Location_X")
    private String locationX;

    @XmlElement(name = "Location_Y")
    private String locationY;

    @XmlElement(name = "Scale")
    private String scale;

    @XmlElement(name = "Label")
    private String label;

    @XmlElement(name = "AppType")
    private String appType;

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
