package com.github.shuaidd.response.government;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 网格信息
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class GridInfo {

    /**
     * 网格id
     */
    @JsonProperty("grid_id")
    private String gridId;

    /**
     * 网格名称
     */
    @JsonProperty("grid_name")
    private String gridName;

    /**
     * 父节点网格id
     */
    @JsonProperty("grid_parent_id")
    private String gridParentId;

    /**
     * 网格负责人userid列表
     */
    @JsonProperty("grid_admin")
    private List<String> gridAdmin;

    /**
     * 网格成员userid列表
     */
    @JsonProperty("grid_member")
    private List<String> gridMember;

    public String getGridId() {
        return gridId;
    }

    public void setGridId(String gridId) {
        this.gridId = gridId;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getGridParentId() {
        return gridParentId;
    }

    public void setGridParentId(String gridParentId) {
        this.gridParentId = gridParentId;
    }

    public List<String> getGridAdmin() {
        return gridAdmin;
    }

    public void setGridAdmin(List<String> gridAdmin) {
        this.gridAdmin = gridAdmin;
    }

    public List<String> getGridMember() {
        return gridMember;
    }

    public void setGridMember(List<String> gridMember) {
        this.gridMember = gridMember;
    }
}
