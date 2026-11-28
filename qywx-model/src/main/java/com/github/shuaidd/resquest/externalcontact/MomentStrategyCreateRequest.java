package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentStrategyPrivilege;
import com.github.shuaidd.dto.externalcontact.MomentStrategyRange;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 创建客户朋友圈规则组请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyCreateRequest {

    /**
     * 父规则组 ID；没有父规则组时为 0 或不填
     */
    @JsonProperty("parent_id")
    private Integer parentId;

    /**
     * 规则组名称
     */
    @JsonProperty("strategy_name")
    private String strategyName;

    /**
     * 规则组管理员 userid 列表
     */
    @JsonProperty("admin_list")
    private List<String> adminList;

    /**
     * 客户朋友圈权限配置；存在父规则组时继承父规则组配置
     */
    @JsonProperty("privilege")
    private MomentStrategyPrivilege privilege;

    /**
     * 规则组管理范围节点列表；单次最多 100 个节点
     */
    @JsonProperty("range")
    private List<MomentStrategyRange> range;

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

    public List<MomentStrategyRange> getRange() {
        return range;
    }

    public void setRange(List<MomentStrategyRange> range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("parentId", parentId)
                .append("strategyName", strategyName)
                .append("adminList", adminList)
                .append("privilege", privilege)
                .append("range", range)
                .toString();
    }
}
