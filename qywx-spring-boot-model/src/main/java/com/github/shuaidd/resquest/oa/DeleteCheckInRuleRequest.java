package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 删除打卡规则请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class DeleteCheckInRuleRequest {

    /**
     * 要删除的打卡规则id
     */
    @JsonProperty("groupid")
    private Integer groupId;

    /**
     * 是否立即生效；不传时默认为false
     */
    @JsonProperty("effective_now")
    private Boolean effectiveNow;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Boolean getEffectiveNow() {
        return effectiveNow;
    }

    public void setEffectiveNow(Boolean effectiveNow) {
        this.effectiveNow = effectiveNow;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("groupId", groupId)
                .append("effectiveNow", effectiveNow)
                .toString();
    }
}
