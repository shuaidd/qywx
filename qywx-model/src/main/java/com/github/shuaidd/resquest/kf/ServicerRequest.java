package com.github.shuaidd.resquest.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-11-25 09:18
 **/
public class ServicerRequest {

    @JsonProperty("open_kfid")
    private String openKfId;

    @JsonProperty("userid_list")
    private List<String> userIds;

    public String getOpenKfId() {
        return openKfId;
    }

    public void setOpenKfId(String openKfId) {
        this.openKfId = openKfId;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
