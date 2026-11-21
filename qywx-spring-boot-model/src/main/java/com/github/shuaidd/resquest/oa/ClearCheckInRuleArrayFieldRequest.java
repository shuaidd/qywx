package com.github.shuaidd.resquest.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 清空打卡规则数组元素请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class ClearCheckInRuleArrayFieldRequest {

    /**
     * 打卡规则id
     */
    @JsonProperty("groupid")
    private Integer groupId;

    /**
     * 要清空的字段标识列表；1-spe_workdays，2-spe_offdays，3-wifimac_infos，4-loc_infos
     */
    @JsonProperty("clear_field")
    private List<Integer> clearFields;

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

    public List<Integer> getClearFields() {
        return clearFields;
    }

    public void setClearFields(List<Integer> clearFields) {
        this.clearFields = clearFields;
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
                .append("clearFields", clearFields)
                .append("effectiveNow", effectiveNow)
                .toString();
    }
}
