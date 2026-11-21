package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 按链接 ID 操作获客链接请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionLinkIdRequest {

    /**
     * 获客链接 ID
     */
    @JsonProperty("link_id")
    private String linkId;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("linkId", linkId)
                .toString();
    }
}