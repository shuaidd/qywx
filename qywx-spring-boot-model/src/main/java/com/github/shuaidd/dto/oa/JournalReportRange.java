package com.github.shuaidd.dto.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.addressbook.UserId;

import java.util.List;
import java.util.StringJoiner;

/**
 * 描述
 *
 * @author ddshuai
 *date 2021-07-20 10:07
 **/
public class JournalReportRange {

    @JsonProperty("user_list")
    private List<UserId> userList;

    @JsonProperty("party_list")
    private List<OpenPartyId> openPartyIds;

    @JsonProperty("tag_list")
    private List<OpenTagId> openTagIds;

    @JsonProperty("leader_list")
    private List<LeaderLevel> leaderList;

    public List<UserId> getUserList() {
        return userList;
    }

    public void setUserList(List<UserId> userList) {
        this.userList = userList;
    }

    public List<OpenPartyId> getOpenPartyIds() {
        return openPartyIds;
    }

    public void setOpenPartyIds(List<OpenPartyId> openPartyIds) {
        this.openPartyIds = openPartyIds;
    }

    public List<OpenTagId> getOpenTagIds() {
        return openTagIds;
    }

    public void setOpenTagIds(List<OpenTagId> openTagIds) {
        this.openTagIds = openTagIds;
    }

    public List<LeaderLevel> getLeaderList() {
        return leaderList;
    }

    public void setLeaderList(List<LeaderLevel> leaderList) {
        this.leaderList = leaderList;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", JournalReportRange.class.getSimpleName() + "[", "]")
                .add("userList=" + userList)
                .add("openPartyIds=" + openPartyIds)
                .add("openTagIds=" + openTagIds)
                .toString();
    }
}
