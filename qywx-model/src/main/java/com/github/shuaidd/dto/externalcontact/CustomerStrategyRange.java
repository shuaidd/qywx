package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 客户联系规则组管理范围节点
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategyRange {

    /**
     * 管理范围节点类型：1-成员，2-部门
     */
    @JsonProperty("type")
    private Integer type;

    /**
     * 管理范围内的成员 userid；仅 type 为 1 时有效
     */
    @JsonProperty("userid")
    private String userid;

    /**
     * 管理范围内的部门 partyid；仅 type 为 2 时有效
     */
    @JsonProperty("partyid")
    private Integer partyid;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getPartyid() {
        return partyid;
    }

    public void setPartyid(Integer partyid) {
        this.partyid = partyid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("type", type)
                .append("userid", userid)
                .append("partyid", partyid)
                .toString();
    }
}