package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 14:10
 **/
@XmlRootElement(name = "xml")
public class ScheduleEventData extends BaseEventData {

    @XmlElement(name = "CalId")
    private String calId;

    @XmlElement(name = "ScheduleId")
    private String scheduleId;

    public String getCalId() {
        return calId;
    }

    public void setCalId(String calId) {
        this.calId = calId;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }
}
