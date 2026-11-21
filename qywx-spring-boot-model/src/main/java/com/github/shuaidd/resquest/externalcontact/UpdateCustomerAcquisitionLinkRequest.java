package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionPriorityOption;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionRange;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 编辑获客链接请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class UpdateCustomerAcquisitionLinkRequest {

    /**
     * 待编辑的获客链接 ID；必填
     */
    @JsonProperty("link_id")
    private String linkId;

    /**
     * 新的获客链接名称；不传表示保持原值
     */
    @JsonProperty("link_name")
    private String linkName;

    /**
     * 新的使用范围；传入后整体替换原范围，不传表示保持原值
     */
    @JsonProperty("range")
    private CustomerAcquisitionRange range;

    /**
     * 通过链接添加客户时是否免于企业成员验证；不传表示保持原值
     */
    @JsonProperty("skip_verify")
    private Boolean skipVerify;

    /**
     * 新的成员分配优先级配置；不传表示保持原值
     */
    @JsonProperty("priority_option")
    private CustomerAcquisitionPriorityOption priorityOption;

    /**
     * 是否将该获客链接标记为广告来源；不传表示保持原值
     */
    @JsonProperty("mark_source")
    private Boolean markSource;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public CustomerAcquisitionRange getRange() {
        return range;
    }

    public void setRange(CustomerAcquisitionRange range) {
        this.range = range;
    }

    public Boolean getSkipVerify() {
        return skipVerify;
    }

    public void setSkipVerify(Boolean skipVerify) {
        this.skipVerify = skipVerify;
    }

    public CustomerAcquisitionPriorityOption getPriorityOption() {
        return priorityOption;
    }

    public void setPriorityOption(CustomerAcquisitionPriorityOption priorityOption) {
        this.priorityOption = priorityOption;
    }

    public Boolean getMarkSource() {
        return markSource;
    }

    public void setMarkSource(Boolean markSource) {
        this.markSource = markSource;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("linkId", linkId)
                .append("linkName", linkName)
                .append("range", range)
                .append("skipVerify", skipVerify)
                .append("priorityOption", priorityOption)
                .append("markSource", markSource)
                .toString();
    }
}