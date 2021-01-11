package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.UnassignedCustomer;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-04 21:54
 **/
public class UnassignedListResponse extends AbstractBaseResponse {

    @JsonProperty("info")
    private List<UnassignedCustomer> customers;

    @JsonProperty("is_last")
    private Boolean isLast;

    public List<UnassignedCustomer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<UnassignedCustomer> customers) {
        this.customers = customers;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }
}
