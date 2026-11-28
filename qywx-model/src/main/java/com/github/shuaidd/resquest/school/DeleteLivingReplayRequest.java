package com.github.shuaidd.resquest.school;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除直播回放数据的请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteLivingReplayRequest {

    /**
     * 直播id
     */
    @JsonProperty("livingid")
    private String livingid;

    public String getLivingid() {
        return livingid;
    }

    public void setLivingid(String livingid) {
        this.livingid = livingid;
    }
}
