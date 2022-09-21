package com.github.shuaidd.resquest.tool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author ddshuai
 * date 2022-09-04 21:08
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenUserId2UserIdRequest {

    @JsonProperty("source_agentid")
    private int sourceAgentId;

    @JsonProperty("open_userid_list")
    private List<String> openUserIdList;

    public int getSourceAgentId() {
        return sourceAgentId;
    }

    public void setSourceAgentId(int sourceAgentId) {
        this.sourceAgentId = sourceAgentId;
    }

    public List<String> getOpenUserIdList() {
        return openUserIdList;
    }

    public void setOpenUserIdList(List<String> openUserIdList) {
        this.openUserIdList = openUserIdList;
    }
}
