package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionLink;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionPriorityOption;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionRange;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获客链接详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionLinkResponse extends AbstractBaseResponse {

    /**
     * 获客链接基础信息
     */
    @JsonProperty("link")
    private CustomerAcquisitionLink link;

    /**
     * 获客链接可使用的成员和部门范围
     */
    @JsonProperty("range")
    private CustomerAcquisitionRange range;

    /**
     * 获客链接的成员分配优先级配置
     */
    @JsonProperty("priority_option")
    private CustomerAcquisitionPriorityOption priorityOption;

    /**
     * 通过链接添加客户时，是否免于企业成员验证
     */
    @JsonProperty("skip_verify")
    private Boolean skipVerify;

    public CustomerAcquisitionLink getLink() {
        return link;
    }

    public void setLink(CustomerAcquisitionLink link) {
        this.link = link;
    }

    public CustomerAcquisitionRange getRange() {
        return range;
    }

    public void setRange(CustomerAcquisitionRange range) {
        this.range = range;
    }

    public CustomerAcquisitionPriorityOption getPriorityOption() {
        return priorityOption;
    }

    public void setPriorityOption(CustomerAcquisitionPriorityOption priorityOption) {
        this.priorityOption = priorityOption;
    }

    public Boolean getSkipVerify() {
        return skipVerify;
    }

    public void setSkipVerify(Boolean skipVerify) {
        this.skipVerify = skipVerify;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("link", link)
                .append("range", range)
                .append("priorityOption", priorityOption)
                .append("skipVerify", skipVerify)
                .toString();
    }
}