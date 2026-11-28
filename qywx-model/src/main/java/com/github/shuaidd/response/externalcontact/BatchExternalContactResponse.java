package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.BatchExternalContact;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-02 19:45
 **/
public class BatchExternalContactResponse extends AbstractBaseResponse {

    @JsonProperty("external_contact_list")
    private List<BatchExternalContact> contactList;

    public List<BatchExternalContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<BatchExternalContact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("contactList", contactList)
                .toString();
    }
}
