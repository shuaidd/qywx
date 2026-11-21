package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建客户群加入群聊配置响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupChatJoinWayCreateResponse extends AbstractBaseResponse {

    /**
     * 新创建的加入群聊配置 ID
     */
    @JsonProperty("config_id")
    private String configId;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("configId", configId)
                .toString();
    }
}
