package com.github.shuaidd.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-07-13 16:37
 **/
@XmlRootElement(name = "xml")
public class CommonEventData extends BaseEventData {

    @XmlElement(name = "Encrypt")
    private String encrypt;

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }
}
