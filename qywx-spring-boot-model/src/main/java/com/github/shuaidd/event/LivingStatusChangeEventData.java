package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-15 09:04
 **/
@XmlRootElement(name = "xml")
public class LivingStatusChangeEventData extends BaseEventData {

    @XmlElement(name = "LivingId")
    private String livingId;

    @XmlElement(name = "Status")
    private Integer status;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
