package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.BatchExternalContact;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 19:45
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
}
