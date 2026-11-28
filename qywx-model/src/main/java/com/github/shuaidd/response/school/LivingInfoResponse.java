package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 上课直播详情响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingInfoResponse extends AbstractBaseResponse {

    /**
     * 直播信息
     */
    @JsonProperty("living_info")
    private LivingInfo livingInfo;

    public LivingInfo getLivingInfo() {
        return livingInfo;
    }

    public void setLivingInfo(LivingInfo livingInfo) {
        this.livingInfo = livingInfo;
    }
}
