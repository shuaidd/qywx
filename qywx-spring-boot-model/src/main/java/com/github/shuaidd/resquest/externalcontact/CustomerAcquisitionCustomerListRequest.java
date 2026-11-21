package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.resquest.CursorPageRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获取获客客户列表请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionCustomerListRequest extends CursorPageRequest {

    /**
     * 获客链接 ID；必须是当前应用创建的链接
     */
    @JsonProperty("link_id")
    private String linkId;

    public CustomerAcquisitionCustomerListRequest() {
        setLimit(1000);
    }

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