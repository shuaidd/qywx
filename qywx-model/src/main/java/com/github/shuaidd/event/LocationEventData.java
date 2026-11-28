package com.github.shuaidd.event;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * 描述 上报地理位置
 *
 * @author ddshuai
 * date 2021-07-14 11:01
 **/
@XmlRootElement(name = "xml")
public class LocationEventData extends BaseEventData {

    @XmlElement(name = "Latitude")
    private String latitude;

    @XmlElement(name = "Longitude")
    private String longitude;

    @XmlElement(name = "Precision")
    private String precision;

    @XmlElement(name = "AppType")
    private String appType;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
