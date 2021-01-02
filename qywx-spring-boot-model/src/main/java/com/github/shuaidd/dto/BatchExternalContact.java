package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-02 19:42
 **/
public class BatchExternalContact {

    @JsonProperty("external_contact")
    private ExternalContactDetail externalContact;

    @JsonProperty("follow_info")
    private FollowUserDetail followUser;

    public ExternalContactDetail getExternalContact() {
        return externalContact;
    }

    public void setExternalContact(ExternalContactDetail externalContact) {
        this.externalContact = externalContact;
    }

    public FollowUserDetail getFollowUser() {
        return followUser;
    }

    public void setFollowUser(FollowUserDetail followUser) {
        this.followUser = followUser;
    }
}
