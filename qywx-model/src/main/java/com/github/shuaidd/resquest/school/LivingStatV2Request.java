package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取上课直播观看统计的请求（v2分页协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingStatV2Request {

    /**
     * 直播id
     */
    @JsonProperty("livingid")
    private String livingid;

    /**
     * 上一次调用返回的next_cursor，初次调用可以填0
     */
    @JsonProperty("next_cursor")
    private String nextCursor;

    public String getLivingid() {
        return livingid;
    }

    public void setLivingid(String livingid) {
        this.livingid = livingid;
    }

    public String getNextCursor() {
        return nextCursor;
    }

    public void setNextCursor(String nextCursor) {
        this.nextCursor = nextCursor;
    }
}
