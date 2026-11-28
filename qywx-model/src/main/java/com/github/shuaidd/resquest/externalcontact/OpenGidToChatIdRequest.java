package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户群 opengid 转换请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class OpenGidToChatIdRequest {

    /**
     * 小程序或 JS-SDK 获取到的客户群开放 ID
     */
    @JsonProperty("opengid")
    private String opengid;

    public String getOpengid() {
        return opengid;
    }

    public void setOpengid(String opengid) {
        this.opengid = opengid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("opengid", opengid)
                .toString();
    }
}
