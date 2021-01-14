package com.github.shuaidd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-12 16:51
 **/
public class WatchStatInfo {
    private List<WatchUser> users;
    @JsonProperty("external_users")
    private List<WatchUser> externalUsers;

    public List<WatchUser> getUsers() {
        return users;
    }

    public void setUsers(List<WatchUser> users) {
        this.users = users;
    }

    public List<WatchUser> getExternalUsers() {
        return externalUsers;
    }

    public void setExternalUsers(List<WatchUser> externalUsers) {
        this.externalUsers = externalUsers;
    }
}
