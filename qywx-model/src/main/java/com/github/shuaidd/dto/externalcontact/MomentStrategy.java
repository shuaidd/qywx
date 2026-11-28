package com.github.shuaidd.dto.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 客户朋友圈规则组详情
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategy {

    /**
     * 客户朋友圈规则组 ID
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 父规则组 ID；根规则组为 0
     */
    @JsonProperty("parent_id")
    private Integer parentId;

    /**
     * 规则组名称
     */
    @JsonProperty("strategy_name")
    private String strategyName;

    /**
     * 规则组创建时间；Unix 秒级时间戳
     */
    @JsonProperty("create_time")
    private Long createTime;

    /**
     * 规则组管理员 userid 列表
     */
    @JsonProperty("admin_list")
    private List<String> adminList;

    /**
     * 客户朋友圈权限配置
     */
    @JsonProperty("privilege")
    private MomentStrategyPrivilege privilege;

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<String> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<String> adminList) {
        this.adminList = adminList;
    }

    public MomentStrategyPrivilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(MomentStrategyPrivilege privilege) {
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
