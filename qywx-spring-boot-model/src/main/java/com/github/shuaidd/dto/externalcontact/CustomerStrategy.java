package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.shuaidd.json.Long2DateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.List;

/**
 * 客户联系规则组
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class CustomerStrategy {

    @JsonProperty("strategy_id")
    private Integer strategyId;

    @JsonProperty("parent_id")
    private Integer parentId;

    @JsonProperty("strategy_name")
    private String strategyName;

    @JsonProperty("create_time")
    @JsonDeserialize(using = Long2DateDeserializer.class)
    private Date createTime;

    @JsonProperty("admin_list")
    private List<String> adminList;

    @JsonProperty("privilege")
    private CustomerStrategyPrivilege privilege;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<String> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<String> adminList) {
        this.adminList = adminList;
    }

    public CustomerStrategyPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(CustomerStrategyPrivilege privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .append("parentId", parentId)
                .append("strategyName", strategyName)
                .append("createTime", createTime)
                .append("adminList", adminList)
                .append("privilege", privilege)
                .toString();
    }
}
