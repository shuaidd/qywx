package com.github.shuaidd.resquest.externalcontact;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.shuaidd.dto.externalcontact.MomentStrategyPrivilege;
import com.github.shuaidd.dto.externalcontact.MomentStrategyRange;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * 编辑客户朋友圈规则组及其管理范围请求
 *
 * @author ddshuai
 * date 2026-11-21
 **/
public class MomentStrategyEditRequest {

    /**
     * 客户朋友圈规则组 ID
     */
    @JsonProperty("strategy_id")
    private Integer strategyId;

    /**
     * 规则组名称；为空时不编辑
     */
    @JsonProperty("strategy_name")
    private String strategyName;

    /**
     * 管理员 userid 列表；非空时覆盖旧列表
     */
    @JsonProperty("admin_list")
    private List<String> adminList;

    /**
     * 客户朋友圈权限配置；非空时覆盖旧配置
     */
    @JsonProperty("privilege")
    private MomentStrategyPrivilege privilege;

    /**
     * 要加入管理范围的节点列表；单次最多 100 个节点
     */
    @JsonProperty("range_add")
    private List<MomentStrategyRange> rangeAdd;

    /**
     * 要从管理范围删除的节点列表；单次最多 100 个节点
     */
    @JsonProperty("range_del")
    private List<MomentStrategyRange> rangeDel;

    public Integer getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Integer strategyId) {
        this.strategyId = strategyId;
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

    public List<MomentStrategyRange> getRangeAdd() {
        return rangeAdd;
    }

    public void setRangeAdd(List<MomentStrategyRange> rangeAdd) {
        this.rangeAdd = rangeAdd;
    }

    public List<MomentStrategyRange> getRangeDel() {
        return rangeDel;
    }

    public void setRangeDel(List<MomentStrategyRange> rangeDel) {
        this.rangeDel = rangeDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("strategyId", strategyId)
                .append("strategyName", strategyName)
                .append("adminList", adminList)
                .append("privilege", privilege)
                .append("rangeAdd", rangeAdd)
                .append("rangeDel", rangeDel)
                .toString();
    }
}
