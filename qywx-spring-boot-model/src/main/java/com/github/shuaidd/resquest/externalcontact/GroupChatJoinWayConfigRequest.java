package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询或删除客户群加入群聊配置请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupChatJoinWayConfigRequest {

    /**
     * 加入群聊配置 ID
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
