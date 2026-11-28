package com.github.shuaidd.resquest.government;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 添加网格请求
 *
 * @author ddshuai
 * date 2026-11-22
 **/
public class CreateGridRequest {

    /**
     * 网格名称
     */
    @JsonProperty("grid_name")
    private String gridName;

    /**
     * 父节点的网格id
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
