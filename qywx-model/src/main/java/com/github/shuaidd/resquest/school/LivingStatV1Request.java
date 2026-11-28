package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取上课直播观看统计的请求（v1分页协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingStatV1Request {

    /**
     * 直播id
     */
    @JsonProperty("livingid")
    private String livingid;

    /**
     * 上一次调用返回的next_key，初次调用可以填0
     */
    @JsonProperty("next_key")
    private String nextKey;

    public String getLivingid() {
        return livingid;
    }

    public void setLivingid(String livingid) {
        this.livingid = livingid;
    }

    public String getNextKey() {
        return nextKey;
    }

    public void setNextKey(String nextKey) {
        this.nextKey = nextKey;
    }
}
