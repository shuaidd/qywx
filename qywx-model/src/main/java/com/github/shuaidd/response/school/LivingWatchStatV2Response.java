package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 观看直播人员统计响应（v2分页协议）
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class LivingWatchStatV2Response extends AbstractBaseResponse {

    /**
     * 观看直播统计信息
     */
    @JsonProperty("stat_info")
    private LivingWatchStatV2Info statInfo;

    public LivingWatchStatV2Info getStatInfo() {
        return statInfo;
    }

    public void setStatInfo(LivingWatchStatV2Info statInfo) {
        this.statInfo = statInfo;
    }
}
