package com.github.shuaidd.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.WatchStatInfo;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-12 16:52
 **/
public class WatchStatInfoResponse extends AbstractBaseResponse {
    private Integer ending;
    @JsonProperty("next_key")
    private String nextKey;
    @JsonProperty("stat_info")
    private WatchStatInfo watchStatInfo;

    public Integer getEnding() {
        return ending;
    }

    public void setEnding(Integer ending) {
        this.ending = ending;
    }

    public String getNextKey() {
        return nextKey;
    }

    public void setNextKey(String nextKey) {
        this.nextKey = nextKey;
    }

    public WatchStatInfo getWatchStatInfo() {
        return watchStatInfo;
    }

    public void setWatchStatInfo(WatchStatInfo watchStatInfo) {
        this.watchStatInfo = watchStatInfo;
    }
}
