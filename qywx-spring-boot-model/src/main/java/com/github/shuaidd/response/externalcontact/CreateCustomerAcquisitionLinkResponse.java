package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionLink;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建获客链接响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CreateCustomerAcquisitionLinkResponse extends AbstractBaseResponse {

    /**
     * 新创建的获客链接详情
     */
    @JsonProperty("link")
    private CustomerAcquisitionLink link;

    public CustomerAcquisitionLink getLink() {
        return link;
    }

    public void setLink(CustomerAcquisitionLink link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("link", link)
                .toString();
    }
}