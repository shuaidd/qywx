package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.Customer;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-05 21:52
 **/
public class MomentCustomerListResponse extends AbstractBaseResponse {
    @JsonProperty("next_cursor")
    private String nextCursor;
    @JsonProperty("customer_list")
    private List<Customer> customers;

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
