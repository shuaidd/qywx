package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-07-14 11:11
 **/
@XmlRootElement(name = "xml")
public class ScanCodeEventData extends BaseEventData {

    @XmlElement(name = "ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;

    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }
}
