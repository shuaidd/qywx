package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 获客链接使用详情响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionStatisticResponse extends AbstractBaseResponse {

    /**
     * 统计区间内点击获客链接的客户数量
     */
    @JsonProperty("click_link_customer_cnt")
    private Integer clickLinkCustomerCnt;

    /**
     * 统计区间内通过获客链接新增的客户数量
     */
    @JsonProperty("new_customer_cnt")
    private Integer newCustomerCnt;

    public Integer getClickLinkCustomerCnt() {
        return clickLinkCustomerCnt;
    }

    public void setClickLinkCustomerCnt(Integer clickLinkCustomerCnt) {
        this.clickLinkCustomerCnt = clickLinkCustomerCnt;
    }

    public Integer getNewCustomerCnt() {
        return newCustomerCnt;
    }

    public void setNewCustomerCnt(Integer newCustomerCnt) {
        this.newCustomerCnt = newCustomerCnt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("clickLinkCustomerCnt", clickLinkCustomerCnt)
                .append("newCustomerCnt", newCustomerCnt)
                .toString();
    }
}