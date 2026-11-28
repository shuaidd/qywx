package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 获客链接的成员分配优先级配置
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerAcquisitionPriorityOption {

    /**
     * 成员分配优先级类型；1-按全企业配置优先，2-按指定成员列表配置优先
     */
    @JsonProperty("priority_type")
    private Integer priorityType;

    /**
     * 优先分配客户的企业成员 userid 列表；priorityType 为 2 时有效
     */
    @JsonProperty("priority_userid_list")
    private List<String> priorityUseridList;

    public Integer getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(Integer priorityType) {
        this.priorityType = priorityType;
    }

    public List<String> getPriorityUseridList() {
        return priorityUseridList;
    }

    public void setPriorityUseridList(List<String> priorityUseridList) {
        this.priorityUseridList = priorityUseridList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("priorityType", priorityType)
                .append("priorityUseridList", priorityUseridList)
                .toString();
    }
}