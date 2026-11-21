package com.github.shuaidd.response.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.GroupChatJoinWay;
import com.github.shuaidd.response.AbstractBaseResponse;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询客户群加入群聊配置响应
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class GroupChatJoinWayConfigResponse extends AbstractBaseResponse {

    /**
     * 加入群聊配置详情
     */
    @JsonProperty("join_way")
    private GroupChatJoinWay joinWay;

    public GroupChatJoinWay getJoinWay() {
        return joinWay;
    }

    public void setJoinWay(GroupChatJoinWay joinWay) {
        this.joinWay = joinWay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("joinWay", joinWay)
                .toString();
    }
}
