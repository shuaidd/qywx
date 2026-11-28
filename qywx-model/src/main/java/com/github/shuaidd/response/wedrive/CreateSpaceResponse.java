package com.github.shuaidd.response.wedrive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * @author ddshuai
 * date 2022-09-01 22:13
 **/
public class CreateSpaceResponse extends AbstractBaseResponse {

    @JsonProperty(value = "spaceid")
    private String spaceId;

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }
}
