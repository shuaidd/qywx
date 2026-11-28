package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.ExternalContactDetail;
import com.github.shuaidd.dto.externalcontact.FollowUserDetail;
import com.github.shuaidd.response.AbstractBaseResponse;

import java.util.List;

/**
 * 描述 客户详情
 *
 * @author ddshuai
 * date 2021-01-02 16:32
 **/
public class ExternalContactResponse extends AbstractBaseResponse {

    @JsonProperty("external_contact")
    private ExternalContactDetail externalContact;

    @JsonProperty("follow_user")
    private List<FollowUserDetail> followUsers;

    public ExternalContactDetail getExternalContact() {
        return externalContact;
    }

    public void setExternalContact(ExternalContactDetail externalContact) {
        this.externalContact = externalContact;
    }

    public List<FollowUserDetail> getFollowUsers() {
        return followUsers;
    }

    public void setFollowUsers(List<FollowUserDetail> followUsers) {
        this.followUsers = followUsers;
    }
}
