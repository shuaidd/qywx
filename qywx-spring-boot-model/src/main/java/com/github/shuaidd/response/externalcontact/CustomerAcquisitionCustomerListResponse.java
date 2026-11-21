package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.CustomerAcquisitionCustomer;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获客客户列表响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionCustomerListResponse extends AbstractBaseResponse {

    /**
     * 当前页通过获客链接添加的客户信息列表
     */
    @JsonProperty("customer_list")
    private List<CustomerAcquisitionCustomer> customerList;

    public List<CustomerAcquisitionCustomer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerAcquisitionCustomer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("customerList", customerList)
                .toString();
    }
}