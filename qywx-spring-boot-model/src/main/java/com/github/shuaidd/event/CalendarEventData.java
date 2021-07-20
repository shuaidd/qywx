package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-14 14:10
 **/
@XmlRootElement(name = "xml")
public class CalendarEventData extends BaseEventData {

    @XmlElement(name = "CalId")
    private String calId;

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }
}
