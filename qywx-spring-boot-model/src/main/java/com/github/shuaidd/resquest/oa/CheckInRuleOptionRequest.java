package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.checkin.CheckInRuleGroup;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 创建或修改打卡规则请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CheckInRuleOptionRequest {

    /**
     * 是否立即生效；不传时默认为false
     */
    @JsonProperty("effective_now")
    private Boolean effectiveNow;

    /**
     * 打卡规则详细定义；创建时忽略groupId，修改时必须指定groupId
     */
    @JsonProperty("group")
    private CheckInRuleGroup group;

    public Boolean getEffectiveNow() {
        return effectiveNow;
    }

    public void setEffectiveNow(Boolean effectiveNow) {
        this.effectiveNow = effectiveNow;
    }

    public CheckInRuleGroup getGroup() {
        return group;
    }

    public void setGroup(CheckInRuleGroup group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("effectiveNow", effectiveNow)
                .append("group", group)
                .toString();
    }
}
