package com.github.shuaidd.dto.addressbook;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-04 21:52
 **/
public class UnassignedCustomer {
    @JsonProperty("handover_userid")
    private String handoverUserId;
    @JsonProperty("external_userid")
    private String externalUserId;

    @JsonProperty("dimission_time")
    private Date dimissionTime;

    public String getHandoverUserId() {
        return handoverUserId;
    }

    public void setHandoverUserId(String handoverUserId) {
        this.handoverUserId = handoverUserId;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public Date getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(Date dimissionTime) {
        this.dimissionTime = dimissionTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("handoverUserId", handoverUserId)
                .append("externalUserId", externalUserId)
                .append("dimissionTime", dimissionTime)
                .toString();
    }
}
