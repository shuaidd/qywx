package com.github.shuaidd.response.tool;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.tool.LivingInfoData;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 描述
 *
 * @author ddshuai
 * date 2021-01-12 13:31
 **/
public class LivingInfoResponse extends AbstractBaseResponse {

    @JsonProperty("living_info")
    private LivingInfoData livingInfo;

    public LivingInfoData getLivingInfo() {
        return livingInfo;
    }

    public void setLivingInfo(LivingInfoData livingInfo) {
        this.livingInfo = livingInfo;
    }
}
