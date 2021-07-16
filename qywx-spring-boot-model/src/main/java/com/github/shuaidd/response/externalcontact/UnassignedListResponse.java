package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UnassignedCustomer;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 21:54
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customers", customers)
                .append("isLast", isLast)
                .toString();
    }
}
