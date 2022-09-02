package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 * date 2022-09-02 12:53
 **/
public class SpaceShareResponse extends AbstractBaseResponse {

    @JsonProperty(value = "space_share_url")
    private String spaceShareUrl;

    public String getSpaceShareUrl() {
        return spaceShareUrl;
    }

    public void setSpaceShareUrl(String spaceShareUrl) {
        this.spaceShareUrl = spaceShareUrl;
    }
}
