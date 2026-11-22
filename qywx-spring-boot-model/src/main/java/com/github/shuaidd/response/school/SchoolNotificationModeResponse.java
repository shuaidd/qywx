package com.github.shuaidd.response.school;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;

/**
 * 关注「学校通知」模式响应
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class SchoolNotificationModeResponse extends AbstractBaseResponse {

    /**
     * 关注模式：1-可扫码填写资料加入；2-禁止扫码填写资料加入
     */
    @JsonProperty("subscribe_mode")
    private Integer subscribeMode;

    public Integer getSubscribeMode() {
        return subscribeMode;
    }

    public void setSubscribeMode(Integer subscribeMode) {
        this.subscribeMode = subscribeMode;
    }
}
