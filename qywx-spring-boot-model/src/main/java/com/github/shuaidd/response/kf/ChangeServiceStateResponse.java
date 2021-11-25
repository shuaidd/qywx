package com.github.shuaidd.response.kf;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-11-25 09:55
 **/
public class ChangeServiceStateResponse extends AbstractBaseResponse {

    @JsonProperty("msg_code")
    private String msgCode;

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
