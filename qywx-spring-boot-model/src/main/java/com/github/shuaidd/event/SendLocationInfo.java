package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 11:34
 **/
@XmlAccessorType(XmlAccessType.FIELD)
public class SendLocationInfo {

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

    @XmlElement(name = "Poiname")
    private String poiName;

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

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }
}
