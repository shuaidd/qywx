package com.github.shuaidd.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 描述
 *
 * @author ddshuai
 * @date 2021-01-12 13:45
 **/
public class GetWatchStatRequest {
    @JsonProperty("livingid")
    private String livingId;
    @JsonProperty("next_key")
    private String nextKey;

    public String getLivingId() {
        return livingId;
    }

    public void setLivingId(String livingId) {
        this.livingId = livingId;
    }

    public String getNextKey() {
        return nextKey;
    }

    public void setNextKey(String nextKey) {
        this.nextKey = nextKey;
    }
}
