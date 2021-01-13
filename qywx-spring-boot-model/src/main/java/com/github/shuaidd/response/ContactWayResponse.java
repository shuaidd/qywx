package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.ConclusionData;
import com.github.shuaidd.dto.ContactWayDetail;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author shuaidongdong
 */
public class ContactWayResponse extends AbstractBaseResponse {
    @JsonProperty("contact_way")
   private ContactWayDetail contactWayDetail;

    public ContactWayDetail getContactWayDetail() {
        return contactWayDetail;
    }

    public void setContactWayDetail(ContactWayDetail contactWayDetail) {
        this.contactWayDetail = contactWayDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contactWayDetail", contactWayDetail)
                .toString();
    }
}
