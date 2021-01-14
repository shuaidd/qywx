package com.github.shuaidd.resquest;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2019-04-05 19:21
 **/
public class InviteUserRequest {
    private List<String> user;
    private List<Integer> tag;
    private List<Integer> party;

    public List<String> getUser() {
        return user;
    }

    public void setUser(List<String> user) {
        this.user = user;
    }

    public List<Integer> getTag() {
        return tag;
    }

    public void setTag(List<Integer> tag) {
        this.tag = tag;
    }

    public List<Integer> getParty() {
        return party;
    }

    public void setParty(List<Integer> party) {
        this.party = party;
    }
}
